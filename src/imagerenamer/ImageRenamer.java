package imagerenamer;

import java.io.IOException;
import java.util.LinkedList;

public class ImageRenamer {
	
	private final ImagesDirectory sourceDir = ImagesDirectory.fromPath("");
	private final StoreDirectory destDir = StoreDirectory.fromPath("");
	
	public final void renameImages() throws IOException {
		LinkedList<String> alreadyPresentDates = destDir.containedDates();
		
		for (Image image : sourceDir.listImages()) {
			String imageYear = image.extractYear();
			String imageMonth = image.extractMonth();
			String imageName = imageYear + "-" + imageMonth;
					
			if (!alreadyPresentDates.contains(imageName))
				destDir.createDirectory(imageYear, imageMonth);
			
			imageName += "_" + destDir.lastImageNumber(imageYear, imageMonth);
			
			destDir.storeImage(image, imageName, imageYear, imageMonth);
		}
	}
	
}
