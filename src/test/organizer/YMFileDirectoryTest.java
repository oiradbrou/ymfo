package organizer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class YMFileDirectoryTest {
	
	@ClassRule
	public static TemporaryFolder dir = new TemporaryFolder();

	private static List<YMFile> testFiles;
	private static YMFileDirectory testDir;
	private static final int NUMBER_OF_FILES = 10;
	private static final String INVALID_PATH = "invalid path";
	//change error to java one
	private static final String NOTDIRECTORYEXCEPTION_MESSAGE = new NotDirectoryException(dir.getRoot()
																							 .getPath())
																							 .getMessage();

	@BeforeClass
	public static void init() throws FileNotFoundException, IOException {
		try {
			for (int i = 0; i < NUMBER_OF_FILES; i++) {
				File file = dir.newFile();
				testFiles.add(YMFile.createFromPath(file.getPath()));
			}
		} catch (IOException e) {
			 System.err.println("Error creating temporary test files.");
		}
	}
	
	@Test
	public void testCreateFromDirWithValidPath() throws NotDirectoryException {
		assertThat(YMFileDirectory.createFromDir(dir.getRoot().getPath()))
			.isInstanceOf(YMFileDirectory.class);
	}
	
	public void testCreateFromDirWithInvalidPath() throws NotDirectoryException {
		assertThat(YMFileDirectory.createFromDir(INVALID_PATH))
			.isInstanceOf(YMFileDirectory.class);
	}

}
