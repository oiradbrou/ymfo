package organizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Class that wraps a file of {@link File} type.<br>
 * Provides methods to extract it's date.
 *  
 * @author raflat
 */
final class YMFile {

	private final File file;
	private String date;

	/**
	 * Static factory method that given the path to a file creates
	 * it's corresponding OFile.
	 * 
	 * @param filePath - String of path to a file.
	 * @throws NotFileException 
	 */
	static YMFile createFromPath(String filePath) throws FileNotFoundException {
		return new YMFile(filePath);
	}

	/**
	 * Getter that returns the {@link File} that's wrapped 
	 * by the {@link OFile} it's called on.
	 * 
	 * @return File associated to the OFile.
	 */
	File getFile() {
		return file;
	}
	
	/**
	 * Method that extracts the year of the {@link OFile}'s 
	 * date it's called on.
	 * 
	 * @return 
	 * String representing the number of the year of the
	 * OFile it's called on.<br>
	 * The format of the returned string is "yyyy".
	 */
	String extractYear() {
		return date.substring(0, 4);
	}
	
	/**
	 * Method that extracts the month of the {@link OFile}'s 
	 * date it's called on.
	 * 
	 * @return
	 * String representing the number of the month of the OFile 
	 * it's called on.<br>
	 * The format of the returned string is "mm".
	 */
	String extractMonth() {
		return date.substring(5, 7);
	}
		
	/**
	 * Method that extracts the extension of the {@link OFile}, 
	 * preceded by a dot.
	 * 
	 * @return String in the form ".extension".
	 */
	String extractDotExtension() {
		String name = file.getName();
		return name.substring(name.indexOf("."));
	}
	
	private YMFile(String path) throws FileNotFoundException {
		file = new File(path);
		
		if (!file.isFile())
			throw new FileNotFoundException("Error trying to use the file at " + path);
			
		date = extractDate();
	}

	private String extractDate()  {
		String date = null;
		try {
			date = Files.readAttributes(file.toPath(), BasicFileAttributes.class)
								.lastModifiedTime().toString();
		} catch (IOException e) {
			 System.err.println("Error test file.");
		}
		return date;
	}

}