package dirchooser;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;

/**
 * Class that provides methods to easily read from a .ini file.
 */
final class CIni extends Ini {

	private static final long serialVersionUID = 1L;

	private static Ini file;

	/**
	 * Static factory method that creates a {@link CIni} object from a path to
	 * an .ini type file.
	 *
	 * @param iniFilePath - Path to a .ini file.
	 * @return New object of type CIni.
	 */
	static CIni createFromIni(String iniFilePath) {
		return new CIni(iniFilePath);
	}

	/**
	 * Method that allows to read data from the file of a {@link CIni} instance
	 * provided the names of the desired section and field.
	 *
	 * @param sectionName - {@link String} name of field's section.
	 * @param fieldName - String name of field to read.
	 * @return Content of the desired field as String.
	 */
	String readFrom(String sectionName, String fieldName) {
		return file.get(sectionName, fieldName);
	}

	private CIni(String iniFilePath) {
		try {
			file = new Ini(new File(iniFilePath));
		} catch (IOException e) {
			System.err.println("Error trying to open the ini file.");
		}
	}

}
