package organizer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import exceptions.NotFileException;

public class YMFileTest {
	
	private static File testFile;
	private static YMFile testYMFile;
	private static final String TEST_FILE_EXTENSION = ".txt";
	private static final String TEST_FILE_NAME = "testFile" + TEST_FILE_EXTENSION;
	private static final String INVALID_PATH = "";
	private static final String NOTFILEEXCEPTION_MESSAGE = new NotFileException().getMessage();
	
	@ClassRule
	public static TemporaryFolder folder = new TemporaryFolder();

	@BeforeClass
	public static void init() throws NotFileException, IOException {
		try {
			testFile = folder.newFile(TEST_FILE_NAME);
		} catch (IOException e) {
			 System.err.println("Error creating temporary test file.");
		}
		testYMFile = YMFile.createFromPath(testFile.getPath());
	}
	
	@Test
	public void testCreateFromPathWithValidPath() throws NotFileException, IOException {
		assertThat(YMFile.createFromPath(testFile.getPath()))
			.isInstanceOf(YMFile.class);
	}
	
	@Test
	public void testCreateFromPathWithInvalidPath() {		
		assertThatThrownBy(() -> YMFile.createFromPath(INVALID_PATH))
			.isInstanceOf(NotFileException.class)
			.hasMessage(NOTFILEEXCEPTION_MESSAGE);
		
		File testDir = null;
		try  {
			testDir = folder.newFolder("TestDirectory");
		} catch (IOException e) {
			 System.err.println("Error creating temporary test directory.");
		}
		String testDirPath = testDir.getPath();
		assertThatThrownBy(() -> YMFile.createFromPath(testDirPath))
			.isInstanceOf(NotFileException.class)
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
