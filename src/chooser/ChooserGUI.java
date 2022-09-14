package chooser;

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

public final class ChooserGUI extends JFrame implements ActionListener {

	/**
	 * Interface components.
	 */
	private Button sourceButton, destinationButton, runButton;
	private Label sourceLabel, destinationLabel;
	private Panel panel;
	private TextField sourceTextField, destinationTextField;

	/**
	 * Defines how to organize the files in the destination Directory, that is in a <b>year/month</b>
	 * structure.
	 */
	private final YMOrganizer organizer = new YMOrganizer();
	
	/**
	 * Imposes a {@link GridBagConstraints} constraint to the components.
	 */
	private static GridBagConstraints contrains = new GridBagConstraints();

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * When an action occurs, checks if a button is pressed. If it's <b>Run</b>, organizes the destination
	 * Directory. Alternatively, checks if it's one of the <b>Browse</b> ones. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object pressedButton = e.getSource();

		if (pressedButton == runButton)
			organizer.organize();
		else {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String chosenPath = chooser.getSelectedFile().getAbsolutePath();

				checkPressedDirectoryButtons(pressedButton, chosenPath);
			}
		}
	}
	
	/**
	 * Initiates the interface.
	 * 
	 * @return New {@link ChooserGUI} instance.
	 */
	public static void run() {
		new ChooserGUI();
	}

	private void checkPressedDirectoryButtons(Object pressedButton, String choosedDirectoryPath) {
		if (pressedButton == sourceButton) {
			try {
				organizer.setSource(choosedDirectoryPath);
			} catch (NotDirectoryException e1) {
				e1.printStackTrace();
			}
			sourceTextField.setText(choosedDirectoryPath);
		} else if (pressedButton == destinationButton) {
			try {
				organizer.setDestination(choosedDirectoryPath);
			} catch (NotDirectoryException e1) {
				e1.printStackTrace();
			}
			destinationTextField.setText(choosedDirectoryPath);
		}
	}

	private ChooserGUI() {		
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
		sourceLabel = Label.withText("Source folder:");
		destinationLabel = Label.withText("destinationination folder:");
		sourceTextField = TextField.create();
	  	destinationTextField = TextField.create();
	  	sourceButton = Button.withText("Browse");
		destinationButton = Button.withText("Browse");
		runButton = Button.withText("Run");
	}

	private void addButtonsToActionListener() {
		sourceButton.addActionListener(this);
		destinationButton.addActionListener(this);
		runButton.addActionListener(this);
	}

	private void attachComponents() {
		contrains.insets = new Insets(10, 10, 10, 10);

		addToPanelXYConstrained(0, 0, sourceLabel);
		addToPanelXYConstrained(1, 0, sourceTextField);
		addToPanelXYConstrained(2, 0, sourceButton);
		addToPanelXYConstrained(0, 1, destinationLabel);
		addToPanelXYConstrained(1, 1, destinationTextField);
		addToPanelXYConstrained(2, 1, destinationButton);
		contrains.fill = GridBagConstraints.HORIZONTAL;
		addToPanelXYConstrained(1, 3, runButton);
	}

	private void addToPanelXYConstrained(int gridX, int gridY, Component component) {
		contrains.gridx = gridX;
		contrains.gridy = gridY;
		panel.add(component, contrains);
	}

}
