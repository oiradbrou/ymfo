package dirchooser;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import imagerenamer.ImageRenamer;

public final class DirChooser extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Ini confFile;
	
	private JButton srcButton;
	private JButton destButton;
	private JButton runButton;
	
	private TextField srcTextField;
	private TextField destTextField;
	
	private ImageRenamer renamer;
	
	public DirChooser(ImageRenamer renamer) {
		this.renamer = renamer;
		initComponents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object pressedButton = e.getSource();
		
		if (pressedButton == runButton)
			renamer.renameImages();
		else {
			JFileChooser dirChooser = new JFileChooser();
			dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (dirChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String choosedDirPath = dirChooser.getSelectedFile().getAbsolutePath();

				if (pressedButton == srcButton) {
					renamer.setSrcDir(choosedDirPath);
					srcTextField.setText(choosedDirPath);
				}
				else if (pressedButton == destButton) {
					renamer.setDestDir(choosedDirPath);
					destTextField.setText(choosedDirPath);
				}
			}
		}
	}
	
	private void initComponents () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		try {
			confFile = new Ini(new File("src\\main\\resources\\conf.ini"));
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setTitle(getWindowConfig("Title"));
		this.setVisible(true);
		this.setIconImage(new ImageIcon(getWindowConfig("IconPath")).getImage());
		this.getContentPane().setBackground(decodeColor(getWindowConfig("BackgroundColor")));
		this.setSize(Integer.parseInt(getWindowConfig("Width")), Integer.parseInt(getWindowConfig("Height")));
		
		srcButton = new JButton(getButtonsConfig("SrcChooserText"));
		srcButton.setForeground(decodeColor(getButtonsConfig("TextColor")));
		srcButton.setBackground(decodeColor(getButtonsConfig("BackgroundColor")));
		srcButton.addActionListener(this);
		this.add(srcButton);
		
		destButton = new JButton(getButtonsConfig("DestChooserText"));
		destButton.setForeground(decodeColor(getButtonsConfig("TextColor")));
		destButton.setBackground(decodeColor(getButtonsConfig("BackgroundColor")));
		destButton.addActionListener(this);
		this.add(destButton);
		
		srcTextField = new TextField();
		this.add(srcTextField);
		
		destTextField = new TextField();
		this.add(destTextField);
		
		runButton = new JButton("Run");
		runButton.setForeground(decodeColor(getButtonsConfig("TextColor")));
		runButton.setBackground(decodeColor(getButtonsConfig("BackgroundColor")));
		runButton.addActionListener(this);
		this.add(runButton);
		
		this.validate();
	}
	
	private String getWindowConfig(String field) {
		return confFile.get("Window", field);
	}
	
	private String getButtonsConfig(String field) {
		return confFile.get("Buttons", field);
	}
	
	private Color decodeColor(String hexColor) {
		return Color.decode(hexColor);
	}
		
}
