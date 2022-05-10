package imageorganizer;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

final class StoreDirectory {

	private final File destination;
	
	static StoreDirectory fromPath(String path) {
		return new StoreDirectory(path);
	}

	LinkedList<String> containedDates() {
		LinkedList<String> dates = new LinkedList<>(Arrays.asList(""));
		
		if (destination.listFiles() == null)
			return dates;
		
		for (File yearDirectory : destination.listFiles())
			for (File monthDirectory : yearDirectory.listFiles())
				dates.addFirst(yearDirectory + "-" + monthDirectory);
		
		return dates;
	}

	void createDirectory(String year, String month) {
		new File(yearMonthPath(year, month)).mkdirs();
	}
	
	void storeImage(Image image, String name, String year, String month) {
		image.getFile().renameTo(new File(yearMonthPath(year, month) +
											"\\" + name + 
											image.extractDotExtension()));
	}
	
	int lastImageNumber(String imageYear, String imageMonth) {
		for (File year : destination.listFiles())
			if (year.getName().equals(imageYear))
				for (File month : year.listFiles())
					if (month.getName().equals(imageMonth))
						return month.listFiles().length;
		
		return 0;
	}

	private StoreDirectory(String path) {
		this.destination = new File(path);
	}

	private String yearMonthPath(String year, String month) {
		return destination.getPath() + "\\" + year + "\\" + month;
	}
	
}
