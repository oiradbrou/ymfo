package dirchooser;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * Extension of a {@link JLabel} that allows the creation of an instance with custom text, while setting the
 * color of it to a default value.
 */
final class Label extends JLabel {

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns a new {@link Label} object with white text.
	 *
	 * @param text - {@link String} of text.
	 */
	private Label(String text) {
		super(text);
		setForeground(Color.decode("#FFFFFF"));
	}

	/**
	 * Create a new {@link Label} object displaying the text provided.
	 *
	 * @param text - {@link String} of text.
	 *
	 * @return New {@link Label} instance.
	 */
	static Label withText(String text) {
		return new Label(text);
	}

}
