package dirchooser;

import javax.swing.JTextField;

/**
 * Class that extends the {@link JTextField} type by providing it with a method
 * to configure it according to a {@link CIni} configuration file; this method
 * is provided by the interface {@link Configurable}.
 */
final class TextField extends JTextField{

	private static final long serialVersionUID = 1L;

	/**
	 * Static factory method to create a {@link TextField} object.
	 *
	 * @return New TextField instance.
	 */
	static TextField create() {
		return new TextField();
	}
	
	private TextField() {
		setColumns(Integer.parseInt("20"));
		setEditable(false);
		setFocusable(false);
	}

}
