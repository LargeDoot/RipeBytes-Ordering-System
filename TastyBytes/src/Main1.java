import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main1 {

	public static void main(String[] args) {
		fileReader();
	}
	
	public static List<String> fileReader() {
		
		/*Declare a FileReader and BufferedReader for use inside the 'try catch' block as they must be 
		 *declared outside of the block */
		FileReader in;
		BufferedReader br;
		 
		//Create an ArrayList to store file contents inside; each line of the file will be a string inside the ArrayList
		List<String> menuItems = new ArrayList<String>();
		List<String[]> menuItemsOutput = new ArrayList<String[]>();
		
		try { 
			/* Used https://stackoverflow.com/questions/16104616/using-bufferedreader-to-read-text-file 
			 *for help on reading files using BufferedReader.*/

			in = new FileReader("menuOptions.txt");
			br = new BufferedReader(in);
			
			String line;
			
			while ((line = br.readLine()) != null) {
				menuItems.add(line);
				
			}
			
			br.close();
			
		} 
		//Catch and IOExceptions while reading the file and creating the FileReader
		catch (IOException e) {  
			System.out.println("File \"menuOptions.txt\", please ensure the file is present then try again.");
			return (null);
		}
		
		
		/*
		 * Loop through contents of the menu file, and remove any blank lines, or any lines beginning with "//".
		 * This will get the ArrayList ready for separation into the separate menu items.
		 */
		for (int i = 0; i < menuItems.size(); i++) {
			String currentLine = menuItems.get(i);
			
			if (currentLine.equals("") || currentLine.startsWith("//")) {
				menuItems.remove(i);
				
				//Decrement "i" because an entry had been removed.
				i--;
				
			}
			
		}
		// TODO Remove print lines after here.
		
		/*
		 * Split each line of menuItems and for a new ArrayList of String Arrays with the split data in it.
		 * This takes the form of menuItemsOutput[ 0["main1", ... ], 1["side1", ...], 2["drink1", ...] ]
		 */
		menuItemsOutput.add(menuItems.get(0).split(",", 0));
		

		for (int i = 0; i < menuItemsOutput.size(); i++) {
			
			for (int j = 0; j < menuItemsOutput.get(i).length; j++) {
				
				System.out.println(menuItemsOutput.get(i)[j]);
				
			}
			
		}
		
		//Return populated ArrayList filled with file contents
		return (menuItems);
		
		
	}
	

}
