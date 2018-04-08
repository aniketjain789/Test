package Appium;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ss {

	public static void main(String[] args) {
	
		List<String> textFiles(String directory)
		{
				 List<String> textFiles = new ArrayList<String>();
				  File dir = new File(directory);
				  for (File file : dir.listFiles()) {
				    if (file.getName().endsWith((".txt"))) {
				      textFiles.add(file.getName());
				    }
				  }
				  return textFiles;
				}
	}
	
}