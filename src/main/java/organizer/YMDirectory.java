package organizer;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import exceptions.NotDirectoryException;

/**
 * <p>Class that defines a Year/Month type directory.</p>
 * 
 * <p>The files inside the directory are organized into two level of directories:
 * <ol>
 * 	<li>a first layer consisting of directories denoting the year of the file,</li>
 * 	<li>a second one composed of directories indicating the month of the file.</li>
 * </ol>
 * Furthermore, the files are enumerated from 0 to n, according to how many
 * of them are contained in the month sub-directory.</p>
 * 
 * @author raflat
 */
final class YMDirectory extends ODirectory {

	/**
	 * <p>Static factory method that creates and returns a new {@link YMDirectory}
	 * pointing to the path provided.</p>
	 * 
	 * <p>The path provided must be of an existing directory.</p>
	 * 
	 * @param dirPath - path to a directory.
	 * @return New instance of YMDirectory pointing to <b>dirPath</b>.
	 */
	static YMDirectory createFromDir(String dirPath) {
		YMDirectory dir = null;
		
		try {
			dir = new YMDirectory(dirPath);;
		} catch (NotDirectoryException e) {
			e.printStackTrace();
		}
		
		return dir;
	}
	
	/**
	 * <p>Method that returns the dates already contained in the 
	 * {@link YMDirectory} it's called on.</p>
	 * 
	 * <p>The dates contained are obtained by visiting each sub-directory
	 * and joining the name of the year one with each of it's month ones,
	 * obtaining a list of strings in the form "year-month."</p>
	 * 
	 * @return 
	 * {@link LinkedList} containing a string for each year/month couple
	 * contained in the directory.
	 */
	LinkedList<String> containedDates() {
		LinkedList<String> dates = new LinkedList<>(Arrays.asList(""));
		
		for (File yearDirectory : dir.listFiles())
			for (File monthDirectory : yearDirectory.listFiles())
				dates.addFirst(yearDirectory + "-" + monthDirectory);
		
		return dates;
	}

	/**
	 * <p>Method that creates new a sub-directory "<b>year</b>/<b>month</b>"
	 * inside the {@link YMDirectory} it's called on.</p>
	 * 
	 * <p>If not already present, the parent directory "<b>year</b>" 
	 * is created alongside it's child.</p>
	 * 
	 * @param year - name of the parent sub-directory.
	 * @param month - name of the child sub-directory
	 */
	void createDirectory(String year, String month) {
		new File(yearMonthPath(year, month)).mkdirs();
	}
	
	/**
	 * <p>Method that stores a provided {@link OFile} 
	 * into the {@link YMDirectory} it's called on.</p>
	 * 
	 * <p>The OFile is stored in the sub-directory correspondent
	 * to it's date</p>
	 * 
	 * @param file
	 */
	void storeFile(YMFile file) {
		String fileYear = file.extractYear();
		String fileMonth = file.extractMonth();
		
		file.getFile().renameTo(new File(yearMonthPath(fileYear, fileMonth) + "\\" +
										 fileYear + "-" + fileMonth + "_" +
										 lastImageNumber(fileYear, fileMonth) +
										 file.extractDotExtension()));
	}
	
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
