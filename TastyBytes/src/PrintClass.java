import java.util.ArrayList;

/**
 * Responsible for all the print functions of the ordering system, including
 * formatted messages, lines, boxes, formatted lines of text etc.
 * 
 * @author Ethan Wilson
 *
 */
public class PrintClass {

	//Constant for defining how wide the system will display
	public static final int CONSOLE_WIDTH = 70;

	/**
	 * Provides information on how to use the program to the user.
	 */
	static void helpInformation() {

		System.out
				.println("Help information:\n" + "- Enter the food you would like to order from the displayed menu\n\n"
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
	 * 
	 * @param	foodMenu	the menu to be printed
	 */
	static void printMenu(Menu foodMenu) {

		printHorizontalLine(1);
		printMenuLineBlank();

		// Print mains
		printMenuLineCenter("Mains");
		for (int i = 0; i < foodMenu.getMains().size(); i++) {

			printMenuLineFilled(foodMenu.getMains().get(i), foodMenu);
		}
		printMenuLineBlank();

		// Print sides
		printMenuLineCenter("Sides");
		for (int i = 0; i < foodMenu.getSides().size(); i++) {

			printMenuLineFilled(foodMenu.getSides().get(i), foodMenu);
		}
		printMenuLineBlank();

		// Print drinks
		printMenuLineCenter("Drinks");
		for (int i = 0; i < foodMenu.getDrinks().size(); i++) {

			printMenuLineFilled(foodMenu.getDrinks().get(i), foodMenu);
		}
		printMenuLineBlank();

		printHorizontalLine(1);
	}

	/**
	 * Print a welcome message consisting of the logo inside a box made up of "/"'s
	 * 
	 * @param	foodMenu	the menu to be printed
	 */
	static void printWelcome(Menu foodMenu) {

		printHorizontalLine(1);
		printLogo();
		printMenuLineBlank();
		printHorizontalLine(1);

		System.out.println();
		System.out.println();

		printMenu(foodMenu);
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
	static void printCurrentOrder(ArrayList<FoodOrder> orderMeals, ArrayList<String> orderSingles) {

		printHorizontalLine(1);

		printMenuLineBlank();
		printMenuLineCenter("Your Current Order:");
		printMenuLineBlank();
		
		// code to print the object list
		for (int i = 0; i < orderMeals.size(); i++) {

			printMealHeading(i + 1, orderMeals.get(i));

			//Print the contents of the meal "main" followed by the option
			printOrderLine("Main", orderMeals.get(i).getMain());
			printOrderLine("Side", orderMeals.get(i).getSide());
			printOrderLine("Drink", orderMeals.get(i).getDrink());
			printOrderLine("SuperSize™?", String.valueOf(orderMeals.get(i).isSuperSize()));

			printMenuLineBlank();

		}

		printMenuLineCenter("Other items:");

		for (int i = 0; i < orderSingles.size(); i++) {

			printMenuLineCenter(orderSingles.get(i));

		}

		printMenuLineBlank();
		printHorizontalLine(1);

	}

	/**
	 * Print a horizontal line of "/"'s of a width defined by the CONSOLE_WIDTH
	 * constant. Can print several lines at once.
	 * 
	 * @param numLines
	 *            Defines how many lines will be printed
	 */
	static void printHorizontalLine(int numLines) {

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
	static void printMenuLineCenter(String contents) {

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
	 * @param meal	the meal that is about to be printed (used for price)
	 */
	static void printMealHeading(int mealNum, FoodOrder meal) {

		System.out.print("// ");

		/*
		 * Work out number of spaces as explained in the above comment 6 is taken away
		 * from the console width because this is taking into account the "// "
		 *
		 * 7 is taken away to account for the length of "Meal x:"
		 */
		int spacesLeft = (CONSOLE_WIDTH - 21) / 2;
		double spacesRight = (CONSOLE_WIDTH - 21) / 2;

		// Print left spaces
		for (int i = 0; i < spacesLeft; i++) {

			System.out.print(" ");
		}

		System.out.printf("Meal %02d - £%2.02f:", mealNum, meal.totalPrice());

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
	static void printMenuLineFilled(String contents, Menu foodMenu) {

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
	static void printOrderLine(String foodType, String contents) {

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

	/**
	 *  Code to print out the graphic for the welcome screen
	 */
	static void printLogo() {

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
	
	/**
	 * Code to print a blank menu line
	 */
	static void printMenuLineBlank() {
		
		printMenuLineCenter("");
		
	}

}
