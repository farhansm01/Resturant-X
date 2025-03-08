package GUI;

import GUI.DashboardPage;
import Employee.Employee;
import EmployeeList.*;
import EmployeeList.EmployeeList;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import File.*;



public class EmployeeGui extends JFrame implements ActionListener{
	Font titleFont = new Font("italic handwriting ",Font.BOLD,30);
	Font font18 = new Font("Cambrea",Font.BOLD,18);
	Font font15 = new Font("Cambrea",Font.BOLD,15);
	Font font13 = new Font("Cambrea",Font.BOLD,13);
	Font font20 = new Font("Cambrea",Font.PLAIN,20);
	
	private JLabel idLabel,nameLabel,positionLabel,salaryLabel,attendanceLabel;
	
	private JTextField idTextField,nameTextField,salaryTextField,searchTextField,deleteTextField;
	
	private JComboBox positionType;
	
	private JButton addButton,searchButton,deleteButton,updateButton,clearButton,buttonBack,showAllButton;
	
	private JRadioButton present,absent;
	
	private JTable table;
	
	DefaultTableModel modelEmployee;
	
	EmployeeList employeeList = new EmployeeList(500);
	
	public EmployeeGui(){
		super("Employee Management");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(900,735);
		super.setLocationRelativeTo(null);
		super.setLayout(null);
		
		EmployeeFile.loadEmployeeFile(employeeList);
		
		//Background
		ImageIcon backgroundImage = new ImageIcon("./Image/background.jpg");
		JLabel background = new JLabel("",backgroundImage,JLabel.CENTER);
		background.setBounds(0,0,900,700);
		
		//title icon
		this.setIconImage(new ImageIcon("./Image/titleIcon.png").getImage());
		
		//Title
		JLabel title = new JLabel("Employee Management");
		title.setBounds(280,10,400,50);
		title.setForeground(Color.decode("#D67A29"));
		title.setFont(titleFont);
		this.add(title);
		
		//text field and text label
		int top = 100;
		int gap = 40;
		
		//Id
		idLabel = new JLabel ("Employee ID:");
		idLabel.setBounds(15,top,200,30);
		idLabel.setFont(font18);
		idLabel.setForeground(Color.decode("#00ffe4"));
		this.add(idLabel);
		
		idTextField= new JTextField();
		idTextField.setBounds(15,top+=gap,150,30);
		this.add(idTextField);
		
		//name
		nameLabel = new JLabel ("Employee Name:");
		nameLabel.setBounds(15,top+=gap,200,30);
		nameLabel.setForeground(Color.decode("#00ffe4"));
		nameLabel.setFont(font18);
		this.add(nameLabel);
		
		nameTextField= new JTextField();
		nameTextField.setBounds(15,top+=gap,150,30);
		this.add(nameTextField);
		
		//attedance
		attendanceLabel = new JLabel ("Mark Attendance:");
		attendanceLabel.setBounds(15,top+=gap,200,30);
		attendanceLabel.setForeground(Color.decode("#00ffe4"));
		attendanceLabel.setFont(font18);
		this.add(attendanceLabel);
		
		present = new JRadioButton("Present");
		present.setBounds(15,top+=gap,80,30);
		present.addActionListener(this);
		this.add(present);
		
		absent = new JRadioButton("Absent");
		absent.setBounds(15,top+=gap,80,30);
		absent.addActionListener(this);
		this.add(absent);
		
		// Grouping the radio buttons
        ButtonGroup attendanceGroup = new ButtonGroup();
        attendanceGroup.add(present);
        attendanceGroup.add(absent);
		
		int top1 =100;
		int gap1 =40;
		
		//salary
		salaryLabel= new JLabel ("Employee Salary:");
		salaryLabel.setBounds(350,top1,200,30);
		salaryLabel.setFont(font18);
		salaryLabel.setForeground(Color.decode("#00ffe4"));
		this.add(salaryLabel);
		
		salaryTextField= new JTextField();
		salaryTextField.setBounds(350,top1+=gap1,150,30);
		this.add(salaryTextField);
		
		//position
		
		positionLabel = new JLabel ("Position:");
		positionLabel.setBounds(350,top1+=gap1,200,30);
		positionLabel.setForeground(Color.decode("#00ffe4"));
		positionLabel.setFont(font18);
		this.add(positionLabel);
		
		String[] item={"Manager", "Assistant Manager","Chef","Waiter","Dishwasher"};
	    positionType = new JComboBox(item);
		positionType.setBounds(350,top1+=gap1,150,30);
		this.add(positionType);
		
		int top2 = 100;
		int gap2 = 30;
		
		//search
		
		JLabel searchLabel = new JLabel("Search By Employee ID:");
		searchLabel.setBounds(655,top2,250,30);
		searchLabel.setFont(font18);
		searchLabel.setForeground(Color.decode("#00ffe4"));
		this.add(searchLabel);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(655,top2+gap2+10,150,30);
		this.add(searchTextField);
		
		searchButton =new JButton("SEARCH");
		searchButton.setBounds(655,top2+=gap2+60,150,30);
		searchButton.setForeground(Color.decode("#00ffe4"));
		searchButton.setFont(font18);
		searchButton.setBackground(Color.decode("#F2D840"));
		searchButton.setForeground(Color.BLACK);
		searchButton.addActionListener(this);
		this.add(searchButton);
		
		//delete
		
		JLabel deleteLabel = new JLabel("Delete By Employee ID:");
		deleteLabel.setBounds(655,top2+=gap2+30,250,30);
		deleteLabel.setForeground(Color.decode("#00ffe4"));
		deleteLabel.setFont(font18);
		this.add(deleteLabel);
		
		deleteTextField = new JTextField();
		deleteTextField.setBounds(655,top2+=gap2+10,150,30);
		this.add(deleteTextField);
		
		deleteButton = new JButton("DELETE");
	    deleteButton.setBounds(655,top2+=gap2+20,150,30);
	    deleteButton.setFont(font18);
	    deleteButton.setBackground(Color.RED);
	    deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(this);
	    this.add(deleteButton);
		
		//add
		
		addButton =new JButton("ADD");
		addButton.setBounds(280,top1+=gap1+80,120,30);
		addButton.setFont(font18);
		addButton.setBackground(Color.decode("#fc513a"));
		addButton.setForeground(Color.WHITE);
		addButton.addActionListener(this);
		this.add(addButton);
		
		//update
		
	    updateButton = new JButton("UPDATE");
		updateButton.setBackground(Color.decode("#fc513a"));
		updateButton.setForeground(Color.WHITE);
		updateButton.setBounds(420,top1+=gap1-40,120,30);
		updateButton.setFont(font18);
		updateButton.addActionListener(this);
		this.add(updateButton);
		
		
		//table 
		
		table = new JTable();
		modelEmployee = new DefaultTableModel();
		modelEmployee.setColumnIdentifiers(new String[]{"Employee ID","Employee Name","Position","Salary(TK)","Total Attendance(Days)"});
        table.setModel(modelEmployee);
		table.setFont(new Font("Segoe UI Black",Font.ITALIC, 15));
        table.setSelectionBackground(Color.YELLOW);
        table.setBackground(Color.decode("#60EB98"));
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);

