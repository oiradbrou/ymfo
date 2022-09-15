package organizer;

import java.io.File;

import java.nio.file.NotDirectoryException;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Directory with a <b>year\month directory structure</b>.<br>
 * Files inside the directory are organized into two level of directories, according to the last modified
 * date:
 * <ol>
 * 	<li>first layer consisting of directories denoting the year of the file,</li>
 * 	<li>second one composed of directories indicating the month of the file.</li>
 * </ol>
 * Furthermore, the files are enumerated from 0 to n, according to how many of them are contained in the month
 * sub-directory.
 */
final class YearMonthDirectory extends Directory {

	/**
	 * Creates and returns a new {@link YearMonthDirectory} from a provided path, which must be of an
	 * existing directory.
	 *
	 * @param path - {@link String} representing the path to a directory.
	 * 
	 * @return New instance of {@link YearMonthDirectory}.
	 * 
	 * @throws NotDirectoryException if the path leads to a resource that isn't a directory.
	 */
	static YearMonthDirectory from(String path) {
		return new YearMonthDirectory(path);
	}
	

	/**
	 * Returns the dates already contained in the {@link YearMonthDirectory} it's called on. Those are
	 * obtained by visiting each sub-directory and joining the name of the year one with each of it's month
	 * ones, obtaining a list of strings like <b>"year-month"</b>.
	 *
	 * @return {@link LinkedList} containing a {@link String} for each year/month couple contained in the
	 * directory.
	 */
	LinkedList<String> dates() {
		LinkedList<String> dates = new LinkedList<>(Arrays.asList(""));

		for (File yearDirectory : dir.listFiles())
			for (File monthDirectory : yearDirectory.listFiles())
				dates.addFirst(yearDirectory + "-" + monthDirectory);

		return dates;
	}

	/**
	 * Calculates the number of the last image inside the sub-directory named "<b>year\month</b>" of the
	 * {@link YearMonthDirectory} it's called on.
	 *
	 * @param imageYear - {@link String} of the destination year directory.
	 * @param imageMonth - {@link String} of the destination month directory.
	 * 
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
	
	/**
	 * Creates new a sub-directory named "<b>year</b>/<b>month</b>" inside the {@link YearMonthDirectory} it's
	 * called on.<br>
	 * If not already present, the parent directory "<b>year</b>" is created alongside it's child.<br>
	 *
	 * @param year - Number representing a year ("yyyy").
	 * @param month - String representing a month ("mm").
	 */
	void makeDirectory(String year, String month) {
		new File(yearMonthPath(year, month)).mkdirs();
	}

	/**
	 * Stores a provided {@link YearMonthFile} inside the {@link YearMonthDirectory} it's called on.<b>
	 * The YMFile is stored in the sub-directory correspondent to it's date.
	 *
	 * @param file - {@link YearMonthFile} to be store.
	 */
	void store(YearMonthFile file) {
		String year = file.year();
		String month = file.month();
		
		String destinationPath = yearMonthPath(year, month) + "\\";
		String name = year + "-" + month + "_" + lastImageNumber(year, month) + file.dotExtension();

		file.moveTo(destinationPath + name);
	}

	private YearMonthDirectory(String path) {
		super(path);
	}

	private String yearMonthPath(String year, String month) {
		return dir.getPath() + "\\" + year + "\\" + month;
	}

}
