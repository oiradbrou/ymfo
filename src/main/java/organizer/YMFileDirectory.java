package organizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that represents the directory from which the files that the user wants
 * to organize are taken.<br>
 * Once initialized, the class creates a {@link YMFile} for each of the files
 * it contains.
 */
final class YMFileDirectory extends Directory {

	/**
	 * Static factory method that creates and returns a {@link YMFileDirectory}
	 * from a given path.
	 *
	 * @param dirPath - Path to a directory.
	 * @return New YMFileDirectory object.
	 * @throws NotDirectoryException If <b>dirPath</b> leads to a resource
	 * that isn't a directory.
	 */
	static YMFileDirectory createFromDir(String dirPath) throws NotDirectoryException {
		return new YMFileDirectory(dirPath);
	}

	/**
	 * Getter that returns a {@link List} of all the files contained
	 * in the {@link YMFileDirectory} as {@link YMFile} objects.
	 *
	 * @return List of files.
	 */
	List<YMFile> getFiles() {
		return Arrays.asList(dir.listFiles())
					 .parallelStream()
					 .map(File::getPath)
					 .map(path -> {
						 	try {
						 		return YMFile.createFromPath(path);
						 	} catch (FileNotFoundException e) {
						 		System.err.println("Ensure that the directory only contains files.");
						 		return null;}})
					 .collect(Collectors.toList());
	}

	private YMFileDirectory(String path) throws NotDirectoryException {
		super(path);
	}

}