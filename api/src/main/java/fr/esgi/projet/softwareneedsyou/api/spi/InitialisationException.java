/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.spi;

/**
 * @author Tristan
 *
 */
public class InitialisationException extends PluginException {
	private static final long serialVersionUID = 8792679847062163549L;

	/**
	 * 
	 */
	public InitialisationException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public InitialisationException(final String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public InitialisationException(final Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InitialisationException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public InitialisationException(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
