package imageorganizer;

import java.util.LinkedList;

import dirchooser.DirChooser;

public final class Organizer {
	
	private ImagesDirectory srcDir;
	private StoreDirectory destDir;
	
	public static void run() {
		Organizer organizer = new Organizer();
		DirChooser.chooseAndOrganizeWith(organizer);
	}
	
	public void setSrcDir(String srcDirPath) {
		srcDir = ImagesDirectory.fromPath(srcDirPath);
	}
	
	public void setDestDir(String destDirPath) {
		destDir = StoreDirectory.fromPath(destDirPath);
	}
	
	public void renameImages() {
		LinkedList<String> alreadyPresentDates = destDir.containedDates();
		
		for (Image image : srcDir.listImages()) {
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
