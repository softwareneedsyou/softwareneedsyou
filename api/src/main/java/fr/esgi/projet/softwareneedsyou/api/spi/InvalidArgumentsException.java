/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.spi;

/**
 * @author Tristan
 *
 */
public class InvalidArgumentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4509422726487235490L;

	/**
	 * 
	 */
	public InvalidArgumentsException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidArgumentsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidArgumentsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidArgumentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
