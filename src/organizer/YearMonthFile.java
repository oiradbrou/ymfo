package organizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Class that wraps a file, providing methods to extract it's date and change it's path.<br>
 * The date extracted is the one referring to the last time the file was modified.
 */
final class YearMonthFile {

	private final File file;
	private final String date;

	/**
	 * Given the path to a file, creates it's corresponding {@link YearMonthFile} and returns it.
	 *
	 * @param path - {@link String} path to a file.
	 */
	static YearMonthFile from(String path) throws FileNotFoundException {
		return new YearMonthFile(path);
	}

	/**
	 * Returns the extension of the {@link YearMonthFile} it's called on, preceded by a dot.
	 *
	 * @return {@link String} representing the extension as <b>".extension"</b>.
	 */
	String dotExtension() {
		String name = file.getName();

		return name.substring(name.indexOf("."));
	}

	/**
	 * Returns the path of the {@link YearMonthFile} it's called on.
	 *
	 * @return {@link String} representing the path of the file.
	 */
	String path() {
		return file.getPath();
	}

	/**
	 * Returns the month's number of the {@link YearMonthFile} date it's called on.
	 *
	 * @return Month as {@link String} as <b>"mm"</b>.
	 */
	String month() {
		return date.substring(5, 7);
	}

	/**
	 * Moves the {@link YearMonthFile} to the location pointed by the provided path.<br>
	 *
	 * @param path - {@link String} representing the destination.
	 */
	void moveTo(String path) {
		file.renameTo(new File(path));
	}

	/**
	 * Returns the year's number of the {@link YearMonthFile} it's called on.
	 *
	 * @return Year as {@link String} formatted like <b>"yyyy"</b>.
	 */
	String year() {
		return date.substring(0, 4);
	}

	/**
	 * Returns the date of the {@link #file} field.<br>
	 * Shows as message in standard error if an {@link IOException} happens while extracting the date.
	 *  
	 * @return Date as {@link String}.
	 */
	private String date() {
		String date = null;

		try {
			date = Files.readAttributes(file.toPath(), BasicFileAttributes.class)
					.lastModifiedTime().toString();
		} catch (IOException exception) {
			 System.err.println(exception.getMessage());
		}

		return date;
	}

	private YearMonthFile(String path) {
		file = new File(path);

		if (!file.isFile())
			System.err.println("Provided path doesn't lead to a file.");

		date = date();
	}

}