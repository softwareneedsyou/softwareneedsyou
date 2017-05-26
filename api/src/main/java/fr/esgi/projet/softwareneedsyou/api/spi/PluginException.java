/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.spi;

/**
 * @author Blixel
 *
 */
public class PluginException extends Exception {
	private static final long serialVersionUID = -4398686539120079630L;

	/**
	 * 
	 */
	public PluginException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public PluginException(final String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public PluginException(final Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PluginException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public PluginException(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
