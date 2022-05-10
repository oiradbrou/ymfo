package dirchooser;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;

public final class RIni extends Ini {

	private static final long serialVersionUID = 1L;

	private static Ini file;
	
	public static RIni create() {
		return new RIni();
	}
	
	public String readFrom(String sectionName, String fieldName) {
		return file.get(sectionName, fieldName);
	}
	
	private RIni() {
		try {
			file = new Ini(new File("src\\main\\resources\\conf.ini"));
		} catch (IOException e) {}
	}
	
}
