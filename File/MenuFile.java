package File;
import java.io.*;
import java.util.Scanner;
import MenuItem.*;
import ManageMenuItem.*;


public class MenuFile{
		public static void saveItem(Item it){
			try{
				File file = new File ("./File/data/menu.txt");
				FileWriter writer = new FileWriter(file,true);
		
		        String line = it.getItemId()+";"+it.getItemName()+";"+it.getCategory()+";"+it.getDescription()+";"+it.getPrice()+";"+it.isAvailable()+";"+it.getPrepTime()+"\n";
		
		        writer.write(line);
		        writer.flush();
		
		        writer.close();
			}
			catch(Exception e){
				 System.out.println("Cannot Read File");
		    }
		}
    

    public static void loadMenuFile(MenuItemManager menuList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./File/data/menu.txt"));
            String line;

            menuList.clear();  
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");  
                String itemId = fields[0];
                String itemName = fields[1];
                String category = fields[2];
                String description = fields[3];
                double price = Double.parseDouble(fields[4]);
                boolean availability = fields[5].equals("true");
                int prepTime = Integer.parseInt(fields[6]);

                // Create a new Item object and add it to menuList
                Item newItem = new Item(itemId, itemName, category, description, price, availability, prepTime);
                menuList.insert(newItem);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Can not Read File");
        }
    }
	
	
}
	
		