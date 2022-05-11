package dirchooser;

import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import organizer.Organizer;

public final class DirChooser extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
		
	private RPanel panel;

	private RLabel srcLabel;
	private RLabel destLabel;
	
	private RTextField srcTextField;
	private RTextField destTextField;
	
	private RButton srcButton;
	private RButton destButton;
	private RButton runButton;
	
	private Organizer organizer;
	
	public static DirChooser chooseAndOrganizeWith(Organizer organizer) {
		return new DirChooser(organizer);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object pressedButton = e.getSource();
		
		if (pressedButton == runButton)
			organizer.renameImages();
		else {
			JFileChooser dirChooser = new JFileChooser();
			dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (dirChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String choosedDirPath = dirChooser.getSelectedFile().getAbsolutePath();

				if (pressedButton == srcButton) {
					organizer.setSrcDir(choosedDirPath);
					srcTextField.setText(choosedDirPath);
				}
				else if (pressedButton == destButton) {
					organizer.setDestDir(choosedDirPath);
					destTextField.setText(choosedDirPath);
				}
			}
		}
	}
	
	private DirChooser(Organizer organizer) {
		this.organizer = organizer;
		initComponents();
	}
	
	private void initComponents () {
		initPanel();
		initFrame();
		initLabels();
	  	initTextFields();
	  	initButtons();
		
		GridBagConstraints  contrains = new GridBagConstraints();
		contrains.insets = new Insets(10, 10, 10, 10);
		contrains.gridx = 0;
		contrains.gridy = 0;
		panel.add(srcLabel, contrains);
		contrains.gridx = 1;
		contrains.gridy = 0;
		panel.add(srcTextField, contrains);
		contrains.gridx = 2;
		contrains.gridy = 0;
		panel.add(srcButton, contrains);
		contrains.gridx = 0;
		contrains.gridy = 1;
		panel.add(destLabel, contrains);
		contrains.gridx = 1;
		contrains.gridy = 1;
		panel.add(destTextField, contrains);
		contrains.gridx = 2;
		contrains.gridy = 1;
		panel.add(destButton, contrains);
		contrains.gridx = 1;
		contrains.gridy = 3;
		contrains.fill = GridBagConstraints.HORIZONTAL;
		panel.add(runButton, contrains);
		
		this.pack();
		this.setVisible(true);
	}
	
	private void initPanel() {
		panel = RPanel.create();
		panel.configure();
	}
	
	private void initFrame() {
		RIni ini = RIni.create();
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(ini.readFrom("Frame", "Title"));
		
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
	}
	
	private void initLabels() {
		srcLabel = RLabel.withText("Source folder:");
		srcLabel.configure();
		
		destLabel = RLabel.withText("Destination folder:");
		destLabel.configure();
	}
	
	private void initTextFields() {
		srcTextField = RTextField.create();
	  	srcTextField.configure();
	  	
	  	destTextField = RTextField.create();
	  	destTextField.configure();
	}
	
	private void initButtons() {
		srcButton = RButton.withText("Browse");
		srcButton.configure();
		srcButton.addActionListener(this);
	
		destButton = RButton.withText("Browse");
		destButton.configure();
		destButton.addActionListener(this);
		
		runButton = RButton.withText("Run");
		runButton.configure();
		runButton.addActionListener(this);
	}
		
}