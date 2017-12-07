package ops.inventory.exception;

/**
 * Exception class to help wrap any checked exception and also to add any custom messaing to exceptions.
 * 
 */
public class ExternalClientException extends RuntimeException {

	private static final long serialVersionUID = 8094430472238528772L;

	public ExternalClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExternalClientException(String message) {
		super(message);
	}

}
