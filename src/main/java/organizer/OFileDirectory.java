package organizer;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

import exceptions.NotDirectoryException;

/**
 * Class that serves the purpose of representing the directory from which
 * the files that the user wants to organise are taken.
 * 
 * @author raflat
 */
final class OFileDirectory {

	private final File fileDir;

	/**
	 * Static factory method to create an {@link OFileDirectory} from
	 * a given path.
	 * 
	 * @param dirPath Path to a directory.
	 * @return New OFileDirectory object associated to provided path.
	 */
	static OFileDirectory createFromDir(String dirPath) {
		OFileDirectory fileDir = null;
		
		try {
			fileDir = new OFileDirectory(dirPath);;
		} catch (NotDirectoryException e) {
			e.printStackTrace();
		}
		
		return fileDir;
	}
	
	/**
	 * Method that returns an {@link Iterator} of all the files contained
	 * in the {@link OFileDirectory} as {@link OFile} objects.
	 * 
	 * @return
	 * Iterator of files contained in the ImagesDirectory
	 * it's called on.
	 */
	Iterator<OFile> listImages() {
		return Arrays.asList(fileDir.listFiles())
					 .parallelStream()
					 .map(f -> OFile.createFromPath(f.getPath()))
					 .iterator();
	}

	private OFileDirectory(String path) throws NotDirectoryException {
		File resource = new File(path);
		
		if (!resource.isDirectory())
			throw new NotDirectoryException();
		
		fileDir = resource;
	}
	
}