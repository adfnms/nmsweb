package kr.co.adflow.nms.web.exception;

/**
 * This exception indicates an error has occurred handling data from opennms
 * rest service
 */
public class HandleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 */
	public HandleException() {
		super();
	}

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 */
	public HandleException(String message) {
		super(message);
	}

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 * 
	 * @param message
	 *            a {@link java.lang.String} object.
	 * @param cause
	 *            a {@link java.lang.Throwable} object.
	 */
	public HandleException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 * 
	 * @param cause
	 *            a {@link java.lang.Throwable} object.
	 */
	public HandleException(Throwable cause) {
		super(cause);
	}

}
