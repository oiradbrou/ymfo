package exceptions;

import java.io.IOException;

/**
 * {@link IOException} that is thrown when the resource provided
 * to a method is supposed to be a directory, but actually is a file.
 * 
 * @author raflat
 */
public final class NotDirectoryException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates a {@link NotDirectoryException} with a fixed
	 * message that informs the user that a file was provided
	 * instead of a directory.
	 */
	public NotDirectoryException() {
		super("Ensure that a directory was provided and not a file");
	}

}
