/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.spi;

/**
 * Exception se déclarant lors de l'initialisation d'un plugin.
 * 
 * @author Tristan
 * @see PluginException
 */
public class InitialisationException extends PluginException {
	private static final long serialVersionUID = 8792679847062163549L;

	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
	 */
	public InitialisationException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.<br>
	 *   The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
	 *
	 * @param   message   the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
	 */
	public InitialisationException(final String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message of <tt>(cause==null ? null : cause.toString())</tt>
	 * (which typically contains the class and detail message of <tt>cause</tt>).<br>
	 * This constructor is useful for exceptions that are little more than wrappers for other throwables
	 * (for example, {@link java.security.PrivilegedActionException}).
	 *
	 * @param  cause the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public InitialisationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * <p>Note that the detail message associated with {@code cause} is <i>not</i> automatically incorporated in
	 * this exception's detail message.
	 *
	 * @param  message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
	 * @param  cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
	 *           (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public InitialisationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified detail message, cause, suppression enabled or disabled,
	 * and writable stack trace enabled or disabled.
	 *
	 * @param  message the detail message.
	 * @param cause the cause.  (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
	 * @param enableSuppression whether or not suppression is enabled or disabled
	 * @param writableStackTrace whether or not the stack trace should be writable
	 */
	public InitialisationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
