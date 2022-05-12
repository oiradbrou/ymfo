package exceptions;

/**
 * {@link NotResourceException} that is thrown when the resource provided
 * to a method was supposed to be a file, but wasn't.
 * 
 * @author raflat
 */
public final class NotFileException extends NotResourceException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates a {@link NotFileException} with
	 * the message that informs the user that a certain type of resource was 
	 * provided instead of a file.
	 */
	public NotFileException() {
		super("file");
	}

}
