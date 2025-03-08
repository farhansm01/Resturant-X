package Employee;
public class Employee{
	 private String id;
     private String name;
     private String position;
     private double salary;
     private int attendanceDays;
	 
	 public Employee(String id, String name, String position, double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        setSalary(salary);
        this.attendanceDays = 0; 
	 }
		
	    //setter method
		
		public void setId(String id){
			this.id=id;
		}
        public void setName(String name) {
        this.name = name;
		}
		public void setPosition(String position) {
        this.position = position;
		}
        public void setSalary(double salary) {
			if(salary>=0){
				 this.salary = salary;
			}
       }
	   public void markAttendance() {
             attendanceDays++;
             System.out.println(name + "'s attendance has been marked. Total attendance: " + attendanceDays);
	   }
	   
		//get method
		
	   public String getId() {
           return id;
       }
	   
       public String getName() {
           return name;
       }
	   
       public String getPosition() {
           return position;
       }
	   
       public double getSalary() {
           return salary;
       }
	   
       public int getAttendanceDays() {
           return attendanceDays;
       }
	   
	   public void displayEmployee(){
	       System.out.println("-------------------------");
	       System.out.println("Employee ID: "+id);
	       System.out.println("Employee Name: "+name);
	       System.out.println("Position: " + position);
	       System.out.println("Salary: " + salary +"TK");
	       System.out.println("Attendance Days: " + attendanceDays);
	       System.out.println("-------------------------");
	  }
	  
	  
	  public String getEmployeeAsString(){
		return 
		        "----------"+"\n"+
		        "\t"+"Employee Details:\n" +
                "Employee ID:" + id +"\n"+
                "Employee Name:" + name +"\n"+
                "Position:" + position+"\n"+
                "Salary:" + salary + "TK"+"\n"+
                "Attendance Days:" + attendanceDays+"\n"+
                "Minutes"+"\n"+"----------"+"\n";
	}
	
	
   
}