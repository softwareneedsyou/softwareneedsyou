/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.util.Map;
import java.util.UUID;

import org.kohsuke.MetaInfServices;

import fr.esgi.projet.softwareneedsyou.api.spi.InitialisationException;
import lombok.NonNull;

/**
 * @author Tristan
 *
 */
@MetaInfServices
public class FileHistoryPluginDeclare implements PluginHistoryDeclare {

	/**
	 * 
	 */
	public FileHistoryPluginDeclare() {
	}

	private final static UUID uuid = UUID.fromString("dbc3236a-a16b-46b5-89f6-e7823f761dcd");
	/* (non-Javadoc)
	 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor#getID()
	 */
	@Override
	public UUID getID() {
		return uuid;
	}

	/* (non-Javadoc)
	 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor#getName()
	 */
	@Override
	public String getName() {
		return FileHistoryPlugin.class.getSimpleName();
	}

	/* (non-Javadoc)
	 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor#getResume()
	 */
	@Override
	public String getResume() {
		return "Simple histories loader";
	}

	/* (non-Javadoc)
	 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Load histories files from application folder.";
	}

	/* (non-Javadoc)
	 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor#getInstance(java.util.Map)
	 */
	@Override
	public PluginHistory getInstance(@NonNull final Map<String, Object> params) throws InitialisationException {
		return new FileHistoryPlugin();
	}

	/* (non-Javadoc)
	 * @see fr.esgi.projet.softwareneedsyou.api.history.PluginHistoryDeclare#getCompilerID()
	 */
	/*@Override
	public UUID getCompilerID() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
