package organizer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.NotDirectoryException;

/**
 * Class that serves the purpose of representing the directory from which
 * the files that the user wants to organise are taken.
 * 
 * @author raflat
 */
final class OFileDirectory extends ODirectory {

	/**
	 * Static factory method to create an {@link OFileDirectory} from
	 * a given path.
	 * 
	 * @param dirPath Path to a directory.
	 * @return New OFileDirectory object associated to provided path.
	 */
	static OFileDirectory createFromDir(String dirPath) {
		OFileDirectory dir = null;
		
		try {
			dir = new OFileDirectory(dirPath);;
		} catch (NotDirectoryException e) {
			e.printStackTrace();
		}
		
		return dir;
	}
	
	/**
	 * Method that returns a {@link List} of all the files contained
	 * in the {@link OFileDirectory} as {@link OFile} objects.
	 * 
	 * @return
	 * List of files contained in the ImagesDirectory it's called on.
	 */
	List<YMFile> listOFiles() {
		return Arrays.asList(dir.listFiles())
					 .parallelStream()
					 .map(f -> YMFile.createFromPath(f.getPath()))
					 .collect(Collectors.toList());
	}

	private OFileDirectory(String path) throws NotDirectoryException {
		super(path);
	}
	
}