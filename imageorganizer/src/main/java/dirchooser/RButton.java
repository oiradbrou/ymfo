<<<<<<< HEAD
package dirchooser;

import java.awt.Color;

import javax.swing.JButton;

public final class RButton extends JButton {

	private static final long serialVersionUID = 1L;

	public static RButton withText(String text) {
		return new RButton(text);
	}
	
	public void configure() {
		RIni ini = RIni.create();
		
		setBackground(Color.decode(ini.readFrom("Button", "BackgroundColor")));
		setForeground(Color.decode(ini.readFrom("Label", "TextColor")));
		setFocusable(false);
	}
	
	private RButton(String text) {
		super(text);
	}
	
}
=======
package dirchooser;

import java.awt.Color;

import javax.swing.JButton;

public final class RButton extends JButton {

	private static final long serialVersionUID = 1L;

	public static RButton withText(String text) {
		return new RButton(text);
	}
	
	public void configure() {
		RIni ini = RIni.create();
		
		setBackground(Color.decode(ini.readFrom("Button", "BackgroundColor")));
		setForeground(Color.decode(ini.readFrom("Label", "TextColor")));
		setFocusable(false);
	}
	
	private RButton(String text) {
		super(text);
	}
	
}
>>>>>>> 26ef2a4 (Project added)
