package organizer;

import java.nio.file.NotDirectoryException;
import java.util.LinkedList;

import dirchooser.DirChooser;

/**
 * Class that organizes the files from the source {@link SrcDirectory} in the
 * destination {@link YMDirectory}. The files are organized in the year/month
 * structure of the destination folder.
 */
public final class YMOrganizer {

	private SourceDirectory srcDir;
	private YMDirectory destDir;

	/**
	 * Method that runs the {@link YMOrganizer} by creating an instance of it and
	 * starting the directory selection module.
	 */
	public static void run() {
		YMOrganizer organizer = new YMOrganizer();
		DirChooser.chooseAndOrganizeWith(organizer);
	}

	/**
	 * Setter of the {@link SrcDirectory} from which the files to organize are
	 * taken.
	 *
	 * @param srcDirPath - Path to a directory.
	 * @throws NotDirectoryException - If the path provided leads to a resource
	 * that isn't a directory.
	 */
	public void setSrcDir(String srcDirPath) throws NotDirectoryException {
		srcDir = SourceDirectory.createFromDir(srcDirPath);
	}

	/**
	 * Setter of the destination {@link YMDirectory} where the organized files
	 * are stored.
	 *
	 * @param destDirPath - Path to a directory.
	 * @throws NotDirectoryException - If the path provided leads to a resource
	 * that isn't a directory.
	 */
	public void setDestDir(String destDirPath) throws NotDirectoryException {
		destDir = YMDirectory.createFromDir(destDirPath);
	}

	/**
	 * Method that organizes the files contained in the {@link SrcDirectory}
	 * by storing them into the {@link YMDirectory}. This means that the files
	 * are organized according to the structure of the destination directory.
	 */
	public void organize() {
		LinkedList<String> alreadyPresentDates = destDir.containedDates();

		for (YMFile image : srcDir.getFiles()) {
			String imageYear = image.extractYear();
			String imageMonth = image.extractMonth();
			String imageName = imageYear + "-" + imageMonth;
			
			if (!alreadyPresentDates.contains(imageName))
				destDir.createDirectory(imageYear, imageMonth);
			
			destDir.storeFile(image);
		}
	}

}
