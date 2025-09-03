package onlinereservationsys;

import java.sql.Connection;
import java.util.Scanner;

public class main{
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        Connection conn = database.getConnection();
        if (conn == null) {
            System.out.println("Cannot connect to database. Exiting...");
            return;
        }

        if (!service.login(sc)) {
            System.out.println("Login failed! Exiting...");
            return;
        }

        reservationservice rs = new reservationservice(sc, conn);

        while (true) {
            System.out.println("\n--- Online Reservation System ---");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View All Reservations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> rs.makeReservation();
                case 2 -> rs.cancelReservation();
                case 3 -> rs.viewReservations();
                case 4 -> {
                    System.out.println("Goodbye!");
                    try { conn.close(); } catch(Exception e) {}
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
