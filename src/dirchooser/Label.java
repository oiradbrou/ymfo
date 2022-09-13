package dirchooser;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * Class that extends the {@link JLabel} type by providing it with a method to
 * configure it according to a {@link CIni} configuration file; this method is
 * provided by the interface {@link Configurable}.
 */
final class Label extends JLabel {

	private static final long serialVersionUID = 1L;

	/**
	 * Static factory method to create a {@link Label} object displaying a
	 * given text.
	 *
	 * @param text - {@link String} of text.
	 * @return New Label instance.
	 */
	static Label withText(String text) {
		return new Label(text);
	}

	private Label(String text) {
		super(text);
		
		setForeground(Color.decode("#FFFFFF"));
	}

}
