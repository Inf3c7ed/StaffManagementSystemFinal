package org.example;

import java.util.Map;

public interface Service  {

    Map<Long, Employee> readEmployeesFromFile();

    void writeEmployeesToFile();


}
