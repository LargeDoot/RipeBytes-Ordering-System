/**
 * Used to create FoodOrder objects that represent an individual meal (main,
 * side and drink). Contains name of main, side and drink, whether the meal is
 * SuperSized or not, and the total price.
 * 
 * The class can return names or items in the meal, price of the meal and can
 * also edit items.
 * 
 * @author Ethan Wilson
 *
 */
public class FoodOrder {

	String main, side, drink;

	boolean superSize;
	double price;

	Menu foodMenu;

	/**
	 * constructor method to initialise the main, side, drink and foodMenu variables
	 * 
	 * @param initialMain	the initial main food item of the meal
	 * @param initialSide	the initial side food item of the meal
	 * @param initialDrink	the initial drink item of the meal
	 * @param menu			the menu that the class will pull prices from
	 */
	public FoodOrder(String initialMain, String initialSide, String initialDrink, Menu menu) {

		main = initialMain;
		side = initialSide;
		drink = initialDrink;

		foodMenu = menu;
	}
	
	/**
	 * sets the main item in the order to a new value
	 * @param newMain	new main item to be set
	 */
	void setMain(String newMain) {

		main = newMain;
	}

	/**
	 * sets the side item in the order to a new value
	 * @param newSide	new side item to be set 
	 */
	void setSide(String newSide) {

		main = newSide;
	}

	/**
	 * sets the drink item in the order to a new value
	 * @param newDrink	new drink item to be set 
	 */
	void setDrink(String newDrink) {

		main = newDrink;
	}

	/**
	 * sets the superSize variable to true
	 */
	void superSize() {

		superSize = true;
	}

	/**
	 * sets the superSize variable to false
	 */
	void removeSuperSize() {

		superSize = false;
	}

	/**
	 * determines the total price of the meal in its current state 
	 * @return	price of the meal
	 */
	double totalPrice() {

		price += foodMenu.getPrice(main);
		price += foodMenu.getPrice(side);
		price += foodMenu.getPrice(drink);

		if (superSize == true) {

			return price += 1;
		}

		return (price);
	}

	/**
	 * returns the main item in the meal
	 * @return	the main item in the meal
	 */
	String getMain() {

		return (main);
	}

	/**
	 * returns the side item in the meal
	 * @return	the side item in the meal
	 */
	String getSide() {

		return (side);
	}

	/**
	 * returns the drink item in the meal
	 * @return	the drink item in the meal
	 */
	String getDrink() {

		return (drink);
	}

	/**
	 * returns true if the meal is superSized and false if it is not
	 * @return	superSize status (true/false)
	 */
	boolean isSuperSize() {

		return (superSize);
	}

}
