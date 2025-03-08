package File;
import java.io.*;
import Food.Vegetables;
import FoodList.VegetableList;

public class VegetableFile {
    private static final String FILE_PATH = "vegetables.txt";

    public static void saveVegetable(Vegetables veg) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(veg.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void loadVegetableFile(VegetableList vegList) {
        vegList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length < 5) continue;
                String productId = fields[0];
                String productName = fields[1];
                String expiryDate = fields[2];
                String productType = fields[3];
                int stock = Integer.parseInt(fields[4]);
                vegList.insert(new Vegetables(productId, productName, expiryDate, productType, stock));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
