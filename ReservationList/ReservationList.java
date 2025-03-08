package ReservationList;

import Reservation.Reservation;

public class ReservationList {
    private Reservation[] reservations;
    private int reservationCount;

    public ReservationList() {
        this(100);
    }

    public ReservationList(int size) {
        reservations = new Reservation[size];
        reservationCount = 0;
    }

    public void insert(Reservation r) {
        if (reservationCount < reservations.length) {
            reservations[reservationCount] = r;
            reservationCount++;
            System.out.println("Reservation Added.");
        } else {
            System.out.println("Reservation Failed. List is full.");
        }
    }

    public Reservation getByName(String name) {
        for (int i = 0; i < reservationCount; i++) {
            if (reservations[i] != null && reservations[i].getCustomerName().equals(name)) {
                return reservations[i];
            }
        }
        return null;
    }

    public void removeByName(String name) {
        for (int i = 0; i < reservationCount; i++) {
            if (reservations[i] != null && reservations[i].getCustomerName().equals(name)) {
                reservations[i] = reservations[reservationCount - 1];
                reservations[reservationCount - 1] = null;
                reservationCount--;
                return;
            }
        }
    }

    public void showAll() {
        for (int i = 0; i < reservationCount; i++) {
            if (reservations[i] != null) {
                reservations[i].showReservationInfo();
            }
        }
    }

    public String getAllAsString() {
        StringBuilder allReservations = new StringBuilder();
        for (int i = 0; i < reservationCount; i++) {
            if (reservations[i] != null) {
                allReservations.append(reservations[i].getReservationAsString());
            }
        }
        return allReservations.toString();
    }

    public void updateReservation(String name, String phone, String email, String tableType) {
        for (int i = 0; i < reservationCount; i++) {
            if (reservations[i] != null && reservations[i].getCustomerName().equals(name)) {
                reservations[i].setCustomerName(name);
                reservations[i].setCustomerPhone(phone);
                reservations[i].setCustomerEmail(email);
                reservations[i].setTableType(tableType);
                return;
            }
        }
    }

    public Reservation[] getAllReservations() {
        Reservation[] copy = new Reservation[reservationCount];
        System.arraycopy(reservations, 0, copy, 0, reservationCount);
        return copy;
    }

    public int getReservationCount() {
        return reservationCount;
    }
}