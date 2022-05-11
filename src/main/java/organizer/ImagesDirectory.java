package organizer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that serves the purpose of representing the directory from which
 * the images that the user wants to organise are taken.
 * 
 * @author 
 * Dario
 */
final class ImagesDirectory {

	private final File file;

	/**
	 * Static factory method to create an {@link ImagesDirectory} from
	 * a given path.
	 * 
	 * @param 
	 * path String that represents the path to the {@link ImagesDirectory}.
	 * @return
	 * New {@link ImagesDirectory} object associated to provided path.
	 */
	static ImagesDirectory fromPath(String path) {
		return new ImagesDirectory(path);
	}
	
	/**
	 * Method that returns a list of all the images contained in the 
	 * {@link ImagesDirectory} as {@link Image} objects.
	 * 
	 * @return
	 * {@link List} of images contained in the {@link ImagesDirectory}
	 * it's called on.
	 */
	List<Image> listImages() {
		return Arrays.asList(file.listFiles())
					 .parallelStream()
					 .map(Image::new)
					 .collect(Collectors.toList());
	}

	private ImagesDirectory(String path) {
		this.file = new File(path);
	}
	
}