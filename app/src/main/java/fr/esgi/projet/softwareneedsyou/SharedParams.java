/**
 * 
 */
package fr.esgi.projet.softwareneedsyou;

import java.nio.file.Paths;

/**
 * Classe utilitaire stockant/partageant les variables et paramètres global à l'application
 * @author Tristan
 *
 */
public final class SharedParams {
	private SharedParams() { ; }

	/**
	 * Dossier propre à l'application (paramètres et plugins)
	 */
	public static final String AppParamsFolder = Paths.get(System.getProperty("user.home", "."), "SNY").toString();

	/**
	 * Sous-dossier où se trouve les plugins
	 */
	public static final String AppPluginsFolder = Paths.get(AppParamsFolder, "plugins").toString();
}
