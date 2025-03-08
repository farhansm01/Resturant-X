package Reservation;

public class Reservation {
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String tableType;

    public Reservation(String customerName, String customerPhone, String customerEmail, String tableType) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.tableType = tableType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void showReservationInfo() {
        System.out.println("Name: " + customerName);
        System.out.println("Phone: " + customerPhone);
        System.out.println("Email: " + customerEmail);
        System.out.println("Table Type: " + tableType);
    }

    public String getReservationAsString() {
        return "Name: " + customerName + ", Phone: " + customerPhone + ", Email: " + customerEmail + ", Table Type: " + tableType + "\n";
    }
}