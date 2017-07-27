/**
 * 
 */
package fr.esgi.projet.softwareneedsyou;

import java.nio.file.Path;
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
	public static final Path AppParamsFolder = Paths.get(System.getProperty("user.home", "."), "SNY");

	/**
	 * Sous-dossier où se trouve les plugins
	 */
	public static final Path AppPluginsFolder = AppParamsFolder.resolve("plugins");
}
