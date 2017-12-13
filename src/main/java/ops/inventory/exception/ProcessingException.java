
package ops.inventory.exception;

/**
 * Exception class for any exception that occur while processing.
 *
 */
public class ProcessingException extends RuntimeException {
	
  private static final long serialVersionUID = 9102231833233901088L;

	public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
