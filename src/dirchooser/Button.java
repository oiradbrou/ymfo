package dirchooser;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Extension of a {@link JButton} that allows the creation of an instance with custom text, while setting the
 * color of both text and background to default values. Also, the button is created as not focusable.
 */
final class Button extends JButton {

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns a new {@link Button} object with light grey background and white text. Also, the button is
	 * created as not focusable and shows the text provided.
	 *
	 * @param text - {@link String} of text.
	 */
	private Button(String text) {
		super(text);
		setBackground(Color.decode("#808080"));
		setFocusable(false);
		setForeground(Color.decode("#FFFFFF"));
	}

	/**
	 * Create a new {@link Button} object displaying the text provided.
	 *
	 * @param text - {@link String} of text.
	 *
	 * @return New {@link Button} instance.
	 */
	static Button withText(String text) {
		return new Button(text);
	}

}
