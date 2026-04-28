import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Loads menu items from a CSV file.
Each line has this format:
Item Name,Price
*/

public class MenuLoader {
    public static ArrayList<Item> loadMenu(String fileName) {
        ArrayList<Item> menu = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());

                    Item item = new Item(name, price);
                    menu.add(item);
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("There was a problem reading a price in the menu file.");
        }

        return menu;
    }
}