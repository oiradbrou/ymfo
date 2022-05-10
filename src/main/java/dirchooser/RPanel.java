package dirchooser;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public final class RPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static RPanel create() {
		return new RPanel();
	}
	
	public void configure() {
		RIni ini = RIni.create();
		
		setLayout(new GridBagLayout());
		setBackground(Color.decode(ini.readFrom("Panel", "Color")));
	}
	
	private RPanel() {}
	
}
