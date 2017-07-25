/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.spi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public abstract class PluginLoader<D extends PluginDescriptor<P>, P extends Plugin> implements AutoCloseable {
	@NonNull private final ServiceLoader<D> sl /*= ServiceLoader.load(Plugin.class)*/;
	@NonNull private final Map<D, P> addons = new HashMap<>();

	/**
	 * Initialise un loader pour le type de plugin donné.
	 * @param type Type de plugin à géré
	 */
	protected PluginLoader(@NonNull final Class<D> type) {
		System.out.println("// init PluginLoader for " + type);
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
	
	public Optional<P> getPluginImpl(@NonNull final D plugin) {
		return Optional.ofNullable(this.addons.get(plugin));
	}
	
	public Map<D, P> getPluginsLoaded() {
		return Collections.unmodifiableMap(this.addons);
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
	 * @see #initPluginParams(PluginDescriptor)
	 */
	public void load(@NonNull final D plugin) throws PluginException {
		System.out.println("// load plugin " + plugin);
		if(this.addons.get(plugin) != null)
			throw new PluginException("Plugin déjà chargé");
		else
			this.addons.put(plugin, plugin.getInstance(Collections.emptyMap()));
	}
	
	/**
	 * Appelé par {@link #load(PluginDescriptor)} pour passer les paramètres au plugin lors de l'initialisation.
	 * @param plugin le plugin qui va être initialisé
	 * @return une map, jamais null
	 * @see #load(PluginDescriptor)
	 */
	protected abstract Map<String, Object> initPluginParams(@NonNull final D plugin);
	
	/**
	 * Ferme/décharge un plugin précédement chargé.
	 * @param plugin le plugin à déchargé
	 * @throws PluginException si une erreur survient durant la fermeture du plugin
	 * @throws InvalidArgumentsException si le plugin n'est pas reconnu
	 */
	public void unload(@NonNull final D plugin) throws PluginException, InvalidArgumentsException {
		System.out.println("// unload plugin " + plugin);
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
	 * Rafraîchi la liste des plugins disponibles.<br>
	 * Les plugins précédemment chargés qui ne sont plus détecté seront déchargés.
	 */
	public void refreshList() {
		System.out.println("// refreshList");
		List<D> tmp = new LinkedList<>();
		this.sl.reload();
		this.sl.forEach(tmp::add); //load all classes found
		System.out.println("ServiceLoader -> " + Arrays.toString(tmp.toArray()));
		this.addons.forEach((k, v) -> { //unload old classes
			if(!tmp.contains(k))
				try {
					this.unload((D) this.addons.remove(k));
				} catch (PluginException | InvalidArgumentsException e) {
					e.printStackTrace();
				}
		});
		tmp.forEach(d -> this.addons.putIfAbsent(d, null)); //add new classes
	}

}
