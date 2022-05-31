package dirchooser;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;

/*
 * Class that wraps an Ini type object, providing methods to easily create one
 * and read from it.
 */
final class CIni extends Ini {

	private static final long serialVersionUID = 1L;

	private static Ini file;

	public static CIni createFromIni(String iniFilePath) {
		return new CIni(iniFilePath);
	}
	
	public String readFrom(String sectionName, String fieldName) {
		return file.get(sectionName, fieldName);
	}
	
	private CIni(String iniFilePath) {
		try {
			file = new Ini(new File(iniFilePath));
			//file = new Ini(new File("src\\main\\resources\\conf.ini"));
		} catch (IOException e) {}
	}
	
}
