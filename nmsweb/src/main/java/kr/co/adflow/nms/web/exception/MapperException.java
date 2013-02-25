package kr.co.adflow.nms.web.exception;

/**
 * This exception indicates an error has occurred mapping data from nmsweb.
 */
public class MapperException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 */
	public MapperException() {
		super();
	}

	/**
	 * <p>
	 * Constructor for HandleException.
	 * </p>
	 */
	public MapperException(String message) {
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
	public MapperException(String message, Throwable cause) {
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
	public MapperException(Throwable cause) {
		super(cause);
	}

}
