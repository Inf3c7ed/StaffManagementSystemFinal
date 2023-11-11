package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor
@Getter
@Slf4j
public class StaffService implements Service{

    Map<Long, Employee> listOfEmployees = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    private final String filePath = "src/main/resources/EmployeeData.csv";

    @Override
    public Map<Long, Employee> readEmployeesFromFile() { // We get the input from the CSV file and fill a Map with all created objects
        String line;
        String splitBy = ",";

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();  // skip the header of CSV
            while ((line = reader.readLine()) != null)
            {

                String[] employeeData = line.trim().split(splitBy);

                long idValue = Long.parseLong(employeeData[0]);
                String nameValue = employeeData[1];
                String departmentValue = employeeData[2];
                String roleValue = employeeData[3];
                double salaryValue = Double.parseDouble(employeeData[4]);

                listOfEmployees.put(idValue,
                        new Employee(idValue, nameValue, departmentValue, roleValue, salaryValue));
            }
        }
        catch (IOException e)
        {
            log.error("Error while trying to read employee list: {}", e.getMessage());
        }

        return listOfEmployees;
    }

    @Override
    public void writeEmployeesToFile() {
        clearCSV();
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("ID, Name, Department, Role, Salary\n"); //write the header and continue to write the CSV file from next line
            for (var employee :
                    listOfEmployees.entrySet()) {
                writer.write(employee.getValue().getId() + "," +
                                employee.getValue().getName() + "," +
                                employee.getValue().getDepartment() + "," +
                                employee.getValue().getRole() + "," +
                                employee.getValue().getSalary() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            log.error("Error while trying to write all employees data to CSV: {}", e.getMessage());
        }
    }

    private void clearCSV() {

        try {
            FileWriter writer = new FileWriter(filePath);

            writer.write("");

            writer.close();

        } catch (IOException e) {
            log.error("Error while trying to clear data of CSV file: {}", e.getMessage());;
        }
    }

    static void newRole(Employee employee){
        log.info("Please enter the new role: ");
        String input = scanner.nextLine();
        if (Utils.containsOnlyLetters(input)) {
            employee.setRole(input);
            System.out.println(employee.toString());
        } else {
            log.error("Please enter valid role!"); // check if there are any numbers in the role.
            newRole(employee);
        }
    }

    static void newSalary(Employee employee){
        log.info("Please enter the new salary: ");
        String input2 = scanner.nextLine();
        if (Utils.containsOnlyNumbers(input2)) {
            employee.setSalary(Double.parseDouble(input2));
            System.out.println(employee.toString());
        } else {
            log.error("Please enter valid salary!"); // check if there are any letters in the salary input.
            newSalary(employee);
        }

    }
}
