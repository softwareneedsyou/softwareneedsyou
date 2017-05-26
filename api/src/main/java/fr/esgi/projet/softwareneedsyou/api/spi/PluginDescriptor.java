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
 * <p>La méthode {@link Object#equals(Object)} et {@link Object#hashCode()} doit être (re)définie car elle est utilisée pour géré les plugins chargés.</p>
 * @param <P> Le type de plugin décrit
 */
public interface PluginDescriptor<P extends AutoCloseable> {
	/**
	 * Nom "visible" du plugin dans l'interface<br>
	 * Valeur <code>null</code> interdit.
	 * @return Nom du plugin
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
	 * Valeur <code>null</code> interdit.
	 * @return description complète du plugin
	 */
	@NonNull String getDescription();
	
	/**
	 * Description courte du plugin<br>
	 * Valeur <code>null</code> interdit.
	 * @return description courte du plugin
	 */
	@NonNull String getShortDescription();

	/**
	 * Permet d'éviter de charger tous les plugins en mémoire<br>
	 * Valeur <code>null</code> interdit.
	 * <i>note: Pattern Factory</i>
	 * @param params les paramètres d'initialisation
	 * @return instance (singleton ou non) du plugin
	 * @throws InitialisationException si l'initilisation du plugin à échouée
	 */
	@NonNull P getInstance(Map<String, Object> params) throws InitialisationException;
}
