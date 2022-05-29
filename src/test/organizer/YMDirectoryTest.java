package organizer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class YMDirectoryTest {
	
	@ClassRule
	public static final TemporaryFolder dir = new TemporaryFolder();

	private static List<YMFile> testFiles = new LinkedList<>();
	private static final int NUMBER_OF_FILES = 10;
	private static final String INVALID_PATH = "invalid path";
																						
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
		assertThat(YMDirectory.createFromDir(dir.getRoot().getPath()))
			.isInstanceOf(YMDirectory.class);
	}
	
	@Test
	public void testCreateFromDirWithInvalidPath() throws NotDirectoryException {
		assertThatThrownBy(() -> YMDirectory.createFromDir(INVALID_PATH))
			.isInstanceOf(NotDirectoryException.class)
			.hasMessage(INVALID_PATH);
	}
	
}
