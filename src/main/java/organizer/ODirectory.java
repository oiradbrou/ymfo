package organizer;

import java.io.File;

import exceptions.NotDirectoryException;

class ODirectory {

	final File dir;
	
	ODirectory(String dirPath) throws NotDirectoryException {
		File resource = new File(dirPath);
		
		if (!resource.isDirectory())
			throw new NotDirectoryException();
		
		dir = resource;
	}

}