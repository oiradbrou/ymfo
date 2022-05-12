package exceptions;

import java.io.IOException;

/**
 * {@link IOException} that is thrown when the resource provided
 * to a method is supposed to be of some type, but actually isn't.
 * 
 * @author raflat
 */
class NotResourceException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates a {@link NotResourceException} with a
	 * message that informs the user that a certain type of resource was 
	 * provided instead of another.
	 * 
	 * @param 
	 * resourceName - String that represents the name of the expected resource.
	 */
	NotResourceException(String resourceName) {
		super("Ensure that a " + resourceName + 
			  " was provided and not another type of resource.");
	}
	
}
