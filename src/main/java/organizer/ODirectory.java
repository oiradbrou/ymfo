package organizer;

import java.io.File;
import java.nio.file.NotDirectoryException;

/**
 * Abstract class that serves the purpose of extracting the common parts
 * of {@link OFileDirectory} and {@link StoreDirectory}.<br>
 * It generalises the concept of directory in the project,
 * having a field that stores it and a constructor to initialise it.
 * 
 * @author raflat
 */
abstract class ODirectory {

	/**
	 * Field that represents the directory that defines the class.
	 */
	final File dir;
	
	/**
	 * Constructor that initialises the field {@link #dir}.
	 * 
	 * @param dirPath - Path to a directory.
	 * @throws 
	 * NotDirectoryException if the path provided leads to a resource
	 * that isn't a directory.
	 */
	ODirectory(String dirPath) throws NotDirectoryException {
		File resource = new File(dirPath);
		
		if (!resource.isDirectory())
			throw new NotDirectoryException(dirPath);
		
		dir = resource;
	}
	
}