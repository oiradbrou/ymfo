package dirchooser;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public final class CPanel extends JPanel implements Configurable {

	private static final long serialVersionUID = 1L;

	public static CPanel create() {
		return new CPanel();
	}
	
	@Override
	public void configure(CIni iniFile) {		
		setLayout(new GridBagLayout());
		setBackground(Color.decode(iniFile.readFrom("Panel", "Color")));
	}
	
	private CPanel() {}
	
}
