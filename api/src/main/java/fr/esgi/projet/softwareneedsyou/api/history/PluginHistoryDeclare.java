/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.util.UUID;

import fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor;

/**
 * @author Tristan
 *
 */
public interface PluginHistoryDeclare extends PluginDescriptor<PluginHistory> {
	/* *
	 * ID unique au plugin.
	 * Permet la reconnaissance du plugin pour les histoires.
	 * @return plugin's id
	 */
	//UUID getCompilerID();

}
