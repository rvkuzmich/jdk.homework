package ru.gb.jdk.homework.hw4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Roman", "23456789", 164002, 3);
        Employee emp2 = new Employee("Alexei", "45678933", 163876, 3);
        Employee emp3 = new Employee("Ekaterina", "34567893", 163429, 4);
        Employee emp4 = new Employee("Alexander", "234567876", 164182, 2);
        Employee emp5 = new Employee("Ekaterina", "456789876", 162345, 6);
        Employee emp6 = new Employee("Sergei", "8765434567", 178273, 1);

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        EmployeeBook eBook = new EmployeeBook(employees);

        System.out.println(eBook.findEmployeeByExperience(eBook.getEmployeeList(), 3));
        System.out.println(eBook.findPhoneByName(eBook.getEmployeeList(),"Ekaterina"));
        System.out.println(eBook.findByUin(eBook.getEmployeeList(), 164002));
        System.out.println(eBook.findByUin(eBook.getEmployeeList(), 32));
        eBook.addEmployeeToList(eBook.getEmployeeList(), emp6);
        System.out.println(eBook.getEmployeeList());
    }
}
