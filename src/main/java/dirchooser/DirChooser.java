package dirchooser;

import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.NotDirectoryException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import organizer.YMOrganizer;

public final class DirChooser extends JFrame implements ActionListener, Configurable {

	private static final long serialVersionUID = 1L;

	private Panel panel;

	private Label srcLabel;
	private Label destLabel;

	private TextField srcTextField;
	private TextField destTextField;

	private Button srcButton;
	private Button destButton;
	private Button runButton;

	private YMOrganizer organizer;

	public static DirChooser chooseAndOrganizeWith(YMOrganizer organizer) {
		return new DirChooser(organizer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object pressedButton = e.getSource();

		if (pressedButton == runButton)
			organizer.organize();
		else {
			JFileChooser dirChooser = new JFileChooser();
			dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (dirChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String choosedDirPath = dirChooser.getSelectedFile().getAbsolutePath();

				if (pressedButton == srcButton) {
					try {
						organizer.setSrcDir(choosedDirPath);
					} catch (NotDirectoryException e1) {
						e1.printStackTrace();
					}
					srcTextField.setText(choosedDirPath);
				}
				else if (pressedButton == destButton) {
					try {
						organizer.setDestDir(choosedDirPath);
					} catch (NotDirectoryException e1) {
						e1.printStackTrace();
					}
					destTextField.setText(choosedDirPath);
				}
			}
		}
	}

	@Override
	public void configure(CIni iniFile) {
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(iniFile.readFrom("Frame", "Title"));

		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
	}

	private DirChooser(YMOrganizer organizer) {
		this.organizer = organizer;
		initFrame();
	}

	private void initFrame() {
		initPanel();
		initLabels();
		initTextFields();
		initButtons();

		CIni iniFile = CIni.createFromIni("src\\main\\resources\\conf.ini");
		customizeComponents(iniFile);

		attachComponents();

		this.pack();
		this.setVisible(true);
	}

	private void initPanel() {
		panel = Panel.create();
	}

	private void initLabels() {
		srcLabel = Label.withText("Source folder:");
		destLabel = Label.withText("Destination folder:");
	}

	private void initTextFields() {
		srcTextField = TextField.create();
	  	destTextField = TextField.create();
	}

	private void initButtons() {
		srcButton = Button.withText("Browse");
		srcButton.addActionListener(this);

		destButton = Button.withText("Browse");
		destButton.addActionListener(this);

		runButton = Button.withText("Run");
		runButton.addActionListener(this);
	}

	private void customizeComponents(CIni iniFile) {
		panel.configure(iniFile);
		this.configure(iniFile);
		srcLabel.configure(iniFile);
		destLabel.configure(iniFile);
		srcTextField.configure(iniFile);
		destTextField.configure(iniFile);
		srcButton.configure(iniFile);
		destButton.configure(iniFile);
		runButton.configure(iniFile);
	}

	private void attachComponents() {
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
	}

}
