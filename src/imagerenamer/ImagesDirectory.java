package imagerenamer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class ImagesDirectory {

	private final File source;
	
	static ImagesDirectory fromPath(String path) {
		return new ImagesDirectory(path);
	}
	
	List<Image> listImages() {
		return Arrays.asList(source.listFiles())
					 .parallelStream()
					 .map(Image::new)
					 .collect(Collectors.toList());
	}
	
	private ImagesDirectory(String path) {
		this.source = new File(path);
	}
	
}