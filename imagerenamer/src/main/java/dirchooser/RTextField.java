package dirchooser;

import java.awt.TextField;

public final class RTextField extends TextField {

	private static final long serialVersionUID = 1L;
	
	public static RTextField create() {
		return new RTextField();
	}

	public void configure() {
		RIni ini = RIni.create();
				
		setColumns(Integer.parseInt(ini.readFrom("TextField", "Length")));
		setEditable(false);
		setFocusable(false);
	}
	
	private RTextField() {}
	
}
