package dirchooser;

import java.awt.Color;

import javax.swing.JLabel;

public final class CLabel extends JLabel implements Configurable {

	private static final long serialVersionUID = 1L;

	public static CLabel withText(String text) {
		return new CLabel(text);
	}

	@Override
	public void configure(CIni iniFile) {
		setForeground(Color.decode(iniFile.readFrom("Label", "TextColor")));		
	}
	
	private CLabel(String text) {
		super(text);
	}

}
