package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StaffService service = new StaffService();
        service.readEmployeesFromFile();

        StaffManager manager = new StaffManager(scanner,service);

        System.out.println("Welcome to Staff Management System");

        Utils.menu(manager);

        service.writeEmployeesToFile();
    }
}