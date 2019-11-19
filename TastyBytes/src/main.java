import java.util.*;

//cheeseburger with fries and a sprite, and also a hotdog meal with mash and tea, and also water

public class main {

	static final int CONSOLE_WIDTH = 70;
	static Menu foodMenu = new Menu();
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();
		
		ArrayList<FoodOrder> order = new ArrayList<FoodOrder>();
		ArrayList<String> singleOrderItems = new ArrayList<String>();
		
		boolean orderFinished = false;
		
		printWelcome();

		System.out.println();
		System.out.println();

		printMenu();

		while (orderFinished == false) {
		
			foodToOrder = getOrder(sc);
	
			int orderSize = foodToOrder.size();
	
			int numMains = foodToOrder.get(0).size();
			int numSides = foodToOrder.get(1).size();
			int numDrinks = foodToOrder.get(2).size();
	
			int numMeals = getNumberMeals(numMains, numSides, numDrinks)[0];
	
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
			
			printCurrentOrder(order, singleOrderItems);
			
			System.out.println("Would you like to order more? (y/n):");
			String userResponse = sc.nextLine();
			if (userResponse.contains("no") || userResponse.contains("n")) {
				
				orderFinished = true;
				
			}
			
		}

		printCurrentOrder(order, singleOrderItems);

		if (order.size() > 0) {
			superSizeMeals(sc, order);
			printCurrentOrder(order, singleOrderItems);
		}
			
		sc.close();
		System.out.printf("%nThe total cost of your order is £%.02f", getFinalPrice(singleOrderItems, order));
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Asks the user if they want to SuperSize any meals in the order and updates the objects 
	 * accordingly.
	 * 
	 * @param	sc	scanner to be parsed
	 */
	public static void superSizeMeals(Scanner sc, ArrayList<FoodOrder> mealOrders) {
		
		System.out.println("Would you like to supersize any of your meals? This will upgrade\n"
				+ "your food to a larger size for only £1! If you would like to supersize \n"
				+ "any of your meals, enter the meal numbers below!:");
		
		String mealsToSuperSize = sc.nextLine().replaceAll("\\D+", " ");
		
		String[] splitList = new String[0];
		
		splitList = mealsToSuperSize.split(" ", 0);
		
		for (int i = 0; i < splitList.length; i++) {
			
			if (!splitList[i].equals("")) {
				
				mealOrders.get(i).superSize();
				
			}
			
		}
		
	}
	
	
	/**
	 * Gets an input from user and scans it to produce a list of keywords such as menu items
	 * 
	 * @param sc	Scanner
	 * @return	foodToOrder is a list of keywords such as menu items
	 */
	public static ArrayList<ArrayList<String>> getOrder(Scanner sc) {
		
		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();
		
		do {
			// Get user input and convert to upper-case
			System.out.println("What would you like to order? (type 'help' for help):");
			String userResponse = sc.nextLine().toUpperCase();
			userResponse = userResponse.replaceAll("\\W+", " ");
	
			// Scan the input and return an ArrayList of ArrayLists (mains, sides, drinks)
			foodToOrder = scanUserInput(userResponse);
		} while (foodToOrder == null);
		
		return foodToOrder;
	}
	
	
	/**
	 * Takes a user input and checks each word against the menu, if a menu items is
	 * found within the user input string then it is added to a 2d ArrayList and
	 * returned after the whole user input is checked. The arrayList contains a list of
	 * mains, sides, and drinks
	 * 
	 * @param userInput
	 *            userInput to be checked
	 * @return ArrayList of matching items
	 */
	public static ArrayList<ArrayList<String>> scanUserInput(String userInput) {

		String[] splitUserInput = new String[0];

		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();

		ArrayList<String> mainsToOrder = new ArrayList<String>();
		ArrayList<String> sidesToOrder = new ArrayList<String>();
		ArrayList<String> drinksToOrder = new ArrayList<String>();

		splitUserInput = userInput.split(" ", 0);

		if (userInput.contains("HELP")) {
			helpInformation();

			return null;
		}

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

		if (mainsToOrder.size() == 0 && sidesToOrder.size() == 0 && drinksToOrder.size() == 0) {

			System.out.println("No valid menu items were found in your order. \nPlease "
					+ "only order food items that are displayed on the menu above.");

			return null;

		}

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

		for (int i = 0; i < order.size(); i++) {

			totalPrice += order.get(i).totalPrice();

		}

		for (int i = 0; i < foodToOrder.size(); i++) {

			totalPrice += foodMenu.getPrice(foodToOrder.get(i));

		}

		return totalPrice;

	}

