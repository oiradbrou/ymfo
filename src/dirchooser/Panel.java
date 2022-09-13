package dirchooser;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * Class that extends the {@link JPanel} type by providing it with a method to
 * configure it according to a {@link CIni} configuration file; this method is
 * provided by the interface {@link Configurable}.
 */
final class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Static factory method to create a {@link Panel} object.
	 *
	 * @return New Panel instance.
	 */
	static Panel create() {
		return new Panel();
	}
	
	private Panel() {
		setLayout(new GridBagLayout());
		setBackground(Color.decode("#343434"));
	}

}
