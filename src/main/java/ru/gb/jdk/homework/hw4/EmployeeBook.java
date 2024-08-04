package ru.gb.jdk.homework.hw4;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBook {
    private List<Employee> employees;

    public EmployeeBook(List<Employee> employeeList) {
        this.employees = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employees;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employees = employeeList;
    }

    public List<String> findEmployeeByExperience (List<Employee> employees, int workExperience) {
        List<String> nameList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getWorkExperience() >= workExperience) {
                nameList.add(employee.getName());
            }
        }
        return nameList;
    }

    public List<String> findPhoneByName (List<Employee> employees, String name) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                phoneNumbers.add(employee.getPhone());
            }
        }
        return phoneNumbers;
    }

    public String findByUin (List<Employee> employees, int uin) {
        for (Employee employee : employees) {
            if (employee.getUin() == uin) {
                return employee.toString();
            }
        }
        return "Такого сотрудника нет";
    }

    public List<Employee> addEmployeeToList (List<Employee> employees, Employee employee) {
        employees.add(employee);
        return employees;
    }
}