	/**
	 * Work out which of the arrays is shortest. This will be used to determine how
	 * many meals can be created
	 * 
	 * @param numMains
	 *            number of mains
	 * @param numSides
	 *            number of sides
	 * @param numDrinks
	 *            number of drinks
	 * @return smallest length
	 */
	public static int[] getNumberMeals(int numMains, int numSides, int numDrinks) {

		int[] arrayLengths = new int[] { numMains, numSides, numDrinks };

		Arrays.sort(arrayLengths);

		int[] orderMinMax = new int[] { arrayLengths[0], arrayLengths[2] };

		return orderMinMax;

	}

	/**
	 * Provides information on how to use the program to the user.
	 */
	public static void helpInformation() {

		System.out.println("Help information:\n" 
				+ "- Enter the food you would like to order from the displayed menu\n\n"
				+ "- Multiple food items can be ordered on one line, but if you need more \n"
				+ "  then feel free to order more until you specify that you are done!\n\n"
				+ "- Food ordered will be sorted into meals for your convenience, which is\n"
				+ "  done by taking a main, side and drink in order of entry. For example,\n"
				+ "  if you enter 'burger burger fries mash water water fanta', meal 1\n"
				+ "  would have 'burger, fries, water' and meal 2 would have 'burger, mash\n"
				+ "  and water' with an extra water ordered on its own.\n");

	}

	/**
	 * Print the full contents of the menu; mains, sides and drinks. The menu is
	 * enclosed inside a box of "/"'s
	 */
	public static void printMenu() {

		printHorizontalLine(1);
		printMenuLineCenter("");

		// Print mains
		printMenuLineCenter("Mains");
		for (int i = 0; i < foodMenu.getMains().size(); i++) {

			printMenuLineFilled(foodMenu.getMains().get(i));
		}
		printMenuLineCenter("");

		// Print sides
		printMenuLineCenter("Sides");
		for (int i = 0; i < foodMenu.getSides().size(); i++) {

			printMenuLineFilled(foodMenu.getSides().get(i));
		}
		printMenuLineCenter("");

		// Print drinks
		printMenuLineCenter("Drinks");
		for (int i = 0; i < foodMenu.getDrinks().size(); i++) {

			printMenuLineFilled(foodMenu.getDrinks().get(i));
		}
		printMenuLineCenter("");

		printHorizontalLine(1);
	}

	/**
	 * Print a welcome message consisting of the logo inside a box made up of "/"'s
	 */
	public static void printWelcome() {

		printHorizontalLine(1);
		printLogo();
		printMenuLineCenter("");
		printHorizontalLine(1);
	}

	/**
	 * Prints out the current order to the user, in the form of meals, then items
	 * ordered outside of a meal.
	 * 
	 * @param orderMeals
	 *            The list of meals that are to be ordered
	 * @param orderSingles
	 *            The list of single items to be ordered
	 */
	public static void printCurrentOrder(ArrayList<FoodOrder> orderMeals, ArrayList<String> orderSingles) {

		printHorizontalLine(1);

		// code to print the object list
		for (int i = 0; i < orderMeals.size(); i++) {

			printMealHeading(i+1);

			printOrderLine("Main", orderMeals.get(i).getMain());
			printOrderLine("Side", orderMeals.get(i).getSide());
			printOrderLine("Drink", orderMeals.get(i).getDrink());
			printOrderLine("SuperSize™?", String.valueOf(orderMeals.get(i).isSuperSize()));

			printMenuLineCenter("");

		}

		printMenuLineCenter("Other items:");

		for (int i = 0; i < orderSingles.size(); i++) {

//			for (int j = 0; j < orderSingles.get(i).size(); j++) {
//
//				// System.out.printf("%s, ", orderSingles.get(i).get(j));
//				printMenuLineCenter(orderSingles.get(i).get(j));
//				// System.out.println();
//
//			}
			printMenuLineCenter(orderSingles.get(i));

		}

		printHorizontalLine(1);

	}

	/**
	 * Print a horizontal line of "/"'s of a width defined by the CONSOLE_WIDTH
	 * constant. Can print several lines at once.
	 * 
	 * @param numLines
	 *            Defines how many lines will be printed
	 */
	public static void printHorizontalLine(int numLines) {

		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < CONSOLE_WIDTH; j++) {

				System.out.print("\\");
			}

