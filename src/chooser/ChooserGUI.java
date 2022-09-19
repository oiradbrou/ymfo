package chooser;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

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
	 * Imposes a {@link GridBagConstraints} constraint to the components.
	 */
	private static GridBagConstraints contrains = new GridBagConstraints();

	/**
	 * Default serial version ID to avoid warnings.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Defines how to organize the files in the destination Directory, that is in a <b>year/month</b>
	 * structure.
	 */
	private final YMOrganizer organizer = new YMOrganizer();

	/**
	 * When an action occurs, checks if a button is pressed. If it's <b>Run</b>, organizes the destination
	 * Directory. Alternatively, checks if it's one of the <b>Browse</b> ones.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object pressedButton = event.getSource();

		if (pressedButton == runButton)
			organizer.organize();
		else {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String chosenPath = chooser.getSelectedFile().getAbsolutePath();
				checkBrowsePressed(pressedButton, chosenPath);
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

	/**
	 * Checks whether a <b>Browse</b> buttons was pressed. If so, it sets the organizer's associated
	 * directory to the one located at the chosen path.
	 *
	 * @param pressedButton - button that has been pressed.
	 * @param path - path of the chosen directory.
	 */
	private void checkBrowsePressed(Object pressedButton, String path) {
		if (pressedButton == sourceButton) {
			organizer.setSource(path);
			sourceTextField.setText(path);
		} else {
			organizer.setDestination(path);
			destinationTextField.setText(path);
		}
	}

	/**
	 * Adds <b>Browse</b> and <b>Run</b> to {@link ActionListener}, so that they can trigger a reaction when
	 * pressed.
	 */
	private void addButtons() {
		sourceButton.addActionListener(this);
		destinationButton.addActionListener(this);
		runButton.addActionListener(this);
	}
	
	/**
	 * Adds a component to the panel in position (x,y).
	 * 
	 * @param component - component to add.
	 * @param x - horizontal position.
	 * @param y - vertical position.
	 */
	private void addConstrained(Component component, int x, int y) {
		contrains.gridx = x;
		contrains.gridy = y;
		panel.add(component, contrains);
	}
	
	/**
	 * Adds all the buttons and components to the interface.
	 */
	private void addComponents() {
		add(panel);
		addButtons();
		
		contrains.insets = new Insets(10, 10, 10, 10);
		
		addConstrained(sourceLabel, 0, 0);
		addConstrained(sourceTextField, 1, 0);
		addConstrained(sourceButton, 2, 0);
		addConstrained(destinationLabel, 0, 1);
		addConstrained(destinationTextField, 1, 1);
		addConstrained(destinationButton, 2, 1);
		
		contrains.fill = GridBagConstraints.HORIZONTAL;
		addConstrained(runButton,1, 3);
	}
	
	/**
	 * Creates all the components needed for the interface.
	 */
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

	/**
	 * Starts the GUI and sets its style to the system one.
	 */
	private ChooserGUI() {
		initGUI();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * Creates the interface by setting its title, icon and makes it closable. Next, the components are
	 * created and added. Finally, the GUI is set visible.
	 */
	private void initGUI() {		
		setTitle("Year\\Month File Organizer");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image image = new ImageIcon("icon.png").getImage();
		setIconImage(image);

		createComponents();
		addComponents();

		this.pack();
		this.setVisible(true);
	}

}
