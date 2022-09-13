package dirchooser;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Extension of a {@link JButton} that sets the object's background and text to a default color. It also
 * provides a method to change the button's text.
 */
final class Button extends JButton {

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;
	
	private Button(String text) {
		super(text);

		setBackground(Color.decode("#808080"));
		setForeground(Color.decode("#FFFFFF"));
		setFocusable(false);
	}

	/**
	 * Create a {@link Button} object displaying the text provided.
	 *
	 * @param text - {@link String} of text.
	 * 
	 * @return New Button instance.
	 */
	static Button withText(String text) {
		return new Button(text);
	}

}
