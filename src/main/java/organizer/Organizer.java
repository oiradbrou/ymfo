package organizer;

import java.nio.file.NotDirectoryException;

import dirchooser.DirChooser;

/**
 * Class that organizes the files from the source {@link SrcDirectory} in the
 * destination {@link YMDirectory}. The files are organized in the year/month
 * structure of the destination folder.
 */
public final class Organizer {

	private SrcDirectory srcDir;
	private YMDirectory destDir;

	/**
	 * Method that runs the {@link Organizer} by creating an instance of it and
	 * starting the directory selection module.
	 */
	public static void run() {
		Organizer organizer = new Organizer();
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
		srcDir = SrcDirectory.createFromDir(srcDirPath);
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
		srcDir.getFiles().forEach(destDir::storeFile);
	}

}
