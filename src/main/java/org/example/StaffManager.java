package org.example;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
@AllArgsConstructor
@Slf4j
public class StaffManager implements Manager{
    Scanner scanner;
    StaffService service;


    @Override
    public void addEmployee() {

        log.info("Please enter the information of the Employee you would like to add in the following format: ID, Name, Department, Role, Salary (.00): ");
        String[] input = scanner.nextLine().trim().split(",");

        long idValue = Long.parseLong(input[0]);
        String nameValue = input[1];
        String departmentValue = input[2];
        String roleValue = input[3];
        double salaryValue = Double.parseDouble(input[4]);

        if (service.getListOfEmployees().containsKey(idValue)){  //check if the entered key already exists in the map
            log.error("An employee with current ID already exists. Please try with another ID.");
            addEmployee();
        }
        else{
            service.getListOfEmployees().put(idValue,new Employee(idValue,nameValue,departmentValue,roleValue,salaryValue));
            System.out.println(service.getListOfEmployees().get(idValue).toString());
        }
    }

    @Override
    public void editEmployee() {
        log.info("Please enter the ID of the Employee you would like to edit: ");
        Employee employee = service.getListOfEmployees().get(Long.parseLong(scanner.nextLine()));

        log.info(employee.toString());
        Utils.displayEditOptions();

        int command = Integer.parseInt(scanner.nextLine());
        switch(command){

            case 1:
                log.info("Please enter the new department: ");
                employee.setDepartment(scanner.nextLine());
                System.out.println(employee.toString());
                break;

            case 2:
                StaffService.newRole(employee);
                break;

            case 3:
                StaffService.newSalary(employee);
                break;

            case 4:
                break;
        }
    }

    @Override
    public void fireEmployee() {
        log.info("Please enter the ID of the Employee you would like to fire: ");
        service.getListOfEmployees().remove(Long.parseLong(scanner.nextLine()));
        log.info("Employee has been fired :( ");
    }

    @Override
    public void listAllEmployees() {
        for (var employee :
                service.getListOfEmployees().entrySet()) {
            System.out.println(employee.toString());
        }
    }

    @Override
    public void searchEmployee() {
        log.info("Please enter choose the type of search: ");
       // Employee employee = service.getListOfEmployees().get(Long.parseLong(scanner.nextLine()));

       // log.info(employee.toString());
        Utils.displayEditOptions();

        int command = Integer.parseInt(scanner.nextLine());
        switch(command){

            case 1:
                log.info("Please enter the ID of the Employee you would like to search: ");
                Employee employee = service.getListOfEmployees().remove(Long.parseLong(scanner.nextLine()));
                System.out.println(employee.toString());
                break;

            case 2:
                log.info("Please enter the name of the department you would like to search: ");
               String input = scanner.nextLine().trim();
                for (var employeed :
                        service.getListOfEmployees().entrySet()) {
                    if (input.equals(employeed.getValue().getDepartment())){
                        System.out.println(employeed.toString());
                    }
                    else{
                        System.out.println("No active employees are in department " + input);
                    }
                }
                break;

            case 3:
                // no time :(

        }
    }


}
