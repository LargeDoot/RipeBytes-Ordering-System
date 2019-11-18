import java.io.*;
import java.util.*;

// TODO Change menu arrays to maps to map pricing and add functions to return prices of food 

public class Menu {

	ArrayList<String[]> menuItemsSplit = new ArrayList<String[]>();
	ArrayList<ArrayList<String>> menuItemsNames = new ArrayList<ArrayList<String>>();
	
	Map<String, Double> menuItemsPrices = new HashMap<String, Double>();
	

	public Menu() {
		
		for (int i = 0; i < 3; i++) {
			
			menuItemsNames.add(new ArrayList<String>());
			
		}
		
		ArrayList<String> menuContentsRaw = importMenu();
		
		splitLists(menuContentsRaw);
		
	}
	
	/**
	 * Returns price of specified menu item
	 * 
	 * @return	double	price of specified menu item
	 */
	public double getPrice(String item) {

		return (menuItemsPrices.get(item));

	}
	
	/**
	 * Returns map of mains and prices
	 * 
	 * @return	ArrayList<String> 
	 */
	public ArrayList<String> getMains() {

		return (menuItemsNames.get(0));

	}	
	
	/**
	 * Returns arrayList of sides
	 * 
	 * @return	ArrayList<String> 
	 */
	public ArrayList<String> getSides() {

		return (menuItemsNames.get(1));

	}
	
	/**
	 * Returns arrayList of drinks
	 * 
	 * @return	ArrayList<String> 
	 */
	public ArrayList<String> getDrinks() {

		return (menuItemsNames.get(2));

	}

	public ArrayList<String> importMenu() {

		/*
		 * Declare a FileReader and BufferedReader for use inside the 'try catch' block
		 * as they must be declared outside of the block
		 */
		FileReader in;
		BufferedReader br;

		// Create an ArrayList to store file contents inside; each line of the file will
		// be a string inside the ArrayList
		ArrayList<String> menuItems = new ArrayList<String>();

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
			//Terminate the program as it cannot perform any functionality with no valid menu.
			System.exit(0);
		}
		
		return(menuItems);
	}
	
	/**
	 * Takes a List of lines from a file, removes blank lines, lines beginning with "//",
	 * splits the remaining lines by ", " and then splits the result by "=" to get a map
	 * of menu items and their prices, where the item name is the key.
	 * 	
	 * @param menuItems	List of lines from a file
	 */
	public void splitLists(ArrayList<String> menuItems) {
		
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
		for (int i = 0; i < menuItems.size(); i++) {

			menuItemsSplit.add(menuItems.get(i).split(", ", 0));

		}
		
		/**
		 * Split each of the split menu items into an item and the corresponding price by
		 * splitting the string by the "=" and casting the price to an Integer.
		 * This is then capitalised and inserted into a map ("NAME", double Price) and the 
		 * names into an ArrayList to be use for displaying the menu in non caps.
		 */
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < menuItemsSplit.get(i).length; j++) {
			
				String[] itemAndPrice = menuItemsSplit.get(i)[j].split("=", 0);
				
				menuItemsPrices.put(itemAndPrice[0].toUpperCase(), Double.parseDouble(itemAndPrice[1]));
				menuItemsNames.get(i).add(itemAndPrice[0]);
			
			}
			
		}

	}

}
