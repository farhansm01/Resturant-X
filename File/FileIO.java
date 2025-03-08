package File;

import java.io.*;
import java.util.Scanner;

public class FileIO {
    private static final String FILE_PATH = "./File/data/users.txt";

    // Ensure the file and directory exist
    static {
        File file = new File(FILE_PATH);
        if (file.getParentFile() != null) { // Ensure parent directory exists
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    // Check if user exists (for login)
    public static boolean checkUser(String uname, String upass) {
        try (Scanner fileSc = new Scanner(new File(FILE_PATH))) {
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                String[] data = line.split(",");
                if (data.length >= 3 && uname.equals(data[0]) && upass.equals(data[2])) {
                    return true; // Username and password match
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Read File: " + e.getMessage());
        }
        return false; // Invalid credentials
    }

    // Check if the username already exists
    public static boolean userExists(String uname) {
        try (Scanner fileSc = new Scanner(new File(FILE_PATH))) {
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                String[] data = line.split(",");
                if (data.length >= 3 && uname.equals(data[0])) {
                    return true; // Username already exists
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Read File: " + e.getMessage());
        }
        return false; // Username is available
    }

    // Add a new user
    public static void addUser(String uname, String email, String upass) throws IOException {
        if (userExists(uname)) {
            throw new IOException("User already exists.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(uname + "," + email + "," + upass); // Store username, email, and password
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            throw e; // Re-throw for handling in caller
        }
    }
}
