package MenuItem;
public class Item {
	private String itemId;
    private String itemName;
    private String category;
    private String description;
    private double price;
    private boolean availability;
    private int prepTime;
	
	private static int itemCounter=0;
	
	public Item(){}
	public Item(String itemId, String itemName, String category, String description, double price, boolean availability,int prepTime) {
        setItemId(itemId);
        setItemName(itemName);
        setCategory (category);
        setDescription(description);
        setPrice(price);
        setAvailability(availability);
        setPrepTime(prepTime);
		itemCounter++;
    }
	
	public void setItemId(String itemId) {
        this.itemId = itemId;
    }
	public String getItemId() {
        return itemId;
    }
	public void setItemName(String itemName) {
        this.itemName = itemName;
    }
	public String getItemName() {
        return itemName;
    }
	 public void setCategory(String category) {
        this.category = category;
    }
	public String getCategory() {
        return category;
    }
	 public void setDescription(String description) {
        this.description = description;
    }
	 public String getDescription() {
        return description;
    }
	 public void setPrice(double price) {
        this.price = price;
    }
	public double getPrice() {
        return price;
    }
	public void setAvailability(boolean availability) {
        this.availability = availability;
    }
	 public boolean isAvailable() {
        return availability;
    }
	 public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
	public int getPrepTime() {
        return prepTime;
    }
	
	public void showItemInfo(){
		System.out.println("-------------------------");
		System.out.println("Item ID: "+itemId);
		System.out.println("Item Name: "+itemName);
		System.out.println("Category: "+category);
		System.out.println("Description: "+description);
		System.out.println("Price: "+price +"TK");
		System.out.println("Availability: "+availability);
		System.out.println("Prepare Time Need: "+prepTime + "Minutes");
		System.out.println("-------------------------");
	}
	
	public String getItemAsString(){
		return 
		        "----------"+"\n"+
		        "\t"+"MenuItem:\n" +
                "Item ID:" + itemId +"\n"+
                "Item Name:" + itemName +"\n"+
                "Category:" + category+"\n"+
                "Description:" + description+"\n"+
                "Price:" + price + "TK"+"\n"+
                "Availability" + availability+"\n"+
                "Prepare Time Need:" + prepTime+"Minutes"+"\n"+"----------"+"\n";
    }
	
	public static void totalNumberOfUniqueItem(){
		System.out.println("Total Number Of Unique Items : " + itemCounter);
	}
}