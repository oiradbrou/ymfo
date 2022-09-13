package dirchooser;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Class that extends the {@link JButton} type by providing it with a method to
 * configure it according to a {@link CIni} configuration file; this method is
 * provided by the interface {@link Configurable}.
 */
final class Button extends JButton {

	private static final long serialVersionUID = 1L;

	/**
	 * Static factory method to create a {@link Button} object displaying a
	 * given text.
	 *
	 * @param text - {@link String} of text.
	 * @return New Button instance.
	 */
	static Button withText(String text) {
		return new Button(text);
	}

	private Button(String text) {
		super(text);

		setBackground(Color.decode("#808080"));
		setForeground(Color.decode("#FFFFFF"));
		setFocusable(false);
	}

}
