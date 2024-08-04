package ru.gb.jdk.homework.hw4;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String phone;
    private final int uin;
    private int workExperience;

    public Employee(String name, String phone, int uin, int workExperience) {
        this.name = name;
        this.phone = phone;
        this.uin = uin;
        this.workExperience = workExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUin() {
        return uin;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", uin=" + uin +
                ", workExperience=" + workExperience +
                '}';
    }
}
