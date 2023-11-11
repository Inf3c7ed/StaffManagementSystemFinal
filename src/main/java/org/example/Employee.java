package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    private long id;
    private String name;
    private String department;
    private String role;
    private double salary;

    @Override
    public String toString() {
        return "The new information for the employee is: " +
                "\n ID: " + id +
                "\n Name: " + name +
                "\n Department: " + department +
                "\n Role: " + role +
                "\n Salary: " + salary + "\n"; // print the edited info of the employee\
    }
}
