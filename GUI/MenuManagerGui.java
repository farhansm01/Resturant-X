package GUI;

import GUI.DashboardPage;
import MenuItem.Item;
import ManageMenuItem.MenuItemManager;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import File.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import File.MenuFile;


public class MenuManagerGui extends JFrame implements ActionListener{
	Font titleFont = new Font("italic handwriting ",Font.BOLD,30);
	Font font18 = new Font("Cambrea",Font.BOLD,18);
	Font font15 = new Font("Cambrea",Font.BOLD,15);
	Font font13 = new Font("Cambrea",Font.BOLD,13);
	Font font20 = new Font("Cambrea",Font.PLAIN,20);
	
	private JLabel itemIdLabel,itemNameLabel,itemCategoryLabel,itemDescriptionLabel,itemPriceLabel,itemAvailabilityLabel,itemPrepTimeLabel;
	
	private JTextField itemIdTextField,itemNameTextField,itemCategoryTextField,itemDescriptionTextField,itemPriceTextField,itemAvailabilityTextField,itemPrepTimeTextField;
	
	private JTextField searchTextField,deleteTextField;
	
	private JButton addButton,updateButton,searchButton,deleteButton,clearButton,showAllButton,buttonBack;
	
	private JComboBox catagoryType,availabilityType;
	
	private JRadioButton yes,no;
	
	private JTextArea textArea;
	
	private JTable table;
	
    DefaultTableModel modelMenu;
	
	MenuItemManager menuList = new MenuItemManager(500);
	
