package main.java.com;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCopy {

//Function to copy a file

	public void copyFile(String src, String dest) throws IOException {
				
		Path originalPath = Paths.get(src);
		
		Path copied = Paths.get(dest);
		
		Files.copy(originalPath, copied);
		
		System.out.println("File Copied Successfully");
		
		//try {
		
		//Files.copy(originalPath, copied);
					
	//	} catch(FileAlreadyExistsException e){
			
		//    System.out.println("File Already Exist");
		    
		//} catch (IOException e) {
		    
			//something else went wrong
		    
		//	e.printStackTrace();
		
		//}
	}
	
	//Function to remove the copied file
	
	public void removeFile(String filepath) {
		
		Path removeFilePath = Paths.get(filepath);
		
		try {
			
			Files.deleteIfExists(removeFilePath);
			
			System.out.println("File Removed Successfully");
		
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
	}
}
