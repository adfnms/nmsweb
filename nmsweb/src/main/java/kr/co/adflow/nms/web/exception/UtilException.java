package kr.co.adflow.nms.web.exception;

/**
 * This exception indicates an error has occurred handling data from opennms
 * rest service
 */
public class UtilException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Constructor for UtilException.
	 * </p>
	 */
	public UtilException() {
		super();
	}

	/**
	 * <p>
	 * Constructor for UtilException.
	 * </p>
	 */
	public UtilException(String message) {
		super(message);
	}

	/**
	 * <p>
	 * Constructor for UtilException.
	 * </p>
	 * 
	 * @param message
	 *            a {@link java.lang.String} object.
	 * @param cause
	 *            a {@link java.lang.Throwable} object.
	 */
	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <p>
	 * Constructor for UtilException.
	 * </p>
	 * 
	 * @param cause
	 *            a {@link java.lang.Throwable} object.
	 */
	public UtilException(Throwable cause) {
		super(cause);
	}

}
