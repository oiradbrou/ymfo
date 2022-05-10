<<<<<<< HEAD
package dirchooser;

import java.awt.Color;

import javax.swing.JLabel;

public final class RLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public static RLabel withText(String text) {
		return new RLabel(text);
	}
	
	public void configure() {
		RIni ini = RIni.create();
		
		setForeground(Color.decode(ini.readFrom("Label", "TextColor")));
	}
	
	private RLabel(String text) {
		super(text);
	}

}
=======
package dirchooser;

import java.awt.Color;

import javax.swing.JLabel;

public final class RLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public static RLabel withText(String text) {
		return new RLabel(text);
	}
	
	public void configure() {
		RIni ini = RIni.create();
		
		setForeground(Color.decode(ini.readFrom("Label", "TextColor")));
	}
	
	private RLabel(String text) {
		super(text);
	}

}
>>>>>>> 26ef2a4 (Project added)
