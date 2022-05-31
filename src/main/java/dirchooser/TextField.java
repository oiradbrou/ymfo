package dirchooser;

import javax.swing.JTextField;

final class TextField extends JTextField implements Configurable {

	private static final long serialVersionUID = 1L;
	
	static TextField create() {
		return new TextField();
	}
	
	@Override
	public void configure(CIni iniFile) {
		setColumns(Integer.parseInt(iniFile.readFrom("TextField", "Length")));
		setEditable(false);
		setFocusable(false);
	}
	
	private TextField() {}
	
}
