/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author Tristan
 *
 */
@EqualsAndHashCode
@ToString(of={})
abstract class PluginLoader<D extends PluginDescriptor<P>, P extends AutoCloseable> implements AutoCloseable {
	@NonNull private final ServiceLoader<D> sl /*= ServiceLoader.load(Plugin.class)*/;
	@NonNull private final Map<D, P> addons = new HashMap<>();
	
	/**
	 * Initialise un loader pour le type de plugin donné.
	 * @param type Type de plugin à géré
	 */
	protected PluginLoader(@NonNull final Class<D> type) {
		this.sl = ServiceLoader.load(type);
		this.refreshList();
	}

	/**
	 * Retourne la liste des plugins disponibles, détecter depuis le dernière refresh.
	 * @return une liste non modifiable des plugins détectés
	 */
	public Set<D> getImplementations() {
		return Collections.unmodifiableSet(this.addons.keySet());
	}
	
	/**
	 * Test si un plugin est cargé ou non.
	 * @param plugin
	 * @return
	 */
	public boolean isLoaded(@NonNull final D plugin) {
		return this.addons.get(plugin) != null;
	}
	
	/**
	 * Charge un plugin s'il n'est pas déjà chargé
	 * @param plugin le plugin à chargé
	 * @throws PluginException si le plugin est déjà chargé
	 * @see {@link #initPluginParams(PluginDeclare)}
	 */
	public void load(@NonNull final D plugin) throws PluginException {
		if(this.addons.get(plugin) != null)
			throw new PluginException("Plugin déjà chargé");
		else
			this.addons.put(plugin, plugin.getInstance(Collections.emptyMap()));
	}
	
	/**
	 * Appelé par {@link #load(PluginDeclare)} pour passer les paramètres au plugin lors de l'initialisation.
	 * @param plugin le plugin qui va être initialisé
	 * @return une map, jamais null
	 * @see #load(PluginDeclare)
	 */
	protected abstract Map<String, Object> initPluginParams(@NonNull final D plugin);
	
	/**
	 * Ferme/décharge un plugin précédement chargé.
	 * @param plugin le plugin à déchargé
	 * @throws PluginException si une erreur survient durant la fermeture du plugin
	 * @throws InvalidArgumentsException si le plugin n'est pas reconnu
	 */
	public void unload(@NonNull final D plugin) throws PluginException, InvalidArgumentsException {
		if(this.addons.containsKey(plugin)) {
			final P pl = this.addons.put(plugin, null);
			if(pl != null)
				try {
					pl.close();
				} catch (final Exception e) {
					throw new PluginException("Erreur lors de la fermeture du plugin", e);
				}
		} else
			throw new InvalidArgumentsException("Plugin non reconnu");
	}

	/**
	 * Rafraîchi la liste des plugins disponibles.<br/>
	 * Les plugins précédemment chargés qui ne sont plus détecté seront déchargés.
	 */
	public void refreshList() {
		List<D> tmp = new LinkedList<>();
		this.sl.reload();
		this.sl.forEach(tmp::add);
		this.addons.forEach((k, v) -> {
			if(!tmp.contains(k))
				try {
					this.unload((D) this.addons.remove(k));
				} catch (PluginException | InvalidArgumentsException e) {
					e.printStackTrace();
				}
		});
		tmp.forEach(d -> this.addons.computeIfAbsent(d, null));
	}

}
