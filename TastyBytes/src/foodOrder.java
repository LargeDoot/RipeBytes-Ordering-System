
public class foodOrder {
	
	String main, side, drink;
	
	boolean superSize;
	double price;
	
	public foodOrder(String initialMain, String initialSide, String initialDrink) {
		
		main = initialMain;
		side = initialSide;
		drink = initialDrink;
		
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
