package organizer;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Directory from which the files to organize are taken.<br>
 * Once instantiated, the objects creates a {@link YearMonthFile} for each file it contains.
 */
final class SourceDirectory extends Directory {

	/**
	 * Static factory method that creates and returns a {@link SrcDirectory} from a given path.
	 *
	 * @param dirPath - Path to a directory.
	 * 
	 * @return New SrcDirectory object.
	 */
	static SourceDirectory from(String dirPath) {
		return new SourceDirectory(dirPath);
	}

	/**
	 * Returns a {@link List} of all the files contained in the {@link SourceDirectory} as
	 * {@link YearMonthFile} objects.
	 *
	 * @return {@link List} of files.
	 */
	List<YearMonthFile> files() {
		return Arrays.asList(dir.listFiles()).parallelStream()
				.map(File::getPath)
				.map(path -> {
					try {
						return YearMonthFile.from(path);
					} catch (FileNotFoundException exception) {
						System.err.println("Ensure that the directory only contains files.");
						return null;
					}
				}).collect(Collectors.toList());
	}

	/**
	 * Returns a {@link SourceDirectory} that wraps the directory at the provided path.
	 * 
	 * @param path - {@link String} representing the path of the directory to wrap.
	 */
	private SourceDirectory(String path) {
		super(path);
	}

}