/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.compiler;

/**
 * @author Tristan
 *
 */
public class CompilerException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6775620794509556182L;

	/**
	 * 
	 */
	public CompilerException() {
		super();
	}

	/**
	 * @param message
	 */
	public CompilerException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CompilerException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CompilerException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CompilerException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
