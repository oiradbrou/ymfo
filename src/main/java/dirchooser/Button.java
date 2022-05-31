package dirchooser;

import java.awt.Color;

import javax.swing.JButton;

final class Button extends JButton implements Configurable {

	private static final long serialVersionUID = 1L;

	static Button withText(String text) {
		return new Button(text);
	}
	
	@Override
	public void configure(CIni iniFile) {
		setBackground(Color.decode(iniFile.readFrom("Button", "BackgroundColor")));
		setForeground(Color.decode(iniFile.readFrom("Label", "TextColor")));
		setFocusable(false);	
	}
	
	private Button(String text) {
		super(text);
	}
	
}
