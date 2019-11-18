import java.util.Map;

public class FoodOrder {
	
	String main, side, drink;
	
	boolean superSize;
	double price;
	
	Menu foodMenu;
	
	public FoodOrder(String initialMain, String initialSide, String initialDrink, 
						Menu menu) {
		
		main = initialMain;
		side = initialSide;
		drink = initialDrink;
		
		foodMenu = menu;
	}
	
	
	public void updateMain(String newMain) {
		
		main = newMain;
	}
	
	public void updateSide(String newSide) {
		
		main = newSide;
	}
	
	public void updateDrink(String newDrink) {
		
		main = newDrink;
	}
	
	public void superSize() {
		
		superSize = true;
	}
	
	public void removeSuperSize() {
		
		superSize = false;
	}
	
	
	public double totalPrice() {
		
		price += foodMenu.getPrice(main);
		price += foodMenu.getPrice(side);
		price += foodMenu.getPrice(drink);
		
		if (superSize == true) {
			
			return price += 2;
		} 
		
		return(price);
	}
	
	public String getMain() {

		return (main);
	}
	
	public String getSide() {

		return (side);
	}
	
	public String getDrink() {

		return (drink);
	}
	
	public boolean isSuperSize() {

		return (superSize);
	}
	
}
