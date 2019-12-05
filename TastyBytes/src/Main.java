import java.util.*;

//cheeseburger with fries and a sprite, and also a hotdog meal with mash and tea, and also water

/**
 * The main class.
 * 
 * @author Ethan Wilson
 *
 */
public class Main {

	// Create a menu object for use
	static Menu foodMenu = new Menu();

	/**
	 * main method
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Initialisations
		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();
		ArrayList<FoodOrder> order = new ArrayList<FoodOrder>();
		ArrayList<String> singleOrderItems = new ArrayList<String>();

		boolean orderFinished = false;

		// Print the welcome message and the menu
		PrintClass.printWelcome(foodMenu);

		// Start the ordering process
		while (orderFinished == false) {

			foodToOrder = getOrder(sc);

			// Create some variables for the lengths of all the lists to save repeating
			int orderSize = foodToOrder.size();

			int numMains = foodToOrder.get(0).size();
			int numSides = foodToOrder.get(1).size();
			int numDrinks = foodToOrder.get(2).size();

			// Set numMeals to the shortest of the 3 lists ([0] because an array is returned
			int numMeals = getNumberMeals(numMains, numSides, numDrinks)[0];

			/*
			 * Loop through the number of meals determined above, and add a main, side and
			 * drink to new FoodObject orders.
			 */
			for (int i = 0; i < numMeals; i++) {

				order.add(new FoodOrder(foodToOrder.get(0).get(i), foodToOrder.get(1).get(i), foodToOrder.get(2).get(i),
						foodMenu));

			}

			// Add any items NOT part of a meal to the singleOrderItems list
			for (int i = 0; i < orderSize; i++) {

				for (int j = numMeals; j < foodToOrder.get(i).size(); j++) {

					singleOrderItems.add(foodToOrder.get(i).get(j));

				}

			}

			//Print the order as is
			PrintClass.printCurrentOrder(order, singleOrderItems);

