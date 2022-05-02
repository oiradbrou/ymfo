package imagerenamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

final class Image {
	
	private final File source;
	private final String date;

	Image(File source) {
		this.source = source;
		
		String extractedDate = null;
		try {
			extractedDate = extractDate();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		date = extractedDate;
	}

	File getSource() {
		return source;
	}
	
	String extractYear() {
		return date.substring(0, 4);
	}
	
	String extractMonth() {
		return date.substring(5, 7);
	}
	
	String extractDotExtension() {
		String name = source.getName();
		return name.substring(name.indexOf("."));
	}
	
	private final String extractDate() throws IOException {
		if (!source.isFile())
			throw new IOException("Ensure that a file was given and not a directory.");
		return Files.readAttributes(source.toPath(),
									BasicFileAttributes.class)
					.lastModifiedTime()
					.toString();
	}

}