	public MenuManagerGui(){
		super("Menu Manage");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(920,785);
		super.setLocationRelativeTo(null);
		super.setLayout(null);
		
		MenuFile.loadMenuFile(menuList);
		
		//Background
		
		ImageIcon backgroundImage = new ImageIcon("./Image/menuManage.jpg");
		JLabel background = new JLabel("",backgroundImage,JLabel.CENTER);
		background.setBounds(0,0,950,750);
		
		//title icon
		this.setIconImage(new ImageIcon("./Image/icon.jpg").getImage());
		
		//Title
		
		JLabel title = new JLabel("Menu Management");
		title.setBounds(330,10,300,50);
		title.setForeground(Color.decode("#fcf8e8"));
		title.setFont(titleFont);
		this.add(title);
		
		//text field and text label
		int top = 100;
		int gap = 40;
		
		//Id
		itemIdLabel = new JLabel ("Item ID:");
		itemIdLabel.setBounds(15,top,200,30);
		itemIdLabel.setFont(font18);
		itemIdLabel.setForeground(Color.decode("#00ffe4"));
		this.add(itemIdLabel);
		
		itemIdTextField= new JTextField();
		itemIdTextField.setBounds(15,top+=gap,150,30);
		this.add(itemIdTextField);
		
		//name
		itemNameLabel = new JLabel ("Item Name:");
		itemNameLabel.setBounds(15,top+=gap,200,30);
		itemNameLabel.setForeground(Color.decode("#00ffe4"));
		itemNameLabel.setFont(font18);
		this.add(itemNameLabel);
		
		itemNameTextField= new JTextField();
		itemNameTextField.setBounds(15,top+=gap,150,30);
		this.add(itemNameTextField);
		
		//category
		
		
		itemCategoryLabel = new JLabel ("Category:");
		itemCategoryLabel.setBounds(15,top+=gap,200,30);
		
		itemCategoryLabel.setForeground(Color.decode("#00ffe4"));
		itemCategoryLabel.setFont(font18);
		this.add(itemCategoryLabel);
		
		String[] item={"Fast Food", "Beverages","Desserts","Snacks"};
	    catagoryType = new JComboBox(item);
		catagoryType.setBounds(15,top+=gap,150,30);
		this.add(catagoryType);
		
		//description
		int top2=100;
		int gap2=40;
		itemDescriptionLabel = new JLabel ("Description:");
		itemDescriptionLabel.setBounds(360,top2,200,30);
		itemDescriptionLabel.setForeground(Color.decode("#00ffe4"));
		itemDescriptionLabel.setFont(font18);
		
		this.add(itemDescriptionLabel);
		
		itemDescriptionTextField= new JTextField();
		itemDescriptionTextField.setBounds(360,top2+=gap2,150,30);
		this.add(itemDescriptionTextField);
		
		//price
		
		itemPriceLabel = new JLabel ("Price:");
		itemPriceLabel.setBounds(360,top2+=gap2,200,30);
		itemPriceLabel.setForeground(Color.decode("#00ffe4"));
		itemPriceLabel.setFont(font18);
		this.add(itemPriceLabel);
		
		itemPriceTextField= new JTextField();
		itemPriceTextField.setBounds(360,top2+=gap2,150,30);
		this.add(itemPriceTextField);
		
		//availability
		
		itemAvailabilityLabel = new JLabel ("Availability:");
		itemAvailabilityLabel.setBounds(360,top2+=gap2,200,30);
		itemAvailabilityLabel.setForeground(Color.decode("#00ffe4"));
		itemAvailabilityLabel.setFont(font18);
		this.add(itemAvailabilityLabel);
		
		
		yes = new JRadioButton("YES");
		yes.setBounds(360,top2+=gap2,50,30);
		this.add(yes);
		
		no = new JRadioButton("NO");
		no.setBounds(460,300,50,30);
		this.add(no);
		
		//prepare time
		
		itemPrepTimeLabel = new JLabel ("Prepare Time:");
		itemPrepTimeLabel.setBounds(15,top+=gap,200,30);
		itemPrepTimeLabel.setForeground(Color.decode("#00ffe4"));
		itemPrepTimeLabel.setFont(font18);
		this.add(itemPrepTimeLabel);
		
		itemPrepTimeTextField= new JTextField();
		itemPrepTimeTextField.setBounds(15,top+=gap,150,30);
		this.add(itemPrepTimeTextField);
		
		//Text area
		
		/*textArea = new JTextArea();
		textArea.setFont(font20);
		textArea.setEditable(false);
		
		JScrollPane jsp = new JScrollPane(textArea);
		jsp.setBounds(220,120,460,530);
		super.add(jsp); */
		
		//table 
		
		table = new JTable();
		modelMenu = new DefaultTableModel();
		modelMenu.setColumnIdentifiers(new String[]{"Item ID","Item Name","Category","Description","Price","Availability","Prepare Time"});
        table.setModel(modelMenu);
		table.setFont(new Font("Segoe UI Black",Font.ITALIC, 15));
        table.setSelectionBackground(Color.YELLOW);
        table.setBackground(Color.YELLOW);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		table.getColumnModel().getColumn(3).setPreferredWidth(400);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.setEnabled(false);
        
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(15,430,870,250);
		super.add(jsp);
		
		
		//Right side
		int tp=100;
		int gp=40;
		
		//search
		
		JLabel searchLabel = new JLabel("Search By Item ID:");
		searchLabel.setBounds(735,tp,200,30);
		searchLabel.setFont(font18);
		searchLabel.setForeground(Color.decode("#00ffe4"));
		this.add(searchLabel);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(735,tp+gp,150,30);
		this.add(searchTextField);
		
		searchButton =new JButton("SEARCH");
		searchButton.setBounds(735,tp+=gp+40,150,30);
		itemIdLabel.setForeground(Color.decode("#00ffe4"));
		searchButton.setFont(font18);
		searchButton.setBackground(Color.YELLOW);
		searchButton.setForeground(Color.BLACK);
		searchButton.addActionListener(this);
		this.add(searchButton);
		
		//delete 
		
		JLabel deleteLabel = new JLabel("Delete By Item ID:");
		deleteLabel.setBounds(735,tp+=gp+20,200,30);
		deleteLabel.setForeground(Color.decode("#00ffe4"));
		deleteLabel.setFont(font18);
		this.add(deleteLabel);
		
		deleteTextField = new JTextField();
		deleteTextField.setBounds(735,tp+=gp,150,30);
		this.add(deleteTextField);
		
		deleteButton = new JButton("DELETE");
	    deleteButton.setBounds(735,tp+=gp,150,30);
	    deleteButton.setFont(font18);
	    deleteButton.setBackground(Color.RED);
	    deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(this);
	    this.add(deleteButton);
		
		//add
		
		addButton =new JButton("ADD");
		addButton.setBounds(310,top2+=gap2+30,150,40);
		addButton.setFont(font18);
		addButton.setBackground(Color.decode("#fc513a"));
		addButton.setForeground(Color.WHITE);
		addButton.addActionListener(this);
		this.add(addButton);
		
		//update
		
		updateButton = new JButton("UPDATE");
		updateButton.setBackground(Color.decode("#fc513a"));
		updateButton.setForeground(Color.WHITE);
		updateButton.setBounds(490,top2+=gap2-40,150,40);
		updateButton.setFont(font18);
		updateButton.addActionListener(this);
		this.add(updateButton);
		
		//show all
		
		showAllButton = new JButton("SHOW ALL");
	    showAllButton.setBounds(200,700,150,30);
	    showAllButton.setFont(font15);
	    showAllButton.setBackground(Color.PINK);
	    showAllButton.setForeground(Color.BLACK);
		showAllButton.addActionListener(this);
	    this.add(showAllButton);
		
		//clear all
		
		clearButton = new JButton("CLEAR SCREEN");
	    clearButton.setBounds(365,700,150,30);
	    clearButton.setFont(font13);
	    clearButton.setBackground(Color.DARK_GRAY);
	    clearButton.setForeground(Color.WHITE);
		clearButton.addActionListener(this);
	    this.add(clearButton);
		
		//back
		buttonBack = new JButton("Back");
        buttonBack.setFont(font20);
        buttonBack.setBounds(530, 700, 150, 30);
        buttonBack.setBackground(Color.decode("#d49c74"));
        buttonBack.setForeground(Color.BLACK);
		buttonBack.addActionListener(this);
        this.add(buttonBack);
		
		this.add(background);
		super.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		//add
		if(addButton == e.getSource()){
			System.out.println("Clicked Add Button");
			String itemId= itemIdTextField.getText();
			String itemName = itemNameTextField.getText();
			String category = catagoryType.getSelectedItem().toString();
			String description = itemDescriptionTextField.getText();
			double price =Double.parseDouble(itemPriceTextField.getText());
			
			boolean availability = false;
			if (yes.isSelected()) {
				availability = true; 
				} 
			else if (no.isSelected()) {
            availability = false; 
			}
			else {
				JOptionPane.showMessageDialog(this,"Please select availability.");
			}
			
			int prepTime = Integer.parseInt(itemPrepTimeTextField.getText());
			
		    Item it = menuList.getById(itemId);
		
			if(it == null){
				if(!itemId.isEmpty()&& !itemName.isEmpty()&& !category.isEmpty() && !description.isEmpty() && price>0 && prepTime>0){
					Item newItem = new Item(itemId,itemName,category,description,price,availability,prepTime);
				
				    MenuFile.saveItem(newItem);
				    menuList.insert(newItem);
				}
				else{
					JOptionPane.showMessageDialog(this,"Please Enter All Data");
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"ID Already Used","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		//update
		else if(updateButton == e.getSource()){
			System.out.println("Update Button Clicked");
			Item it = menuList.getById(itemIdTextField.getText());
			if(it!=null) {
				if(!itemNameTextField.getText().isEmpty()){
				it.setItemName(itemNameTextField.getText());
				}
				if(!itemCategoryTextField.getText().isEmpty()){
					it.setCategory(itemCategoryTextField.getText());
				}
				
				
					it.setPrice(Double.parseDouble(itemPriceTextField.getText()));
				
				
					it.setAvailability(Boolean.parseBoolean(itemAvailabilityTextField.getText()));
				
		
					it.setPrepTime(Integer.parseInt(itemPrepTimeTextField.getText()));
					
					if(!itemDescriptionTextField.getText().isEmpty()){
					it.setDescription(itemDescriptionTextField.getText());
				}
				
				else{
					JOptionPane.showMessageDialog(this,"NO Item Found With this ID","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
					
		}
		//search
		else if(searchButton == e.getSource()){
			System.out.println("Search Button Clicked");
            String itemId = searchTextField.getText(); 
            Item it = menuList.getById(itemId);   
            modelMenu.setRowCount(0);            
		    if(it != null){
				modelMenu.addRow(new Object[]{
                it.getItemId(),         
                it.getItemName(),       
                it.getCategory(),               it.getDescription(),    
                it.getPrice(),          
                it.isAvailable() ? "Yes" : "No",it.getPrepTime()
				});
			} 
			else {
                JOptionPane.showMessageDialog(this, "No Item Found With this ID");
			}
		}
		
		//delete
		else if (deleteButton == e.getSource()){
			System.out.println("Delete Button Clicked");
			Item it = menuList.getById(deleteTextField.getText());
			if(it!=null){
				int option = JOptionPane.showConfirmDialog(this,"Are You Sure to Delete Item");
				
				if(option== JOptionPane.YES_OPTION){
					menuList.deleteById(deleteTextField.getText());
					}
			}
	        else{
				JOptionPane.showMessageDialog(this,"No Book Found With This ID","Warning",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//show all 

        else if(showAllButton == e.getSource()){
			System.out.println("Show All Button Clicked");
			modelMenu.setRowCount(0);
			
			try (BufferedReader reader = new BufferedReader(new FileReader("./File/data/menu.txt"))) {
				String line;
                while ((line = reader.readLine()) !=null) {
					String[] rowData = line.split(";");modelMenu.addRow(rowData);
				}
			} 
			catch (Exception ep) {
            JOptionPane.showMessageDialog(this, 
                "Error loading menu file: " + ep.getMessage(),
                "File Error", JOptionPane.ERROR_MESSAGE);
			}
		}


		
		//clear Button
		else if(clearButton == e.getSource()){
			System.out.println("You have clicked Clear Button");
			modelMenu.setRowCount(0);
		}
		
		//Back
		else if(buttonBack == e.getSource()){
			System.out.println("You have clicked Back Button");
            setVisible(false);
            new DashboardPage(); //!!!! instead of that line enter main page constructor
		}
		
	
    }
				
}
