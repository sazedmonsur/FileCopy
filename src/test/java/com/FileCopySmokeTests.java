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
	
	//Setup method to run before each tests
	@BeforeEach
	public void Setup() {
		filecopy = new FileCopy();
	}
	
	//Teardown method to run after each tests
	@AfterEach
	public void TearDown() {
	    
		filecopy.removeFile(fileDestination);
	}
	
	//Testcase : File should be Copied into the user specified location
	@Test
	public void shouldCopyFileInSpecifiedLocation() throws IOException {
		filecopy.copyFile(fileSource, fileDestination);
	    Assert.assertTrue("Copied file Exists", Paths.get(fileDestination).toFile().exists());
	}
	
	//Testcase : Original file content and Copied file content must match
	@Test
	public void originalAndCopiedContentShouldMatch() throws IOException {
		Charset charset = Charset.forName("ISO-8859-1");
		filecopy.copyFile(fileSource, fileDestination);
		Assertions.assertEquals(Files.readAllLines(Paths.get(fileDestination), charset), Files.readAllLines(Paths.get(fileSource), charset));
	}
	
	// For invalid file source no such file found exception should be thrown
	@Test
	public void shouldThrowNoSuchFileExceptionForInvalidPath() throws IOException {
		Assertions.assertThrows(NoSuchFileException.class, () -> {
			 filecopy.copyFile(fileSource, "K:\\InvalidPath");	 
		    });
	}
	
	//File already exists exception should be displayed if the copied file already exists in the specified destination
	@Test
	public void shouldThrowFileAlreadyExistsExceptionWhenCopiedFileAlreadyExists() throws IOException {
		Assertions.assertThrows(FileAlreadyExistsException.class, () -> {
			 filecopy.copyFile(fileSource, fileDestination);
			 filecopy.copyFile(fileSource, fileDestination);	
		    });
	}
}