		table.setEnabled(false);
        
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(15,top1+=gap1+15,855,250);
		super.add(jsp);
		
		
		//show all
		
		showAllButton = new JButton("SHOW ALL");
	    showAllButton.setBounds(200,660,150,30);
	    showAllButton.setFont(font15);
	    showAllButton.setBackground(Color.decode("#E7894B"));
	    showAllButton.setForeground(Color.BLACK);
		showAllButton.addActionListener(this);
	    this.add(showAllButton);
		
		//clear all
		
		clearButton = new JButton("CLEAR SCREEN");
	    clearButton.setBounds(365,660,150,30);
	    clearButton.setFont(font13);
	    clearButton.setBackground(Color.decode("#0C273E"));
	    clearButton.setForeground(Color.WHITE);
		clearButton.addActionListener(this);
	    this.add(clearButton);
		
		//back
		buttonBack = new JButton("Back");
        buttonBack.setFont(font20);
        buttonBack.setBounds(530, 660, 150, 30);
        buttonBack.setBackground(Color.decode("#d49c74"));
        buttonBack.setForeground(Color.BLACK);
		buttonBack.addActionListener(this);
        this.add(buttonBack);
		
		
		this.add(background);
		super.setVisible(true);
	}
	private void clearInputFields() {
        idTextField.setText("");
        nameTextField.setText("");
        salaryTextField.setText("");
        searchTextField.setText("");
        deleteTextField.setText("");
        positionType.setSelectedIndex(0); 
        present.setSelected(false); 
        absent.setSelected(false);  
    }

	
	public void actionPerformed(ActionEvent e){
		
		//add 
		if(addButton == e.getSource()){
			System.out.println("Clicked Add Button");
			String id= idTextField.getText();
			String name = nameTextField.getText();
			String position = positionType.getSelectedItem().toString();
			
			double salary = Double.parseDouble(salaryTextField.getText());
			
		   
		   Employee employee = employeeList.searchEmployeeById(id);
		   
		   if(employee == null){
			   if(!id.isEmpty() && !name.isEmpty() && !position.isEmpty() && salary>0){
				   Employee newEmployee = new Employee(id,name,position,salary);
				   
				  
				   
				   if (present.isSelected()) {
					   newEmployee.markAttendance(); 
                       JOptionPane.showMessageDialog(this, "Attendance marked as Present for " + newEmployee.getName());
				   } 
			       else if (absent.isSelected()) {
                       JOptionPane.showMessageDialog(this, "Attendance marked as Absent for " + newEmployee.getName());
			       } 
			       else {
                       JOptionPane.showMessageDialog(this, "Please select Present or Absent.");
                   }
				   
				   employeeList.addEmployee(newEmployee);
				   EmployeeFile.saveEmployee(newEmployee);
                   clearInputFields();				   
			   }
				else{
				  JOptionPane.showMessageDialog(this,"ID Already Used","Warning",JOptionPane.WARNING_MESSAGE);
			    }	   
		   }
		   		   
		}
		
		//update
		
		else if (updateButton == e.getSource()) {
			System.out.println("Update Button Clicked");
            String id = idTextField.getText();
            Employee employee = employeeList.searchEmployeeById(id);
            if (employee != null) {
				if (!nameTextField.getText().isEmpty())
				{
					employee.setName(nameTextField.getText());  
			    }
				if (!positionType.getSelectedItem().toString().isEmpty()) {
					employee.setPosition(positionType.getSelectedItem().toString()); 
                }
				if (!salaryTextField.getText().isEmpty()) {
                  try {
					  double salary = Double.parseDouble(salaryTextField.getText());
                      employee.setSalary(salary);  
                  } 
				  catch (NumberFormatException ex) {
                      JOptionPane.showMessageDialog(this, "Invalid salary entered.");
                      return;
                  }
				}
				if(present.isSelected()){
					employee.markAttendance();
				}
				else if (absent.isSelected()) {
                       JOptionPane.showMessageDialog(this, "Attendance marked as Absent for " + employee.getName());
			    }
		       else {
                    JOptionPane.showMessageDialog(this, "Please select Present or Absent.");
                }
				EmployeeFile.saveEmployee(employee); 

                JOptionPane.showMessageDialog(this, "Employee Info updated successfully.");
			    
                modelEmployee.setRowCount(0); 
                String allEmployeeString = employeeList.getAllAsString();  
                String[] employees  = allEmployeeString.split("\n");
                for (String employeeString : employees) {
				String[] employeeData = employeeString.split(";");
                modelEmployee.addRow(new Object[]{
					employeeData[0],  
                    employeeData[1],  
                    employeeData[2],  
                    employeeData[3],  
                    employeeData[4]   
                });
				}
			}				
	        else {
		     JOptionPane.showMessageDialog(this, "No Employee found with this ID.", "Warning", JOptionPane.WARNING_MESSAGE);
		    }
		}
		
		
		//search
		else if(searchButton == e.getSource()){
			System.out.println("Search Button Clicked");
            String id = searchTextField.getText(); 
            Employee employee = employeeList.searchEmployeeById(id);   
            modelEmployee.setRowCount(0);            
		    if(employee !=null){
				modelEmployee.addRow(new Object[]{
                employee.getId(),         
                employee.getName(),       
                employee.getPosition(),                
                employee.getSalary(),          
                employee.getAttendanceDays()});
			} 
			else {
                JOptionPane.showMessageDialog(this, "No Item Found With this ID");
			}
			clearInputFields();
		}
		
		//delete
		else if (deleteButton == e.getSource()){
			System.out.println("Delete Button Clicked");
			Employee employee = employeeList.searchEmployeeById(deleteTextField.getText());
			if(employee!=null){
				int option = JOptionPane.showConfirmDialog(this,"Are You Sure to Delete Employee");
				
				if(option== JOptionPane.YES_OPTION){
					employeeList.removeEmployee(deleteTextField.getText());
					}
			}
	        else{
				JOptionPane.showMessageDialog(this,"No Employee Found With This ID","Warning",JOptionPane.ERROR_MESSAGE);
			}
			clearInputFields();
		}
		
		//show all 

        else if(showAllButton == e.getSource()){
			System.out.println("Show All Button Clicked");
			modelEmployee.setRowCount(0);
			
			try (BufferedReader reader = new BufferedReader(new FileReader("./File/Data/employeeInfo.txt"))) {
				String line;
                while ((line = reader.readLine()) !=null) {
					String[] rowData = line.split(";");modelEmployee.addRow(rowData);
				}
			} 
			catch (Exception ep) {
            JOptionPane.showMessageDialog(this, 
                "Error loading Employee file: " + ep.getMessage(),
                "File Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//clear Button
		else if(clearButton == e.getSource()){
			System.out.println("You have clicked Clear Button");
			modelEmployee.setRowCount(0);
			clearInputFields();
		}
		
		//Back
		else if(buttonBack == e.getSource()){
			System.out.println("You have clicked Back Button");
            setVisible(false);
            new DashboardPage (); // !!!! instead of that line enter main page constructor
		}
		
		
	}
	
	
	
	
}