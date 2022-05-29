package organizer;

import java.nio.file.NotDirectoryException;
import java.util.LinkedList;

import dirchooser.DirChooser;

/**
 * Class that organizes the files from the source {@link OFileDirectory}
 * in the destination {@link StoreDirectory}.<br>
 * The files are organized in two level of directories: a first one
 * consisting of directories denoting the year of the file, the
 * second one composed of directories indicating the month of the file.
 * Furthermore, the files are enumerated from 0 to n, according to how many
 * of them are contained in the directory.
 */
public final class Organizer {

	private SrcDirectory srcDir;
	private YMDirectory destDir;

	/**
	 * Method that runs the {@link Organizer} by launching the
	 * directory selection module.
	 */
	public static void run() {
		Organizer organizer = new Organizer();
		DirChooser.chooseAndOrganizeWith(organizer);
	}

	/**
	 * Setter to set the source {@link OFileDirectory} from which
	 * the files to organize are taken, given the path to a
	 * directory.
	 *
	 * @param srcDirPath - Path to a directory.
	 * @throws NotDirectoryException
	 */
	public void setSrcDir(String srcDirPath) throws NotDirectoryException {
		srcDir = SrcDirectory.createFromDir(srcDirPath);
	}

	/**
	 * Setter to set the destination {@link StoreDirectory} to which
	 * the files to organize are stored, given the path to a
	 * directory.
	 *
	 * @param destDirPath - Path to a directory.
	 * @throws NotDirectoryException 
	 */
	public void setDestDir(String destDirPath) throws NotDirectoryException {
		destDir = YMDirectory.createFromDir(destDirPath);
	}

	/**
	 * Method that organizes the files contained in the source
	 * {@link OFileDirectory} by renaming them to their new location.
	 */
	public void renameFiles() {
		LinkedList<String> alreadyPresentDates = destDir.containedDates();

		for (YMFile image : srcDir.getFiles()) {
			String imageYear = image.extractYear();
			String imageMonth = image.extractMonth();
			String imageName = imageYear + "-" + imageMonth;

			if (!alreadyPresentDates.contains(imageName))
				destDir.createDirectory(imageYear, imageMonth);

			imageName += "_" + destDir.lastImageNumber(imageYear, imageMonth);

			destDir.storeFile(image);
		}
	}

}
