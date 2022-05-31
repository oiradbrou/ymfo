package dirchooser;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

final class Panel extends JPanel implements Configurable {

	private static final long serialVersionUID = 1L;

	static Panel create() {
		return new Panel();
	}
	
	@Override
	public void configure(CIni iniFile) {		
		setLayout(new GridBagLayout());
		setBackground(Color.decode(iniFile.readFrom("Panel", "Color")));
	}
	
	private Panel() {}
	
}
