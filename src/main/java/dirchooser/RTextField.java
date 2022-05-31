package dirchooser;

import javax.swing.JTextField;

public final class RTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	
	public static RTextField create() {
		return new RTextField();
	}

	public void configure() {
		CIni ini = CIni.create();
				
		setColumns(Integer.parseInt(ini.readFrom("TextField", "Length")));
		setEditable(false);
		setFocusable(false);
	}
	
	private RTextField() {}
	
}
