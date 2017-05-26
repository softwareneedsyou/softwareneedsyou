/**
 * 
 */
package fr.esgi.projet.softwareneedsyou;

import java.io.File;

/**
 * Classe utilitaire stockant/partageant les variables et paramètres global à l'application
 * @author Tristan
 *
 */
final class SharedParams {
	private SharedParams() { ; }

	/**
	 * Dossier propre à l'application (paramètres et plugins)
	 */
	public static final String AppParamsFolder = System.getProperty("user.home", ".") + System.getProperty("path.separator", File.separator) + "SNY";

	/**
	 * Sous-dossier où se trouve les plugins
	 */
	public static final String AppPluginsFolder = AppParamsFolder + System.getProperty("path.separator", File.separator) + "plugins";
}
