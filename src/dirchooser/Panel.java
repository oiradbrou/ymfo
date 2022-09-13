package dirchooser;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * Extension of a {@link JPanel} that allows the creation of an instance with a default colored background and
 * {@link GridBagLayout} layout.
 */
final class Panel extends JPanel {

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns a new {@link Panel} object with dark grey background and {@link GridBagLayout} layout.
	 *
	 * @param text - {@link String} of text.
	 */
	private Panel() {
		setBackground(Color.decode("#343434"));
		setLayout(new GridBagLayout());
	}

	/**
	 * Create a new {@link Panel} object.
	 *
	 * @return New {@link Panel} instance.
	 */
	static Panel create() {
		return new Panel();
	}

}
