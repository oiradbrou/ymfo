package exceptions;

import java.io.IOException;

/**
 * {@link IOException} that is thrown when the resource provided
 * isn't a file, but a directory.
 * 
 * @author raflat
 */
public class NotFileException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates a {@link NotFileException} with a fixed
	 * message that informs the user that a directory was provided
	 * instead of a file.
	 */
	public NotFileException() {
		super("Ensure that a file was provided and not a directory");
	}

}
