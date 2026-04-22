import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuLoader {
    public static ArrayList<Item> loadMenu(String fileName) {
        ArrayList<Item> menu = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");

                String name = parts[0];
                double price = Double.parseDouble(parts[1]);

                Item item = new Item(name, price);
                menu.add(item);
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

        return menu;
    }
}