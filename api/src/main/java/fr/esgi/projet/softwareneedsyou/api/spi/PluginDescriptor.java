/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.spi;

import java.util.Map;

import lombok.NonNull;

/**
 * @author Tristan
 *
 * <p>Interface générique décrivant un plugin de base</p>
 * <p>La méthode {@link #equals(Object)} et {@link #hashCode()} doit être définie car elle est utilisée pour géré les plugins chargés.</p>
 */
public interface PluginDescriptor<P extends AutoCloseable> {
	/**
	 * Nom "visible" du plugin dans l'interface<br/>
	 * Valeur <code>null</null> interdit.
	 * @return
	 */
	@NonNull default String getName() {
		return this.getClass().getPackage().getImplementationTitle();
	};
	
	/**
	 * Retourne la version du plugin
	 * @return version du plugin
	 */
	@NonNull default String getVersion() {
		return this.getClass().getPackage().getImplementationVersion();
	};
	
	/**
	 * 
	 * @return courte description du plugin
	 */
	@NonNull String getResume();
	
	/**
	 * Valeur <code>null</null> interdit.
	 * @return description complète du plugin
	 */
	@NonNull String getDescription();
	
	/**
	 * Description courte du plugin<br/>
	 * Valeur <code>null</null> interdit.
	 * @return
	 */
	@NonNull String getShortDescription();

	/**
	 * Permet d'éviter de charger tous les plugins en mémoire<br/>
	 * Valeur <code>null</null> interdit.
	 * <i>note: Pattern Factory</i>
	 * @return instance (singleton ou non) du plugin
	 * @throws InvalidArgumentsException si les paramètres transmis sont invalides
	 * @throws InitialisationException si l'initilisation du plugin à échouée
	 */
	@NonNull P getInstance(Map<String, Object> params) throws InitialisationException;
}
