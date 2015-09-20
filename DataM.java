import java.io.*;
import java.util.*;
import java.text.*;

class DataM {
	
	//paths to save locations
	static String stumpsPath = "stumps.txt";
	static String conversationPath = System.getProperty("user.home");
	
	
	static String separator = System.getProperty("file.separator");
	
	static void saveConversation(List<String> conversation) {
		
		//find date info
		DateFormat yearFormat = new SimpleDateFormat("yyyy");
		DateFormat monthFormat = new SimpleDateFormat("MM");
		DateFormat dayFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		String year = yearFormat.format(date);
		String month = monthFormat.format(date);
		String day = dayFormat.format(date);
		
		try {
			
			//create the directory in needed.
			String fPath=conversationPath+separator+"QuandaSparks"+separator+GUI.currentUser+separator+year+separator+month;
			
			// Create multiple directories
  			boolean success = (new File(fPath)).mkdirs();
  			if (success) {
 				System.out.println("Directories: " + fPath + " created");
  			}  
			
			FileWriter convFile = new FileWriter(fPath+separator+day+".txt", true);
			BufferedWriter writer = new BufferedWriter(convFile);
			//loop through elements of stumps array
			writer.write("----------");
			writer.newLine();
			for(int i=0;i<conversation.size();i++) {
				writer.write(conversation.get(i));
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        conversation.clear();
	}
	
	//save all stumps found to a document
	static void writeStumps(List<String> stumps) {
		System.out.println(stumps);
		try {
			FileWriter stumpFile = new FileWriter(stumpsPath, true);
			BufferedWriter writer = new BufferedWriter(stumpFile);
			//loop through elements of stumps array
			writer.write("----------\n");
			for(int i=0;i<stumps.size();i++) {
				writer.write(stumps.get(i));
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//It reads a data file, creating a list of lines
	static List<String> readFile(String filepath) {
		List<String> brain = new ArrayList<String>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filepath));
			int lineCount = 0;
		    String line;
		    
		    while (( line = bf.readLine()) != null) {
		    	 brain.add(line);
		    	 lineCount++;
		    }
		    bf.close();
		} catch (IOException e) {
			System.out.println("IO Error Occurred: " + e.toString());
		}
		return brain;
	}
}
