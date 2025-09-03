package onlinereservationsys;

import java.util.Scanner;

public class service {
    public static boolean login(Scanner sc) {
        System.out.print("Login ID: ");
        String id = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        return id.equals("admin") && pass.equals("1234");
    }
}

