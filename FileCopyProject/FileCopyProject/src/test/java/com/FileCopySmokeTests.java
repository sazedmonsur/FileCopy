package test.java.com;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.com.FileCopy;


public class FileCopySmokeTests {
	
	//File Source and Destination provided by the user
	
	String fileSource = "Original Files/Word_File.docx";
	String fileDestination= "Copied Files/Word_File_copied.docx";
	FileCopy filecopy;
	
	@BeforeEach
	public void Setup() {
		filecopy = new FileCopy();
	}
	
	@AfterEach
	public void TearDown() {
	    
		filecopy.removeFile(fileDestination);
	}
	
	@Test
	public void shouldCopyFileInSpecifiedLocation() throws IOException {
		filecopy.copyFile(fileSource, fileDestination);
	    Assert.assertTrue("Copied file Exists", Paths.get(fileDestination).toFile().exists());
	}
	
	@Test
	public void originalAndCopiedContentShouldMatch() throws IOException {
		Charset charset = Charset.forName("ISO-8859-1");
		filecopy.copyFile(fileSource, fileDestination);
		Assertions.assertEquals(Files.readAllLines(Paths.get(fileDestination), charset), Files.readAllLines(Paths.get(fileSource), charset));
	}
	
	@Test
	public void shouldThrowNoSuchFileExceptionForInvalidPath() throws IOException {
		Assertions.assertThrows(NoSuchFileException.class, () -> {
			 filecopy.copyFile(fileSource, "K:\\InvalidPath");	 
		    });
	}
	
	@Test
	public void shouldThrowFileAlreadyExistsExceptionWhenCopiedFileAlreadyExists() throws IOException {
		Assertions.assertThrows(FileAlreadyExistsException.class, () -> {
			 filecopy.copyFile(fileSource, fileDestination);
			 filecopy.copyFile(fileSource, fileDestination);	
		    });
	}
}
