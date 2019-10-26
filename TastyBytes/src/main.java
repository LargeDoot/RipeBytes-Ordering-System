
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class main {

	public static String readFile() {

		File file = new File("src/menuOptions.txt");
		System.out.println(file.exists());

		Scanner fileScanner;
		String menuContents;
		
		try {
			fileScanner = new Scanner(file);
			menuContents = fileScanner.next();
			fileScanner.close();
			
			return (menuContents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return(null);

	}

	public static void main(String[] args) {

		System.out.println(readFile());

		List<List<String>> menuItems = new ArrayList<List<String>>();
		for (int i = 0; i < 3; i++) {
			menuItems.add(new ArrayList<String>());

		}
		menuItems.get(0).add("test1");
		System.out.println(menuItems.get(0));
	}

}
