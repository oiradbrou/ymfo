package organizer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.NotDirectoryException;
import exceptions.NotFileException;

/**
 * Class that serves the purpose of representing the directory from which
 * the files that the user wants to organise are taken.
 * 
 * @author raflat
 */
//rename to YMFileDirectory
final class YMFileDirectory extends ODirectory {

	/**
	 * Static factory method to create an {@link OFileDirectory} from
	 * a given path.
	 * 
	 * @param dirPath Path to a directory.
	 * @return New OFileDirectory object associated to provided path.
	 */
	static YMFileDirectory createFromDir(String dirPath) {
		YMFileDirectory dir = null;
		
		try {
			dir = new YMFileDirectory(dirPath);;
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
	List<YMFile> listFiles() {
		return Arrays.asList(dir.listFiles())
					 .parallelStream()
					 .map(File::getPath)
					 .map(path -> { 
						 	try {
						 		return YMFile.createFromPath(path);
						 	} catch (NotFileException e) {
						 		System.err.println("Ensure that the directory only contains files.");
						 		return null;}})
					 .collect(Collectors.toList());
	}

	private YMFileDirectory(String path) throws NotDirectoryException {
		super(path);
	}
	
}