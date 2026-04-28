import java.io.FileWriter;
import java.io.IOException;

//Saves the final order receipt to a text file.

public class ReceiptWriter {
    public static void saveReceipt(String fileName, String receiptText) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(receiptText);
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save receipt file.");
        }
    }
}