package kr.co.adflow.nms.web.exception;

/**
 * This exception indicates an error has occurred validating check from nmsweb.
 * 
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 */
	public ValidationException() {
		super();
	}

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 */
	public ValidationException(String message) {
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
	public ValidationException(String message, Throwable cause) {
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
	public ValidationException(Throwable cause) {
		super(cause);
	}

}
