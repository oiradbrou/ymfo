package organizer;

import java.io.File;
import java.nio.file.NotDirectoryException;

/**
 * Abstract class that serves the purpose of extracting the common parts of
 * {@link YMFileDirectory} and {@link YMDirectory}.<br>
 * It generalizes the concept of directory in the project.
 */
abstract class Directory {

	/**
	 * Field that represents the directory to which the class refers.
	 */
	final File dir;
	
	/**
	 * Constructor that initializes the field {@link #dir}.
	 * 
	 * @param dirPath - Path to a directory.
	 * @throws NotDirectoryException If the path provided leads to a resource
	 * that isn't a directory.
	 */
	Directory(String dirPath) throws NotDirectoryException {
		File resource = new File(dirPath);
		
		if (!resource.isDirectory())
			throw new NotDirectoryException(dirPath);
		
		dir = resource;
	}
	
}