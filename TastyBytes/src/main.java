import java.util.*;

public class main {

	static final int CONSOLE_WIDTH = 80;

	static Menu foodMenu = new Menu();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<String> foodToOrder = new ArrayList<String>();

		printWelcome();

		System.out.println();
		System.out.println();

		printMenu();

		System.out.println("Hi there, what would you like to order?");
		String userResponse = sc.nextLine().toUpperCase();
		
		foodToOrder = scanUserInput(userResponse);
		
		for (int i = 0; i < foodToOrder.size(); i++) {
			
			System.out.println(foodToOrder.get(i));
			
		}
	}
	
	/**
	 * Takes a user input and checks each word against the menu, if a menu items is found within the user input string
	 * then it is added to an ArrayList and returned after the whole user input is checked.
	 * 
	 * @param userInput	userInput to be checked
	 * @return			ArrayList of matching items
	 */
	public static ArrayList<String> scanUserInput(String userInput) {

		String[] splitUserInput = new String[0];
		ArrayList<String> foodToOrder = new ArrayList<String>();

		splitUserInput = userInput.split(" ", 0);

		for (int i = 0; i < splitUserInput.length; i++) {
			
			
			for (int j = 0; j < foodMenu.getMains().length; j++) {
				if (foodMenu.getMains()[j].toUpperCase().contentEquals(splitUserInput[i])) {
					foodToOrder.add(splitUserInput[i]);
				}
			}
			
			
			for (int j = 0; j < foodMenu.getSides().length; j++) {
				if (foodMenu.getSides()[j].toUpperCase().contentEquals(splitUserInput[i])) {
					foodToOrder.add(splitUserInput[i]);
				}
			}
			
			
			for (int j = 0; j < foodMenu.getDrinks().length; j++) {
				if (foodMenu.getDrinks()[j].toUpperCase().contentEquals(splitUserInput[i])) {
					foodToOrder.add(splitUserInput[i]);
				}
			}
		}

		return foodToOrder;

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
		for (int i = 0; i < foodMenu.getMains().length; i++) {

			printMenuLineFilled(foodMenu.getMains()[i], 2);
		}
		printMenuLineCenter("");

		// Print sides
		printMenuLineCenter("Sides");
		for (int i = 0; i < foodMenu.getSides().length; i++) {

			printMenuLineFilled(foodMenu.getSides()[i], 2);
		}
		printMenuLineCenter("");

		// Print drinks
		printMenuLineCenter("Drinks");
		for (int i = 0; i < foodMenu.getDrinks().length; i++) {

			printMenuLineFilled(foodMenu.getDrinks()[i], 2);
		}
		printMenuLineCenter("");

		printHorizontalLine(1);
	}

	/**
	 * Print a welcome message consisting of the logo inside a box made up of "/"'s
	 */
	public static void printWelcome() {

		printHorizontalLine(2);
		printLogo();
		printMenuLineCenter("");
		printHorizontalLine(2);
	}

	/**
	 * TODO
	 */
	public static void printCurrentOrder() {
		// TODO

	}

	/**
	 * Print a horizontal line of "/"'s of a width defined by the CONSOLE_WIDTH
	 * constant. Can print several lines at once.
	 * 
	 * @param numLines Defines how many lines will be printed
	 */
	public static void printHorizontalLine(int numLines) {

		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < CONSOLE_WIDTH; j++) {

				System.out.print("/");
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
	 * @param contents text that will be printed in the centre of the next line
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
	 * printMenuLineFilled takes a message and prints it to the console with "//"
	 * either side, a price at the end and dots in the space between them. This
	 * makes prices more clear. The contents is left aligned.
	 * 
	 * @param contents contents of the message to be displayed
	 * @param price    price of item on current line
	 */
	public static void printMenuLineFilled(String contents, double price) {

		System.out.print("// ");

		System.out.print(contents);

		// Minus 6 for "// " and " //", 5 for the price, and length of the text being
		// displayed
		for (int i = 0; i < (CONSOLE_WIDTH - 6 - 5 - contents.length()); i++) {

			System.out.print(".");
		}

		// Format the price double to have 2 decimal places
		System.out.printf("£%.02f", price);

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
