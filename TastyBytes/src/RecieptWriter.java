import java.io.*;
import java.util.ArrayList;

/**
 * Responsible for writing receipts for an ordering system
 * 
 * @author Ethan
 *
 */
public class RecieptWriter {

	static Writer writer = null;

	/**
	 * Used guidance from <a href=
	 * "https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java">
	 * 
	 * Writes a receipt to a file
	 * 
	 * @param file
	 *            name of file to be created and written to
	 * @param contents
	 */
	static void writeReciept(String file, ArrayList<FoodOrder> orderMeals, ArrayList<String> orderSingles, double totalPrice) {

		int orderNumber = getOrderNumber();
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("orderReciepts/TastyBytes_Order_" + orderNumber + ".txt"), "utf-8"));

			writeCurrentOrder(orderMeals, orderSingles, totalPrice);
			
			System.out.println("Reciept has been successfully written!");

		} catch (IOException e) {
			System.out.println("Failed to write to file");
			e.printStackTrace();

		} finally {

			try {
				writer.close();
			} catch (IOException | NullPointerException e) {
				/* Ignore */}

		}
		
		incrementOrderNumber(orderNumber);

	}

	/**
	 * Prints out the current order to the user, in the form of meals, then items
	 * ordered outside of a meal.
	 * 
	 * @param orderMeals
	 *            The list of meals that are to be ordered
	 * @param orderSingles
	 *            The list of single items to be ordered
	 * @throws IOException
	 */
	static void writeCurrentOrder(ArrayList<FoodOrder> orderMeals, ArrayList<String> orderSingles, double totalPrice) throws IOException {

		writeHorizontalLine(1);

		writeMenuLineBlank();
		writeMenuLineCenter(String.format("Your order %03d - Total Price £%.02f:", getOrderNumber(), totalPrice));
		writeMenuLineBlank();

		// code to print the object list
		for (int i = 0; i < orderMeals.size(); i++) {

			writeMealHeading(i + 1, orderMeals.get(i));

			// Print the contents of the meal "main" followed by the option
			writeOrderLine("Main", orderMeals.get(i).getMain());
			writeOrderLine("Side", orderMeals.get(i).getSide());
			writeOrderLine("Drink", orderMeals.get(i).getDrink());
			writeOrderLine("SuperSize™?", String.valueOf(orderMeals.get(i).isSuperSize()));

			writeMenuLineBlank();

		}

		writeMenuLineCenter("Other items:");

		for (int i = 0; i < orderSingles.size(); i++) {

			writeMenuLineCenter(orderSingles.get(i));

		}

		writeMenuLineBlank();
		writeHorizontalLine(1);

	}

	/**
	 * Print a horizontal line of "/"'s of a width defined by the CONSOLE_WIDTH
	 * constant. Can print several lines at once.
	 * 
	 * @param numLines
	 *            Defines how many lines will be printed
	 * @throws IOException
	 */
	static void writeHorizontalLine(int numLines) throws IOException {

		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < PrintClass.CONSOLE_WIDTH; j++) {

				writer.write("\\");
			}

			writer.write("\n");

		}

	}

	/**
	 * Same as above function but takes an integer value to be displayed after "Meal
	 * " This is used simply for printing headings in the order confirmation
	 * 
	 * @param mealNum
	 *            number of meal being printed
	 * @param meal
	 *            the meal that is about to be printed (used for price)
	 * @throws IOException
	 */
	static void writeMealHeading(int mealNum, FoodOrder meal) throws IOException {

		writer.write("// ");

		/*
		 * Work out number of spaces as explained in the above comment 6 is taken away
		 * from the console width because this is taking into account the "// "
		 *
		 * 7 is taken away to account for the length of "Meal x:"
		 */
		int spacesLeft = (PrintClass.CONSOLE_WIDTH - 21) / 2;
		double spacesRight = (PrintClass.CONSOLE_WIDTH - 21) / 2;

		// Print left spaces
		for (int i = 0; i < spacesLeft; i++) {

			writer.write(" ");
		}

		writer.write(String.format("Meal %02d - £%2.02f:", mealNum, meal.totalPrice()));

		// Print right spaces
		for (int i = 0; i < spacesRight; i++) {

			writer.write(" ");
		}

		writer.write(" //\n");

	}

	static void writeMenuLineBlank() throws IOException {

		writeMenuLineCenter("");

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
	 * @throws IOException
	 */
	static void writeMenuLineCenter(String contents) throws IOException {

		writer.write("// ");

		// Work out number of spaces as explained in the above comment
		int spacesLeft = (PrintClass.CONSOLE_WIDTH - 6 - contents.length()) / 2;
		double spacesRight = (PrintClass.CONSOLE_WIDTH - 6 - Math.ceil(contents.length())) / 2;

		// Print left spaces
		for (int i = 0; i < spacesLeft; i++) {

			writer.write(" ");
		}

		writer.write(contents);

		// Print right spaces
		for (int i = 0; i < spacesRight; i++) {

			writer.write(" ");
		}

		writer.write(" //\n");

	}

	/**
	 * takes a main, side, drink and displays them with a border and "."s between
	 * them
	 * 
	 * @param contents
	 *            contents of the message to be displayed
	 * @param price
	 *            price of item on current line
	 * @throws IOException
	 */
	static void writeOrderLine(String foodType, String contents) throws IOException {

		writer.write("// ");

		writer.write(foodType);

		// Minus 6 for "// " and " //", and length of the text being
		// displayed
		for (int i = 0; i < (PrintClass.CONSOLE_WIDTH - 6 - foodType.length() - contents.length()); i++) {

			writer.write(".");
		}

		// Format the price double to have 2 decimal places
		writer.write(contents);

		writer.write(" //\n");

	}

	/**
	 * Get the next order number from a file
	 * 
	 * @return current orderNumber
	 */
	static int getOrderNumber() {

		/*
		 * Declare a FileReader and BufferedReader for use inside the 'try catch' block
		 * as they must be declared outside of the block
		 */
		FileReader in;
		BufferedReader br;

		int orderNumber = 0;

		try {

			/*
			 * Used <a href =
			 * "https://stackoverflow.com/questions/16104616/using-bufferedreader-to-read-
			 * text-file">for help on reading files using BufferedReader.
			 */

			in = new FileReader("src/orderNumber.txt");
			br = new BufferedReader(in);
			
			String fileLine = br.readLine();
			orderNumber = Integer.parseInt(fileLine);

			br.close();

		} catch (IOException e) {
			System.out.println(
					"File \"orderNumber.txt\" cannot be found, please ensure the file is present then try again. Reciept will use orderNumber 0, and will be overwritten if this error occurs again.");
			System.out.println(e);

		}

		return orderNumber;

	}
	
	/**
	 * Writes the next orderNumber to the file src/orderNumber.txt
	 * 
	 * @param orderNumber	the current orderNumber
	 */
	static void incrementOrderNumber(int orderNumber) {
		
		try {
			//Create BufferedWriter
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/orderNumber.txt"), "utf-8"));
			
			//Increment the current orderNumber and write the result.
			writer.write(String.valueOf(++ orderNumber));

			System.out.println("Order number updated");

		} catch (IOException e) {
			System.out.println("Failed to update order number");
			e.printStackTrace();

		} finally {

			try {
				writer.close();
			} catch (IOException | NullPointerException e) {
				/* Ignore */}

		}
		
	}

}
