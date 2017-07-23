/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.util.Collection;

import fr.esgi.projet.softwareneedsyou.api.spi.Plugin;

/**
 * @author Tristan
 *
 */
public interface PluginHistory extends Plugin {
	/**
	 * Liste des niveaux.
	 * Il est conseiller que la collection soit immutable.
	 * @return Liste des niveaux
	 */
	Collection<History> getHistories();
}
