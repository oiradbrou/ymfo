<<<<<<< HEAD
package imageorganizer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class ImagesDirectory {

	private final File file;
	
	static ImagesDirectory fromPath(String path) {
		return new ImagesDirectory(path);
	}
	
	List<Image> listImages() {
		return Arrays.asList(file.listFiles())
					 .parallelStream()
					 .map(Image::new)
					 .collect(Collectors.toList());
	}
	
	private ImagesDirectory(String path) {
		this.file = new File(path);
	}
	
=======
package imageorganizer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class ImagesDirectory {

	private final File file;
	
	static ImagesDirectory fromPath(String path) {
		return new ImagesDirectory(path);
	}
	
	List<Image> listImages() {
		return Arrays.asList(file.listFiles())
					 .parallelStream()
					 .map(Image::new)
					 .collect(Collectors.toList());
	}
	
	private ImagesDirectory(String path) {
		this.file = new File(path);
	}
	
>>>>>>> 26ef2a4 (Project added)
}