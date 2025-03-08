package File;
import java.io.*;
import java.util.Scanner;
import Employee.Employee;
import EmployeeList.*;


public class EmployeeFile{
	public static void saveEmployee(Employee emp){
		try{
			File file = new File ("./File/Data/employeeInfo.txt");
			FileWriter writer = new FileWriter(file,true);
			
			String line = emp.getId() + ";" + emp.getName() + ";" + emp.getPosition() + ";" +
            emp.getSalary() + ";" + emp.getAttendanceDays() + "\n";
			
			writer.write(line);
			writer.flush();
			
			writer.close();
		}
		catch(Exception e){
				 System.out.println("Cannot Read File");
		}
	}
	
	public static void loadEmployeeFile(EmployeeList employeeList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./File/Data/employeeInfo.txt"));
            String line;
          //  employeeList.clear();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                String id = fields[0];
                String name = fields[1];
                String position = fields[2];
                double salary = Double.parseDouble(fields[3]);
                int attendanceDays = Integer.parseInt(fields[4]);

                Employee newEmployee = new Employee(id, name, position, salary);
                employeeList.addEmployee(newEmployee);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Cannot Read File");
        }
    } 
}
	
		