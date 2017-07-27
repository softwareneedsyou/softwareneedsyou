/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.compiler;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

import fr.esgi.projet.softwareneedsyou.api.ConsoleOutput;
import fr.esgi.projet.softwareneedsyou.api.spi.Plugin;
import lombok.NonNull;

/**
 * @author Tristan
 *
 */
public interface PluginCompiler extends Plugin {
	/**
	 * ID unique au plugin.
	 * Permet la reconnaissance du plugin pour les histoires.
	 * @return plugin's id
	 */
	//UUID getID();
	
	/**
	 * Liste des langages (de programmation) supportés par le plugin
	 * @return liste de string
	 */
	@NonNull Set<String> getLngSupported();

	@NonNull ResultCompiler compileAndTest(@NonNull final String code,
											 @NonNull final InputStream testsDef,
											 @NonNull final ConsoleOutput console) throws CompilerException;
}