			System.out.println();

		}

	}

	/**
	 * Print a new line with "//" at the beginning and end, with some text in the
	 * centre.
	 * 
	 * The required number of spaces for either side of the text is worked out by
	 * taking 6 off of the console width to account for the sides of the box i.e.
	 * "// " and " //, then the length of the contents is subtracted, and then the
	 * result is divided by 2 to make it even on each side. The right hand side gets
	 * an extra space when the contents is odd, this is determined using Math.ceil
	 * for the spaces on the right hand side.
	 *
	 * @param contents
	 *            text that will be printed in the centre of the next line
	 */
	public static void printMenuLineCenter(String contents) {

		System.out.print("// ");

		// Work out number of spaces as explained in the above comment
		int spacesLeft = (CONSOLE_WIDTH - 6 - contents.length()) / 2;
		double spacesRight = (CONSOLE_WIDTH - 6 - Math.ceil(contents.length())) / 2;

		// Print left spaces
		for (int i = 0; i < spacesLeft; i++) {

			System.out.print(" ");
		}

		System.out.print(contents);

		// Print right spaces
		for (int i = 0; i < spacesRight; i++) {

			System.out.print(" ");
		}

		System.out.println(" //");

	}

	/**
	 * Same as above function but takes an integer value to be displayed after "Meal
	 * " This is used simply for printing headings in the order confirmation
	 * 
	 * @param mealNum
	 *            number of meal being printed
	 */
	public static void printMealHeading(int mealNum) {

		System.out.print("// ");

		/*
		 * Work out number of spaces as explained in the above comment 6 is taken away
		 * from the console width because this is taking into account the "// "
		 *
		 * 7 is taken away to account for the length of "Meal x:"
		 */
		int spacesLeft = (CONSOLE_WIDTH - 13) / 2;
		double spacesRight = (CONSOLE_WIDTH - 12) / 2;

		// Print left spaces
		for (int i = 0; i < spacesLeft; i++) {

			System.out.print(" ");
		}

		System.out.printf("Meal %d:", mealNum);

		// Print right spaces
		for (int i = 0; i < spacesRight; i++) {

			System.out.print(" ");
		}

		System.out.println(" //");

	}

	/**
	 * printMenuLineFilled takes a message and prints it to the console with "//"
	 * either side, a price at the end and dots in the space between them. This
	 * makes prices more clear. The contents is left aligned.
	 * 
	 * @param contents
	 *            contents of the message to be displayed
	 * @param price
	 *            price of item on current line
	 */
	public static void printMenuLineFilled(String contents) {

		System.out.print("// ");

		System.out.print(contents);

		// Minus 6 for "// " and " //", 5 for the price, and length of the text being
		// displayed
		for (int i = 0; i < (CONSOLE_WIDTH - 6 - 5 - contents.length()); i++) {

			System.out.print(".");
		}

		// Format the price double to have 2 decimal places
		System.out.printf("£%.02f", foodMenu.getPrice(contents.toUpperCase()));

		System.out.println(" //");

	}

	/**
	 * takes a main, side, drink and displays them with a border and "."s between
	 * them
	 * 
	 * @param contents
	 *            contents of the message to be displayed
	 * @param price
	 *            price of item on current line
	 */
	public static void printOrderLine(String foodType, String contents) {

		System.out.print("// ");

		System.out.print(foodType);

		// Minus 6 for "// " and " //", and length of the text being
		// displayed
		for (int i = 0; i < (CONSOLE_WIDTH - 6 - foodType.length() - contents.length()); i++) {

			System.out.print(".");
		}

		// Format the price double to have 2 decimal places
		System.out.print(contents);

		System.out.println(" //");

	}

	// Code to print out the graphic for the welcome screen
	public static void printLogo() {

		// graphic created using
		// http://patorjk.com/software/taag/#p=testall&f=Doom&t=ripeBytes
		printMenuLineCenter("      _           ______       _            ");
		printMenuLineCenter("     (_)          | ___ \\     | |           ");
		printMenuLineCenter(" _ __ _ _ __   ___| |_/ /_   _| |_ ___  ___ ");
		printMenuLineCenter("| '__| | '_ \\ / _ \\ ___ \\ | | | __/ _ \\/ __|");
		printMenuLineCenter("| |  | | |_) |  __/ |_/ / |_| | ||  __/\\__ \\");
		printMenuLineCenter("|_|  |_| .__/ \\___\\____/ \\__, |\\__\\___||___/");
		printMenuLineCenter("       | |                __/ |             ");
		printMenuLineCenter("       |_|               |___/              ");

	}

}
