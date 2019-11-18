import java.util.*;

//cheeseburger with fries and a sprite, and also a hotdog meal with mash and tea, and also water

public class main {

	static final int CONSOLE_WIDTH = 70;

	static Menu foodMenu = new Menu();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();
		ArrayList<FoodOrder> order = new ArrayList<FoodOrder>();

		printWelcome();

		System.out.println();
		System.out.println();

		printMenu();

		//Get user input and convert to upper-case
		System.out.println("Hi there, what would you like to order?");
		String userResponse = sc.nextLine().toUpperCase();
		userResponse = userResponse.replaceAll("[-+.^:,]", "");
		
		//Scan the input and return an ArrayList of ArrayLists (mains, sides, drinks)
		foodToOrder = scanUserInput(userResponse);
		int orderSize = foodToOrder.size();
		
		
		int numMains = foodToOrder.get(0).size();
		int numSides = foodToOrder.get(1).size();
		int numDrinks = foodToOrder.get(2).size();
		
		int numMeals = getNumberMeals(numMains, numSides, numDrinks)[0];
		
		for (int i = 0; i < numMeals; i++) {
			
			order.add(new FoodOrder(foodToOrder.get(0).get(i), 
									foodToOrder.get(1).get(i), 
									foodToOrder.get(2).get(i),
									foodMenu));
			
		}
		
		// code to print the object list
		for (int i = 0; i<order.size();i++) {
			
			System.out.printf("Meal %d:%n", i+1);
			
			System.out.printf("Main: %s%n", order.get(i).getMain());
			System.out.printf("Side: %s%n", order.get(i).getSide());
			System.out.printf("Drink: %s%n", order.get(i).getDrink());
			System.out.println();
			
		}
		
		for (int i = numMeals; i > 0; i--) {
			
			for (int j = 0; j < orderSize; j++) {
			
				foodToOrder.get(j).remove(i-1);
			
			}	
				
		}
		
		System.out.println("Other items:");
		
		for (int i = 0; i < orderSize; i++) {
					
			for (int j = 0; j < foodToOrder.get(i).size(); j++) {
					
				System.out.println(foodToOrder.get(i).get(j));
					
			}	
						
		}
		
		System.out.printf("%nThe total cost of your order is £%.02f", getFinalPrice(foodToOrder, order));
	}
	
	/**
	 * Takes a user input and checks each word against the menu, if a menu items is found within the user input string
	 * then it is added to an ArrayList and returned after the whole user input is checked.
	 * 
	 * @param userInput	userInput to be checked
	 * @return			ArrayList of matching items
	 */
	public static ArrayList<ArrayList<String>> scanUserInput(String userInput) {

		String[] splitUserInput = new String[0];
		
		ArrayList<ArrayList<String>> foodToOrder = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> mainsToOrder = new ArrayList<String>();
		ArrayList<String> sidesToOrder = new ArrayList<String>();
		ArrayList<String> drinksToOrder = new ArrayList<String>();

		splitUserInput = userInput.split(" ", 0);

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
		
		foodToOrder.add(mainsToOrder);
		foodToOrder.add(sidesToOrder);
		foodToOrder.add(drinksToOrder);
		
		return foodToOrder;

	}
	
	
	/**
	 * Works out the price of all meals, and single items that are to be ordered.
	 * 
	 * @param foodToOrder	list of any single items
	 * @param order			list of any meals
	 * @return	double		price of all food in order
	 */
	public static double getFinalPrice(ArrayList<ArrayList<String>> foodToOrder, 
									 ArrayList<FoodOrder> order) {
		
		double totalPrice = 0;
		
		for (int i = 0;i < order.size(); i++) {
			
			totalPrice += order.get(i).totalPrice();
			
		}
		
		for (int i = 0; i < foodToOrder.size(); i++) {
			
			for (int j = 0; j <foodToOrder.get(i).size(); j++) {
				
				totalPrice += foodMenu.getPrice(foodToOrder.get(i).get(j));
				
			}
			
		}
		
		return totalPrice;
		
	}
	
	/**
	 * Work out which of the arrays is shortest. This will be used to determine how many meals 
	 * can be created
	 * 
	 * @param numMains	number of mains
	 * @param numSides	number of sides
	 * @param numDrinks	number of drinks
	 * @return			smallest length
	 */
	public static int[] getNumberMeals(int numMains, int numSides, int numDrinks) {
		
		int[] arrayLengths = new int[] {numMains, numSides, numDrinks};
		
		Arrays.sort(arrayLengths);
		
		int[] orderMinMax = new int[] {arrayLengths[0], arrayLengths[2]};
		
		return orderMinMax;
		
		
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
