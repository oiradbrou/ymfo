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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import organizer.YMOrganizer;

public final class DirChooser extends JFrame implements ActionListener {

	/**
	 * Interface components.
	 */
	private Button srcButton, destButton, runButton;
	private Label srcLabel, destLabel;
	private Panel panel;
	private TextField srcTextField, destTextField;

	/**
	 * Defines how to organize the files in the destination directory, that is in a <b>year/month</b>
	 * structure.
	 */
	private final YMOrganizer organizer;
	
	
	/**
	 * Imposes a {@link GridBagConstraints} constraint to the components.
	 */
	private static GridBagConstraints contrains = new GridBagConstraints();

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;

	
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

				checkPressedDirButtons(pressedButton, choosedDirPath);
			}
		}
	}

	private void checkPressedDirButtons(Object pressedButton, String choosedDirPath) {
		if (pressedButton == srcButton) {
			try {
				organizer.setSrcDir(choosedDirPath);
			} catch (NotDirectoryException e1) {
				e1.printStackTrace();
			}
			srcTextField.setText(choosedDirPath);
		} else if (pressedButton == destButton) {
			try {
				organizer.setDestDir(choosedDirPath);
			} catch (NotDirectoryException e1) {
				e1.printStackTrace();
			}
			destTextField.setText(choosedDirPath);
		}
	}

	private DirChooser(YMOrganizer organizer) {
		this.organizer = organizer;		
		
		initFrame();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initFrame() {
		createComponents();
		addButtonsToActionListener();
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Image Renamer");

		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		
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

	private void attachComponents() {
		contrains.insets = new Insets(10, 10, 10, 10);

		addToPanelXYConstrained(0, 0, srcLabel);
		addToPanelXYConstrained(1, 0, srcTextField);
		addToPanelXYConstrained(2, 0, srcButton);
		addToPanelXYConstrained(0, 1, destLabel);
		addToPanelXYConstrained(1, 1, destTextField);
		addToPanelXYConstrained(2, 1, destButton);
		contrains.fill = GridBagConstraints.HORIZONTAL;
		addToPanelXYConstrained(1, 3, runButton);
	}

	private void addToPanelXYConstrained(int gridX, int gridY, Component component) {
		contrains.gridx = gridX;
		contrains.gridy = gridY;
		panel.add(component, contrains);
	}

}
