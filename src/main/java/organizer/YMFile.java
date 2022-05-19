package organizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import exceptions.NotFileException;

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
	 */
	static YMFile createFromPath(String filePath) {
		YMFile file = null;
		
		try {
			file = new YMFile(filePath);
		} catch (NotFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
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
	
	private YMFile(String path) throws NotFileException, IOException {
		file = new File(path);
		
		if (!file.isFile())
			throw new NotFileException();
			
		date = extractDate();
	}

	private String extractDate() throws IOException  {
		return Files.readAttributes(file.toPath(), BasicFileAttributes.class)
					.lastModifiedTime().toString();
	}

}