package exceptions;

/**
 * {@link NotResourceException} that is thrown when the resource provided
 * to a method was supposed to be a directory, but wasn't.
 * 
 * @author raflat
 */
public final class NotDirectoryException extends NotResourceException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates a {@link NotDirectoryException} with
	 * the message that informs the user that a certain type of resource was 
	 * provided instead of a directory.
	 */
	public NotDirectoryException() {
		super("directory");
	}

}
