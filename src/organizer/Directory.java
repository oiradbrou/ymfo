package organizer;

import java.io.File;
import java.nio.file.NotDirectoryException;

/**
 * Abstracts the common parts of {@link SourceDirectory} and {@link YearMonthDirectory}.
 */
abstract class Directory {

	/**
	 * Stores the directory that the object refers.
	 */
	final File dir;

	/**
	 * Initializes {@link #dir} field.
	 *
	 * @param path - {@link String} path to a directory.
	 */
	Directory(String path) {
		File resource = new File(dirPath);

		if (!resource.isDirectory())
			System.out.println("Resource chosen is not a directory");

		dir = resource;
	}

}