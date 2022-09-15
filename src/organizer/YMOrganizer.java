package organizer;

import java.util.LinkedList;

/**
 * Moves the files from a source directory into a destination one.<br>
 * Files are organized in the <b>year\month</b> structure of the destination folder.
 */
public final class YMOrganizer {

	/**
	 * Source and destination directories.
	 */
	private SourceDirectory sourceDirectory;
	private YearMonthDirectory destinationDirectory;

	/**
	 * Sets the source directory from which the files to organize are taken.
	 *
	 * @param path - {@link String} path to a directory.
	 */
	public void setSource(String path) {
		sourceDirectory = SourceDirectory.from(path);
	}

	/**
	 * Sets the destination directory where the organized files are stored.
	 *
	 * @param path - {@link String} path to a directory.
	 */
	public void setDestination(String path) {
		destinationDirectory = YearMonthDirectory.from(path);
	}

	/**
	 * Organizes the files contained in the source directory by storing them into the destination one.<br>
	 * Files are organized according to the structure of the destination directory.
	 */
	public void organize() {
		LinkedList<String> dates = destinationDirectory.dates();

		for (YearMonthFile file : sourceDirectory.files()) {
			String year = file.year();
			String month = file.month();

			String name = year + "-" + month;
			if (!dates.contains(name))
				destinationDirectory.makeDirectory(year, month);
			
			destinationDirectory.store(file);
		}
	}

}
