package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
@Slf4j
public class Utils {

   private static final Scanner scanner = new Scanner(System.in);
    static void displayMainMenu(){
        System.out.println("1.Add Employee \n" +
                           "2.Edit Employee \n" +
                           "3.List Employees \n" +
                           "4.Fire Employee \n" +
                           "5.Search Employee \n" +
                           "6.Save and Exit");
    }

    static void displaySearchMenu(){
        System.out.println("1.Search by ID \n" +
                "2.Search by department \n" +
                "3.Search by name \n " +
                "4.Back to Main Menu \n");
    }

    static void menu(StaffManager command){
        boolean isRunning = true;
        while(isRunning){
            Utils.displayMainMenu();
            switch(Integer.parseInt(scanner.nextLine())) {
                case 1:
                    command.addEmployee();
                    break;

                case 2:
                    command.editEmployee();
                    break;

                case 3:
                    command.listAllEmployees();
                    break;

                case 4:
                    command.fireEmployee();
                    break;

                case 5:


                case 6:
                    isRunning = false;
                    break;
            }
        }

    }

    static void displayEditOptions(){
        System.out.println("1.Change department \n" +
                           "2.Change role \n" +
                           "3.Change salary \n" +
                           "4.Back to Main Menu \n");
    }

     static boolean containsOnlyLetters(String string){
        return string.matches("[a-zA-Z]+");
    }

    static boolean containsOnlyNumbers(String string){
        return string.matches("\\d+(\\.\\d+)?");

    }

}