			//Set orderFinished to true if the user has finished the order
			System.out.println("Would you like to order more? (y/n):");
			String userResponse = sc.nextLine();
			if (userResponse.contains("no") || userResponse.contains("n")) {

				orderFinished = true;

			}

		}

		// Print the complete order
		PrintClass.printCurrentOrder(order, singleOrderItems);

		// If there are any meals, ask the user if they want to SuperSize any of them
		if (order.size() > 0) {
			superSizeMeals(sc, order);
			PrintClass.printCurrentOrder(order, singleOrderItems);
		}

		System.out.printf("%nThe total cost of your order is £%.02f%n", getFinalPrice(singleOrderItems, order));

		// Ask the user if they need to edit order, and keep asking until they decline
		while (editOrder(sc, order, singleOrderItems) == true)
			;

		// Print the final Order
		PrintClass.printCurrentOrder(order, singleOrderItems);

		sc.close();
	}

	///////////////////////////end of main /////////////////////////////////

	/**
	 * Used to verify is a given String is on the menu by simply comparing each list
	 * of menu items (mains, sides, drinks) to the String parsed to it.
	 *
	 * @param input
	 *            item to be checked
	 * @return true if it is on the menu, else false
	 */
	public static boolean verifyInputIsOnMenu(String input) {

		for (int i = 0; i < foodMenu.getMains().size(); i++) {
			if (foodMenu.getMains().get(i).toUpperCase().contentEquals(input)) {
				return true;
			}
		}

		for (int i = 0; i < foodMenu.getSides().size(); i++) {
			if (foodMenu.getSides().get(i).toUpperCase().contentEquals(input)) {
				return true;
			}
		}

		for (int i = 0; i < foodMenu.getDrinks().size(); i++) {
			if (foodMenu.getDrinks().get(i).toUpperCase().contentEquals(input)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Asks the user if they want to edit any part of their order before placing the
	 * order.
	 * 
	 * @param sc
	 *            scanner to be parsed
	 * @param mealList
	 *            the list of meals available for edit
	 * @param singleOrderItems
	 *            the list of all items available for edit that are not part of a
	 *            meal
	 * @return true or false depending if the user is finished editing or not (false
	 *         indicates finished)
	 */
	public static boolean editOrder(Scanner sc, ArrayList<FoodOrder> mealList, ArrayList<String> singleOrderItems) {

		System.out.println("Would you like to place your order and pay, or edit your order?");
		String userResponse = sc.nextLine();

		// Check if the user input contains "edit" at any point
		if (userResponse.contains("edit")) {

			System.out.println("Would you like to edit a meal or a single item?");
			userResponse = sc.nextLine();

			// Check if the user input contains "meal" at any point
			if (userResponse.contains("meal")) {

				// Get meal number to edit
				System.out.println("Enter the meal number you wish to edit:");
				int editMeal = sc.nextInt();
				sc.nextLine(); // Clear the return character

				// Get which part of meal to edit
				System.out.println("Would you like to edit the main, side or drink?:");
				userResponse = sc.nextLine();

				// Variable to hold the new value
				String newValue;

				// Get new value and validate it
				do {
					System.out.println(
							"What would you like to change this to? " + "(Please enter a valid option from the menu):");
					newValue = sc.nextLine().toUpperCase();
				} while (verifyInputIsOnMenu(newValue) == false);

				// Set new value depending on which part of the meal is to be edited
				if (userResponse.contains("main")) {
					mealList.get(editMeal - 1).setMain(newValue.toUpperCase());

				} else if (userResponse.contains("side")) {
					mealList.get(editMeal - 1).setSide(newValue.toUpperCase());

				} else if (userResponse.contains("drink")) {
					mealList.get(editMeal - 1).setDrink(newValue.toUpperCase());
				}

			} else if (userResponse.contains("item") || userResponse.contains("single")) {

				// Variable to hold the value to delete
				String itemToDelete;

				// Get the item to delete and validate it
				do {
					System.out.println("Which single items would you like to remove?:");
					itemToDelete = sc.nextLine().toUpperCase();
				} while (verifyInputIsOnMenu(itemToDelete) == false);

				// Remove item
				singleOrderItems.remove(itemToDelete);
			}

			// return true to continue editing
			return true;

		} else {
			// return false to stop the edit loop, as the user indicated they are done
			// editing.
			return false;
		}

	}

	/**
	 * Asks the user if they want to SuperSize any meals in the order and updates
	 * the objects accordingly.
	 * 
	 * @param sc
	 *            scanner for use in method
	 * @param mealOrders
	 *            ArrayList of meals
	 */
	public static void superSizeMeals(Scanner sc, ArrayList<FoodOrder> mealOrders) {

		System.out.println("Would you like to supersize any of your meals? This will upgrade\n"
				+ "your food to a larger size for only £1! If you would like to supersize \n"
				+ "any of your meals, enter the meal numbers below!:");

		// Replace all non-digits with spaces ready for splitting
		String mealsToSuperSize = sc.nextLine().replaceAll("\\D+", " ");

		// Split mealsToSuperSize by spaces to get a list of numbers to superSize
		String[] splitList = mealsToSuperSize.split(" ", 0);

		/*
		 * Loop through the splitList and superSize meals designated by the user as long
		 * as the items does not equal ""
		 */
		for (int i = 0; i < splitList.length; i++) {

			if (!splitList[i].equals("")) {

				mealOrders.get(Integer.parseInt(splitList[i]) - 1).superSize();

			}

		}

	}

	/**
	 * Gets an input from user and then scans it {@link scanUserInput} to produce a
	 * list of keywords such as menu items
	 * 
	 * @param sc
	 *            scanner to be used in method
	 * @return foodToOrder is a list of keywords such as menu items
	 */
	public static ArrayList<ArrayList<String>> getOrder(Scanner sc) {

		// Array to store the food to be ordered (mains, sides and drinks)
		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();

		do {
			// Get user input and convert to upper-case
			System.out.println("What would you like to order? (type 'help' for help):");
			String userResponse = sc.nextLine().toUpperCase();

			// Replace all non-word characters with spaces
			userResponse = userResponse.replaceAll("\\W+", " ");

			// Scan the input and return an ArrayList of ArrayLists (mains, sides, drinks)
			foodToOrder = scanUserInput(userResponse);
		} while (foodToOrder == null);

		return foodToOrder;
	}

	/**
	 * Takes a user input and checks each word against the menu, if a menu items is
	 * found within the user input string then it is added to a 2d ArrayList and
	 * returned after the whole user input is checked. The arrayList contains a list
	 * of mains, sides, and drinks
	 * 
	 * @param userInput
	 *            userInput to be checked
	 * @return ArrayList of matching items
	 */
	public static ArrayList<ArrayList<String>> scanUserInput(String userInput) {

		// Create empty array to hold the split input
		String[] splitUserInput = new String[0];

		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();

		ArrayList<String> mainsToOrder = new ArrayList<String>();
		ArrayList<String> sidesToOrder = new ArrayList<String>();
		ArrayList<String> drinksToOrder = new ArrayList<String>();

		// Split the input
		splitUserInput = userInput.split(" ", 0);

		// If the user asks for help run the help function and return null to restart
		if (userInput.contains("HELP")) {
			PrintClass.helpInformation();

			return null;
		}

		/*
		 * Loop through all the items on the menu and compare the input, if it matches
		 * then add it to the corresponding mainsToOrder, sidesToOrder etc list.
		 */
		for (int i = 0; i < splitUserInput.length; i++) {

			for (int j = 0; j < foodMenu.getMains().size(); j++) {
				if (foodMenu.getMains().get(j).toUpperCase().contentEquals(splitUserInput[i])) {
					mainsToOrder.add(splitUserInput[i]);
				}
			}

			for (int j = 0; j < foodMenu.getSides().size(); j++) {
				if (foodMenu.getSides().get(j).toUpperCase().contentEquals(splitUserInput[i])) {
					sidesToOrder.add(splitUserInput[i]);
				}
			}

			for (int j = 0; j < foodMenu.getDrinks().size(); j++) {
				if (foodMenu.getDrinks().get(j).toUpperCase().contentEquals(splitUserInput[i])) {
					drinksToOrder.add(splitUserInput[i]);
				}
			}
		}

		// If nothing was found, print an error and return null to restart the order
		if (mainsToOrder.size() == 0 && sidesToOrder.size() == 0 && drinksToOrder.size() == 0) {

			System.out.println("No valid menu items were found in your order. \nPlease "
					+ "only order food items that are displayed on the menu above.");

			return null;

		}

		// Add all the sub-lists to the main foodToOrder list to be returned
		foodToOrder.add(mainsToOrder);
		foodToOrder.add(sidesToOrder);
		foodToOrder.add(drinksToOrder);

		return foodToOrder;

	}

	/**
	 * Works out the price of all meals, and single items that are to be ordered.
	 * 
	 * @param foodToOrder
	 *            list of any single items
	 * @param order
	 *            list of any meals
	 * @return double price of all food in order
	 */
	public static double getFinalPrice(ArrayList<String> foodToOrder, ArrayList<FoodOrder> order) {

		double totalPrice = 0;

		// Loop through the list of meals and call the totalPrice function of each
		for (int i = 0; i < order.size(); i++) {

			totalPrice += order.get(i).totalPrice();

		}

		// Loop through all items not in meals and add their price on to the total
		for (int i = 0; i < foodToOrder.size(); i++) {

			totalPrice += foodMenu.getPrice(foodToOrder.get(i));

		}

		return totalPrice;

	}

	/**
	 * Work out which of the arrays is shortest, and which is longest. This will be
	 * used to determine how many meals can be created
	 * 
	 * @param numMains
	 *            number of mains
	 * @param numSides
	 *            number of sides
	 * @param numDrinks
	 *            number of drinks
	 * @return smallest length and longest length in an array[2]
	 */
	public static int[] getNumberMeals(int numMains, int numSides, int numDrinks) {

		int[] arrayLengths = new int[] { numMains, numSides, numDrinks };

		Arrays.sort(arrayLengths);

		int[] orderMinMax = new int[] { arrayLengths[0], arrayLengths[2] };

		return orderMinMax;

	}

}
