package dirchooser;

import java.awt.Component;
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

	private final YMOrganizer organizer;
	
	GridBagConstraints  contrains = new GridBagConstraints();

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
		createComponents();
		addButtonsToActionListener();

		CIni iniFile = CIni.createFromIni("src\\main\\resources\\conf.ini");
		customizeComponents(iniFile);

		attachComponents();

		this.pack();
		this.setVisible(true);
	}
	
	private void createComponents() {
		panel = Panel.create();
		srcLabel = Label.withText("Source folder:");
		destLabel = Label.withText("Destination folder:");
		srcTextField = TextField.create();
	  	destTextField = TextField.create();
	  	srcButton = Button.withText("Browse");
		destButton = Button.withText("Browse");
		runButton = Button.withText("Run");
	}

	private void addButtonsToActionListener() {
		srcButton.addActionListener(this);
		destButton.addActionListener(this);
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
		contrains.insets = new Insets(10, 10, 10, 10);
		addXYConstrained(0, 0, srcLabel);
		addXYConstrained(1, 0, srcTextField);
		addXYConstrained(2, 0, srcButton);
		addXYConstrained(0, 1, destLabel);
		addXYConstrained(1, 1, destTextField);
		addXYConstrained(2, 1, destButton);
		contrains.fill = GridBagConstraints.HORIZONTAL;
		addXYConstrained(1, 3, runButton);
	}
	
	private void addXYConstrained(int gridX, int gridY, Component component) {
		contrains.gridx = gridX;
		contrains.gridy = gridY;
		panel.add(component, contrains);
	}

}
