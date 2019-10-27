import java.io.*;
import java.util.ArrayList;
import java.util.List;

// TODO Change menu arrays to maps to map pricing and add functions to return prices of food 

public class Menu {

	List<String[]> menuItemsSplit = new ArrayList<String[]>();

	public Menu() {

		importMenu();

	}
	
	/**
	 * Returns arrayList of mains
	 * 
	 * @return	ArrayList<String> 
	 */
	public String[] getMains() {

		return (menuItemsSplit.get(0));

	}
	
	/**
	 * Returns arrayList of sides
	 * 
	 * @return	ArrayList<String> 
	 */
	public String[] getSides() {

		return (menuItemsSplit.get(1));

	}
	
	/**
	 * Returns arrayList of drinks
	 * 
	 * @return	ArrayList<String> 
	 */
	public String[] getDrinks() {

		return (menuItemsSplit.get(2));

	}

	public void importMenu() {

		/*
		 * Declare a FileReader and BufferedReader for use inside the 'try catch' block
		 * as they must be declared outside of the block
		 */
		FileReader in;
		BufferedReader br;

		// Create an ArrayList to store file contents inside; each line of the file will
		// be a string inside the ArrayList
		List<String> menuItems = new ArrayList<String>();

		try {
			/*
			 * Used
			 * https://stackoverflow.com/questions/16104616/using-bufferedreader-to-read-
			 * text-file for help on reading files using BufferedReader.
			 */

			in = new FileReader("src/menuOptions.txt");
			br = new BufferedReader(in);

			String line;

			while ((line = br.readLine()) != null) {
				menuItems.add(line);

			}

			br.close();

		}
		// Catch and IOExceptions while reading the file and creating the FileReader
		catch (IOException e) {
			System.out.println(
					"File \"menuOptions.txt\" cannot be found, please ensure the file is present then try again.");
			System.out.println(e);
			return;
		}

		/*
		 * Loop through contents of the menu file, and remove any blank lines, or any
		 * lines beginning with "//". This will get the ArrayList ready for separation
		 * into the separate menu items.
		 */
		for (int i = 0; i < menuItems.size(); i++) {
			String currentLine = menuItems.get(i);

			if (currentLine.equals("") || currentLine.startsWith("//")) {
				menuItems.remove(i);

				// Decrement "i" because an entry had been removed.
				i--;

			}

		}

		/*
		 * Split each line of menuItems and add a new ArrayList of String Arrays with
		 * the split data in it. This takes the form of menuItemsOutput[ 0["main1", ...
		 * ], 1["side1", ...], 2["drink1", ...] ]
		 */
		// TODO Add constant or .length to loop line
		for (int i = 0; i < 3; i++) {

			menuItemsSplit.add(menuItems.get(i).split(", ", 0));

		}

	}

}
