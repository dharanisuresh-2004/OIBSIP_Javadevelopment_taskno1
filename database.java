package onlinereservationsys;

import java.sql.*;

public class database{
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ORSdb",
                "root",
                "#@123root"
            );
        } catch(Exception e) {
            System.out.println("DB connection error: " + e.getMessage());
            return null;
        }
    }
}
