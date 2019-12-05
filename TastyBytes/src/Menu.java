import java.io.*;
import java.util.*;

/**
 * Responsible for:                                                  <br>
 * - Importing a menu from text file                                 <br>
 * - Splitting the contents up into each menu item                   <br>
 * - Splitting the items from its price and storing themboth in a map<br>
 * - Returning prices of items when given an item name               <br>
 * - Returning a list of mains, sides, or drinks                     <br>
 * 
 * @author Ethan Wilson
 *
 */
public class Menu {

	// ArrayList initialisations to hold menu items
	ArrayList<String[]> menuItemsSplit = new ArrayList<String[]>();
	ArrayList<ArrayList<String>> menuItemsNames = new ArrayList<ArrayList<String>>();

	// Map initialisation to hold the prices of menu items
	Map<String, Double> menuItemsPrices = new HashMap<String, Double>();

	/**
	 * Populate menuItemsNames with empty ArrayLists, import them from the
	 * designated text file, and the contents is split into individual items in 3
	 * categories (mains, sides, drinks).
	 */
	public Menu() {

		// Create empty ArrayLists inside of the menuItemsNames ArrayList
		for (int i = 0; i < 3; i++) {

			menuItemsNames.add(new ArrayList<String>());

		}

		ArrayList<String> menuContentsRaw = importMenu();

		splitLists(menuContentsRaw);

	}

	/**
	 * Returns price of specified menu item
	 * 
	 * @param item
	 *            the name of the item a price is needed for
	 * @return the price of specified menu item
	 */
	double getPrice(String item) {

		return (menuItemsPrices.get(item));

	}

	/**
	 * Gets the mains that were retrieved from the menu.
	 * 
	 * @return an ArrayList of the names of all mains on the menu
	 */
	ArrayList<String> getMains() {

		return (menuItemsNames.get(0));

	}

	/**
	 * Gets the sides that were retrieved from the menu.
	 * 
	 * @return an ArrayList of the names of all sides on the menu
	 */
	ArrayList<String> getSides() {

		return (menuItemsNames.get(1));

	}

	/**
	 * Gets the drinks that were retrieved from the menu.
	 * 
	 * @return an ArrayList of the names of all drinks on the menu
	 */
	ArrayList<String> getDrinks() {

		return (menuItemsNames.get(2));

	}

	/**
	 * Imports the menu from the text file
	 * 
	 * @return an arrayList of strings containing each menu entry
	 */
	ArrayList<String> importMenu() {

		/*
		 * Declare a FileReader and BufferedReader for use inside the 'try catch' block
		 * as they must be declared outside of the block
		 */
		FileReader in;
		BufferedReader br;

		/*
		 * Create an ArrayList to store file contents inside; each line of the file will
		 * be a string inside the ArrayList
		 */
		ArrayList<String> menuItems = new ArrayList<String>();

		try {

			/*
			 * Used
			 * "https://stackoverflow.com/questions/16104616/using-bufferedreader-to-read-
			 * text-file" for help on reading files using BufferedReader.
			 */

			in = new FileReader("src/menuOptions.txt");
			br = new BufferedReader(in);

			String line;

			/*
			 * Reads the next line of the text file on each iteration of the loop, and adds
			 * the line to the menuItems ArrayList. When the bufferedReader reaches the end
			 * of the file and returns null, the loop ends.
			 */
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

			/*
			 * Terminate the program as it cannot perform any functionality with no valid
			 * menu.
			 */
			System.exit(0);
		}

		return (menuItems);
	}

	/**
	 * Takes a List of lines from a file and:
	 * <ul>
	 * <li>removes blank lines,</li>
	 * <li>removes lines beginning with "//",</li>
	 * </ul>
	 * then:
	 * <ul>
	 * <li>splits the remaining lines by ", " and</li>
	 * <li>splits the result by "="</li>
	 * </ul>
	 * This ends up as a map of menu items and their prices, where the item name is
	 * the key, and the price is the value.
	 * 
	 * @param menuItems
	 *            list of lines of text to be split in the format "item=00.00, ..."
	 */
	private void splitLists(ArrayList<String> menuItems) {

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
		for (int i = 0; i < menuItems.size(); i++) {

			menuItemsSplit.add(menuItems.get(i).split(", ", 0));

		}

		/**
		 * Split each of the split menu items into an item and the corresponding price
		 * by splitting the string by the "=" and casting the price to an Integer.
		 */
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < menuItemsSplit.get(i).length; j++) {

				// Set itemAndPrice to contain item name in [0] and item price in [2]
				String[] itemAndPrice = menuItemsSplit.get(i)[j].split("=", 0);

				// Capitalise and insert item name and price into a map ("NAME", price)
				menuItemsPrices.put(itemAndPrice[0].toUpperCase(), Double.parseDouble(itemAndPrice[1]));

				// Add the name to a separate list to be used for displaying the menu.
				menuItemsNames.get(i).add(itemAndPrice[0]);

			}

		}

	}

}
