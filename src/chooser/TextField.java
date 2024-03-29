package chooser;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Extension of a {@link JPanel} that allows the creation of an instance with default length, nor editable and
 * nor focusable.
 */
final class TextField extends JTextField {

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new {@link TextField} object.
	 *
	 * @return New TextField instance.
	 */
	static TextField create() {
		return new TextField();
	}
	
	/**
	 * Returns a new {@link TextField} object with twenty columns, not editable and not focusable.
	 */
	private TextField() {
		setColumns(Integer.parseInt("20"));
		setEditable(false);
		setFocusable(false);
	}

}
