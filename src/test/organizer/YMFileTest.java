package organizer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class YMFileTest {
	
	@ClassRule
	public static TemporaryFolder dir = new TemporaryFolder();
	
	private static File testFile;
	private static YMFile testYMFile;
	private static final String TEST_FILE_EXTENSION = ".txt";
	private static final String TEST_FILE_NAME = "testFile" + TEST_FILE_EXTENSION;
	private static final String INVALID_PATH = "ivalid path";
	private static final String NOTFILEEXCEPTION_MESSAGE = "Error trying to use the file at " + 
														   testFile.getPath();

	@BeforeClass
	public static void init() throws FileNotFoundException, IOException {
		try {
			testFile = dir.newFile(TEST_FILE_NAME);
		} catch (IOException e) {
			System.err.println("Error creating temporary test file.");
		}
		testYMFile = YMFile.createFromPath(testFile.getPath());
	}
	
	@Test
	public void testCreateFromPathWithValidPath() throws FileNotFoundException, IOException {
		assertThat(YMFile.createFromPath(testFile.getPath()))
			.isInstanceOf(YMFile.class);
	}
	
	@Test
	public void testCreateFromPathWithInvalidPath() {		
		assertThatThrownBy(() -> YMFile.createFromPath(INVALID_PATH))
			.isInstanceOf(FileNotFoundException.class)
			.hasMessage(NOTFILEEXCEPTION_MESSAGE);
		
		File testDir = null;
		try  {
			testDir = dir.newFolder("TestDirectory");
		} catch (IOException e) {
			 System.err.println("Error creating temporary test directory.");
		}
		String testDirPath = testDir.getPath();
		assertThatThrownBy(() -> YMFile.createFromPath(testDirPath))
			.isInstanceOf(FileNotFoundException.class)
			.hasMessage(NOTFILEEXCEPTION_MESSAGE);;
	}
	
	@Test
	public void testGetFileReturnsCorrectInstance() {
		assertThat(testYMFile.getFile())
			.isEqualTo(testFile);
	}
	
	@Test
	public void testExtractYearReturnsCorrectYear() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int currentYear = localDate.getYear();
		
		assertThat(testYMFile.extractYear())
			.isEqualTo(String.valueOf(currentYear));
	}
	
	@Test
	public void testExtractMonthReturnsCorrectMonth() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int currentMonth = localDate.getMonthValue();
		
		assertThat(testYMFile.extractMonth())
			.isEqualTo(String.format("%02d", currentMonth));
	}
	
	@Test
	public void testExtractDotExtensionReturnsCorrectExtension() {
		assertThat(testYMFile.extractDotExtension())
			.isEqualTo(TEST_FILE_EXTENSION);
	}

}
