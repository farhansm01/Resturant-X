package EmployeeList;
import Employee.Employee;

public class EmployeeList{
	private Employee employees[];
	public EmployeeList(){
		employees = new Employee[100];
	}
    	
	private int employeeCount = 0;
	
	public EmployeeList(int size){
		 employees = new Employee[size];
		 employeeCount = 0;
	}
	public Employee searchEmployeeById(String id){
		boolean flag = false;
		Employee emp = null;
		for(int i=0;i<employees.length;i++){
			if(employees[i] !=null){
				if(employees[i].getId().equals(id)){
					emp=employees[i];
					flag = true;
					break;
				}
			}
		}
		if(flag){
			System.out.println("Employee Found");
		}
		else{
			System.out.println("No Employee Found With This ID.");
		}
		return emp;
	}
	
	public void addEmployee(Employee emp){
		boolean flag = false;	
		for(int i=0;i<employees.length;i++){
			if(employees[i] == null){
				employees[i] = emp;
				employeeCount++;
				flag = true;
				break;
			}
		}
		if(flag){
			System.out.println("Employee Successfully Inserted.");
		}
		else{
			System.out.println("Employee Insertion Failed.");
		}
	}

	public void removeEmployee(String id){
		boolean flag = false;
		
		for(int i=0;i<employees.length;i++){
			if(employees[i] != null){
				if(employees[i].getId().equals(id)){
					employees[i] = null;
					employeeCount--;
					flag = true;
					break;
				}
			}
		}
		if(flag){
			System.out.println("Employee Deleted.");
		}
		else{
			System.out.println("No Employee Found with this ID.");
		}
	}
	
	public void showAll(){
		for(int i=0;i<employees.length;i++){
			if(employees[i]!=null){
				employees[i].displayEmployee();
			}
		}
	}
	
	public String getAllAsString(){
		String allEmployees = "";
		for(int i=0;i<employees.length;i++){
			if(employees[i]!=null){
				allEmployees += employees[i].getEmployeeAsString() + "\n";
			}
		}
		return allEmployees;
	}
	
	public void updateEmployee(String id, String newName, String newPosition, double newSalary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId().equals(id)) {
                employees[i].setName(newName);
                employees[i].setPosition(newPosition);
                employees[i].setSalary(newSalary);
                System.out.println("Employee updated successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }
	
	public void markEmployeeAttendance(String id) {
        boolean employeeFound = false; 

    for (int i = 0; i < employees.length; i++) {
        if (employees[i] != null) {
            if (employees[i].getId().equals(id)) { 
                employees[i].markAttendance();
                employeeFound = true;
                break;
            }
        }
    }

    if (!employeeFound) { 
        System.out.println("Employee not found.");
    }
}
	
	 public void viewEmployeeAttendance(String id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId().equals(id)) {
                System.out.println("Attendance days for " + employees[i].getName() + ": " + employees[i].getAttendanceDays());
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}
	
	 