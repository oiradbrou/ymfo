package organizer;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import exceptions.NotDirectoryException;

/**
 * Class that represents the directory in which the files that the user wants
 * to organise are stored and organised in directories.
 * 
 * @author raflat
 */
//update javadoc
final class YMDirectory extends ODirectory {

	static YMDirectory createFromDir(String dirPath) {
		YMDirectory dir = null;
		
		try {
			dir = new YMDirectory(dirPath);;
		} catch (NotDirectoryException e) {
			e.printStackTrace();
		}
		
		return dir;
	}
	
	LinkedList<String> containedDates() {
		LinkedList<String> dates = new LinkedList<>(Arrays.asList(""));
		
		if (dir.listFiles() == null)
			return dates;
		
		for (File yearDirectory : dir.listFiles())
			for (File monthDirectory : yearDirectory.listFiles())
				dates.addFirst(yearDirectory + "-" + monthDirectory);
		
		return dates;
	}

	void createDirectory(String year, String month) {
		new File(yearMonthPath(year, month)).mkdirs();
	}
	
	void storeImage(OFile image, String name, String year, String month) {
		image.getFile().renameTo(new File(yearMonthPath(year, month) +
											"\\" + name + 
											image.extractDotExtension()));
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
