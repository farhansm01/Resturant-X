package File;

import Reservation.Reservation;
import ReservationList.ReservationList;
import java.io.*;

public class ReservationFile {

    private static final String FILE_NAME = "./File/data/reservation.txt";

    public static void saveReservations(ReservationList reservationList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            Reservation[] reservations = reservationList.getAllReservations();
            for (int i = 0; i < reservationList.getReservationCount(); i++) {
                Reservation reservation = reservations[i];
                String line = reservation.getCustomerName() + ";" + reservation.getCustomerPhone() + ";" +
                        reservation.getCustomerEmail() + ";" + reservation.getTableType() + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error while saving reservations: " + e.getMessage());
        }
    }

    public static void loadReservations(ReservationList reservationList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) {
                    Reservation reservation = new Reservation(data[0], data[1], data[2], data[3]);
                    reservationList.insert(reservation);
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, but since you've created the directories manually,
            // we can assume it's okay and just start with an empty list.
            System.out.println("Reservation file not found. Starting with an empty list.");
            // No need to create the file here.
        } catch (IOException e) {
            System.err.println("Error while loading reservations: " + e.getMessage());
        }
    }

    // Removed createFile() method as you've created the directories manually.
}