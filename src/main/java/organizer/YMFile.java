package organizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Class that wraps a file, providing methods to extract it's date and rename
 * it.<br>
 * The date extracted is the one referring to the last time the file was
 * modified.
 */
final class YMFile {

	private final File file;
	private final String date;

	/**
	 * Getter that return the path of the {@link YMFile} it's called on.
	 *
	 * @return Path as a {@link String}.
	 */
	String getPath() {
		return file.getPath();
	}

	/**
	 * Static factory method that, given the path to a file, creates it's
	 * corresponding {@link YMFile} and returns it.
	 *
	 * @param filePath - {@link String} of path to a file.
	 * @throws FileNotFoundException if <b>filePath</b> leads to a resource
	 * that isn't a file.
	 */
	static YMFile createFromPath(String filePath) throws FileNotFoundException {
		return new YMFile(filePath);
	}

	/**
	 * Method that extracts the year's number of the {@link YMFile} it's called
	 * on.
	 *
	 * @return Year as {@link String} formatted like "yyyy".
	 */
	String extractYear() {
		return date.substring(0, 4);
	}

	/**
	 * Method that extracts the month's number of the {@link YMFile} date it's
	 * called on.
	 *
	 * @return Month as {@link String} formatted like "mm".
	 */
	String extractMonth() {
		return date.substring(5, 7);
	}

	/**
	 * Method that extracts the extension of the {@link YMFile} it's called on,
	 * preceded by a dot.
	 *
	 * @return {@link String} in the form ".extension".
	 */
	String extractDotExtension() {
		String name = file.getName();
		return name.substring(name.indexOf("."));
	}

	/**
	 * Method that changes the {@link YMFile}'s path to the provided
	 * one.<br>
	 *
	 * @param newPath - {@link String} representing the new path.
	 */
	void changePathTo(String newPath) {
		file.renameTo(new File(newPath));
	}

	private YMFile(String path) throws FileNotFoundException {
		file = new File(path);

		if (!file.isFile())
			throw new FileNotFoundException("Error trying to use the file at " + path);

		date = extractDate();
	}

	private String extractDate() {
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