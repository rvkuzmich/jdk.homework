package ru.gb.jdk.homework.hw3.task1;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        Calculator.sum(5, 3.0f);
        System.out.println(calc.getResult());

        Calculator.sum(4.5, 1.3f);
        System.out.println(calc.getResult());

        Calculator.multiply(8.1, 2);
        System.out.println(calc.getResult());

        Calculator.divide(9.9, 3.3);
        System.out.println(calc.getResult());

        Calculator.subtract(10.5, 0.3f);
        System.out.println(calc.getResult());

        Calculator.subtract(10, 0.3);
        System.out.println(calc.getResult());
    }
}
