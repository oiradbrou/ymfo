package organizer;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class that defines a Year/Month type directory.<br>
 * The files inside the directory are organized into two level of directories:
 * <ol>
 * 	<li>a first layer consisting of directories denoting the year of the file,</li>
 * 	<li>a second one composed of directories indicating the month of the file.</li>
 * </ol>
 * Furthermore, the files are enumerated from 0 to n, according to how many
 * of them are contained in the month sub-directory.
 */
final class YMDirectory extends Directory {

	/**
	 * Static factory method that creates and returns a new
	 * {@link YMDirectory} pointing to the path provided. The path provided
	 * must be of an existing directory.
	 *
	 * @param dirPath - Path to a directory.
	 * @return New instance of YMDirectory pointing to <b>dirPath</b>.
	 * @throws NotDirectoryException - If <b>dirPath</b> leads to a resource
	 * that isn't a directory.
	 */
	static YMDirectory createFromDir(String dirPath) throws NotDirectoryException {
		return new YMDirectory(dirPath);
	}

	/**
	 * Method that returns the dates already contained in the
	 * {@link YMDirectory} it's called on.<br>
	 * The dates contained are obtained by visiting each sub-directory and
	 * joining the name of the year one with each of it's month ones, obtaining
	 * a list of strings in the form "year-month."</p>
	 *
	 * @return {@link LinkedList} containing a string for each year/month
	 * couple contained in the directory.
	 */
	LinkedList<String> containedDates() {
		LinkedList<String> dates = new LinkedList<>(Arrays.asList(""));

		for (File yearDirectory : dir.listFiles())
			for (File monthDirectory : yearDirectory.listFiles())
				dates.addFirst(yearDirectory + "-" + monthDirectory);

		return dates;
	}

	/**
	 * Method that creates new a sub-directory "<b>year</b>/<b>month</b>"
	 * inside the {@link YMDirectory} it's called on. If not already present,
	 * the parent directory "<b>year</b>" is created alongside it's child.<br>
	 *
	 * @param year - Name of the parent sub-directory.
	 * @param month - Name of the child sub-directory
	 */
	void createDirectory(String year, String month) {
		new File(yearMonthPath(year, month)).mkdirs();
	}

	/**
	 * Method that stores a provided {@link YMFile} into the
	 * {@link YMDirectory} it's called on. The YMFile is stored in the
	 * sub-directory correspondent to it's date.
	 *
	 * @param file - File to store inside the directory.
	 */
	void storeFile(YMFile file) {
		String fileYear = file.extractYear();
		String fileMonth = file.extractMonth();

		file.changePathTo(yearMonthPath(fileYear, fileMonth) + "\\" +
						  fileYear + "-" + fileMonth + "_" +
						  lastImageNumber(fileYear, fileMonth) +
						  file.extractDotExtension());
	}

	/**
	 * Method that calculates the number of the last image inside the directory
	 * named "<b>year</b>-<b>month</b>" of the {@link YMDirectory} it's called
	 * on.
	 *
	 * @param imageYear - Number of the target year directory.
	 * @param imageMonth - Number of the target month directory.
	 * @return Number of the last image.
	 */
	int lastImageNumber(String imageYear, String imageMonth) {
		for (File year : dir.listFiles())
			if (year.getName().equals(imageYear))
				for (File month : year.listFiles())
					if (month.getName().equals(imageMonth))
						return month.listFiles().length;

		return 0;
	}

	private YMDirectory(String path) throws NotDirectoryException {
		super(path);
	}

	private String yearMonthPath(String year, String month) {
		return dir.getPath() + "\\" + year + "\\" + month;
	}

}
