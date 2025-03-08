package GUI;

import GUI.DashboardPage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import File.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MenuGui extends JFrame implements ActionListener{
	Font font25 = new Font("Times New Roman",Font.BOLD,25);
	Font font20 = new Font("Times New Roman",Font.BOLD,20);
	
	JLabel title;
	JButton buttonOrder, buttonBack;
	JTable table;
	JScrollPane scroll;
	DefaultTableModel model;
	
	public MenuGui(){
		super("Menu");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(950,600);
		super.setLocationRelativeTo(null);
		super.setLayout(null);
		
		
		//Background Image 
		
	    ImageIcon backgroundImage = new ImageIcon("./Image/menu.jpg");
		JLabel background = new JLabel("",backgroundImage,JLabel.CENTER);
		background.setBounds(0,0,950,600);
		
		//title icon
		this.setIconImage(new ImageIcon("./Image/icon.jpg").getImage());
		
		//Title label
		title = new JLabel ("MENU");
		title.setBounds(425,20,100,50);
		title.setForeground(Color.WHITE);
		title.setFont(font25);
		this.add(title);
		
		//Table 
		table = new JTable();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[]{"Item ID","Item Name","Category","Description","Price","Availability","Prepare Time"});
        table.setModel(model);
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
        
		scroll = new JScrollPane(table);
        scroll.setBounds(40, 90, 850,350);
        scroll.setBackground(Color.decode("#CDFFFF")); 
		this.add(scroll);
		
		String menuFile = "File/Data" + File.separator + "menu.txt";
        loadMenuFileInTable(menuFile);
		
		// button - BACK
        buttonBack = new JButton("Back");
        buttonBack.setFont(font20);
        buttonBack.setBounds(300, 480, 150, 30);
        buttonBack.setBackground(Color.decode("#ff553b"));
		buttonBack.addActionListener(this);
        buttonBack.setForeground(Color.WHITE);
        this.add(buttonBack);
		
		// button Order
		
		buttonOrder = new JButton("Order");
        buttonOrder.setFont(font20);
        buttonOrder.setBounds(500, 480, 150, 30);
        buttonOrder.setBackground(Color.decode("#ff553b"));
        buttonOrder.setForeground(Color.WHITE);
		buttonOrder.addActionListener(this);
        this.add(buttonOrder);
		
		this.add(background);
		super.setVisible(true);
		
	}
	private void loadMenuFileInTable(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./File/data/menu.txt"))) {
			
			String line;
            while ((line = reader.readLine()) !=null) {
                String[] rowData = line.split(";");              
                model.addRow(rowData);
            }
        } 
		catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading menu file: " + e.getMessage(),
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	@Override
	
     public void actionPerformed(ActionEvent e) {
       if (e.getSource() == buttonBack) {
        System.out.println("You have clicked Back Button");
        this.dispose(); // Close the current MenuGui window
        SwingUtilities.invokeLater(Login::new); // Navigate back to Dashboard
      } else if (e.getSource() == buttonOrder) {
        System.out.println("You have clicked Order Button");
        this.dispose();
        SwingUtilities.invokeLater(OrderManagement::new); // Open OrderManagement page
       }
    }

		
	
}
		