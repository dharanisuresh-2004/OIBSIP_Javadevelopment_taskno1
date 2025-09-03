package onlinereservationsys;

import java.sql.*;
import java.util.Scanner;

public class reservationservice {
    private Scanner sc;
    private Connection conn;

    public reservationservice(Scanner sc, Connection conn) {
        this.sc = sc;
        this.conn = conn;
    }

    public void makeReservation() {
        try {
            System.out.print("Passenger Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt(); sc.nextLine();
            System.out.print("Train Number: ");
            int trainNo = sc.nextInt(); sc.nextLine();
            String trainName = "Express Train";
            System.out.print("Class Type: ");
            String classType = sc.nextLine();
            System.out.print("From: ");
            String from = sc.nextLine();
            System.out.print("To: ");
            String to = sc.nextLine();
            System.out.print("Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO reservation (passenger_name, age, train_no, train_name, class_type, date_of_journey, source, destination, user_id) "
                       + "VALUES ('"+name+"',"+age+","+trainNo+",'"+trainName+"','"+classType+"','"+date+"','"+from+"','"+to+"',1)";
            stmt.executeUpdate(sql);

            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
                int pnr = rs.getInt(1);
                System.out.println("Reservation done! Your PNR: " + pnr);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cancelReservation() {
        try {
            System.out.print("Enter PNR Number: ");
            int pnr = sc.nextInt(); sc.nextLine();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT passenger_name, train_no, train_name FROM reservation WHERE pnr_no=" + pnr);

            if (rs.next()) {
                System.out.println("Passenger: " + rs.getString("passenger_name"));
                System.out.println("Train: " + rs.getInt("train_no") + " - " + rs.getString("train_name"));
                System.out.print("Cancel ticket? (yes/no): ");
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    stmt.executeUpdate("DELETE FROM reservation WHERE pnr_no=" + pnr);
                    System.out.println("Ticket cancelled!");
                } else {
                    System.out.println("Cancellation aborted.");
                }
            } else {
                System.out.println("PNR not found!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewReservations() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservation");
            boolean found = false;
            while(rs.next()) {
                found = true;
                System.out.println("PNR: " + rs.getInt("pnr_no") +
                        " | Name: " + rs.getString("passenger_name") +
                        " | Train: " + rs.getInt("train_no") + " - " + rs.getString("train_name") +
                        " | From: " + rs.getString("source") + " To: " + rs.getString("destination") +
                        " | Date: " + rs.getDate("date_of_journey"));
            }
            if(!found) System.out.println("No reservations found.");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
