package dirchooser;

import javax.swing.JTextField;

/**
 * Class that extends the {@link JTextField} type by providing it with a method
 * to configure it according to a {@link CIni} configuration file; this method
 * is provided by the interface {@link Configurable}.
 */
final class TextField extends JTextField implements Configurable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Static factory method to create a {@link TextField} object.
	 *
	 * @return New TextField instance.
	 */
	static TextField create() {
		return new TextField();
	}
	
	/**
	 * Method that configures the instance of {@link TextField} is called on.
	 * The configuration is taken from the {@link CIni} file provided.
	 */
	@Override
	public void configure(CIni iniFile) {
		setColumns(Integer.parseInt(iniFile.readFrom("TextField", "Length")));
		setEditable(false);
		setFocusable(false);
	}
	
}
