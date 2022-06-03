package dirchooser;

/**
 * Interface that provides a method to customize an object given a .ini
 * configuration file.
 */
interface Configurable {

	/**
	 * Method that, provided a {@link CIni} object, configures the instance
	 * it's called on.
	 *
	 * @param iniFile - {@link CIni} objects from which the custom features are
	 * taken.
	 */
	void configure(CIni iniFile);

}