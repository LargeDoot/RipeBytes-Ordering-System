
public class main {
	
	static final int CONSOLE_WIDTH = 80;

	public static void main(String[] args) {
		
		Menu foodMenu = new Menu();
		
		printWelcome();
		
		printMenuLineCenter("Mains");
		
		for (int i = 0; i < foodMenu.getMains().length; i++) {
			
			printMenuLineFilled(foodMenu.getMains()[i], 2);
			
		}
		printMenuLineCenter("");
		
		printMenuLineCenter("Sides");
		
		for (int i = 0; i < foodMenu.getSides().length; i++) {
			
			printMenuLineFilled(foodMenu.getSides()[i], 2);
			
		}
		printMenuLineCenter("");
		
		printMenuLineCenter("Drinks");
		
		for (int i = 0; i < foodMenu.getDrinks().length; i++) {
			
			printMenuLineFilled(foodMenu.getDrinks()[i], 2);
			
		}
		printMenuLineCenter("");
		
	}
	
	public static void printWelcome() {
		
		printHorizontalLine(2);
		printLogo();
		printMenuLineCenter("");
		printHorizontalLine(2);
	}
	
	public static void printMenu() {
		
		
	}
	
	public static void printCurrentOrder() {
		
		
	}
	
	public static void printHorizontalLine (int numLines) {
		
		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < CONSOLE_WIDTH; j++) {
				
				System.out.print("*");
			}
			
			System.out.println();
		
		}
		
	}
	
	public static void printMenuLineCenter (String contents) {
		
		System.out.print("** ");
		
		/*
		 *  Work out the required number of spaces for either side of the text. 
		 *  This is worked out by taking 6 off of the console width to account for the sides of the box i.e. "** " and " **,
		 *  then the length of the contents is subtracted, and then the result is divided by 2 to make it even on each side.
		 *  The right hand side gets an extra space when the contents is odd, this is determined using Math.ceil for the spaces
		 *  on the right hand side.
		 */
		int spacesLeft = (CONSOLE_WIDTH - 6 - contents.length()) / 2;
		double spacesRight = (CONSOLE_WIDTH - 6 - Math.ceil(contents.length())) / 2;
		
		for (int i = 0; i < spacesLeft; i++) {
			
			System.out.print(" ");
		}
		
		System.out.print(contents);
		
		for (int i = 0; i < spacesRight; i++) {
			
			System.out.print(" ");
		}
		
		System.out.println(" **");
		
	}
	
	/**
	 * printMenuLineFilled takes a message and prints it to the console with "**" either side,
	 * a price at the end and dots in the space between them. This makes prices more clear.
	 * 
	 * @param contents	contents of the message to be displayed 
	 * @param price		price of item on current line
	 */
	public static void printMenuLineFilled (String contents, double price) {
		
		System.out.print("** ");
		
		System.out.print(contents);
		
		// TODO clean this up
		for (int i = 0; i < (CONSOLE_WIDTH - 6 - 5 - contents.length()); i++) {
			
			System.out.print(".");
		}
		
		System.out.printf("£%.02f", price);
		
		System.out.println(" **");
		
	}
	

	 //Code to print out the graphic for the welcome screen
	public static void printLogo () {
		
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
