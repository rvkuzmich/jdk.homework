package ru.gb.jdk.homework.hw3.task1;

public class Calculator {
    private static Number result;


    public Number getResult() {
        return result;
    }

     static <T extends Number> void sum(T num1, T num2) {
        if (num1 != null && num2 != null) {
            result = num1.doubleValue() + num2.doubleValue();
        } else {
            throw new IllegalArgumentException("Тип данных не поддерживается");
        }
    }

    static <T extends Number> void multiply(T num1, T num2) {
        if (num1 != null && num2 != null) {
            result = num1.doubleValue() * num2.doubleValue();
        } else {
            throw new IllegalArgumentException("Тип данных не поддерживается");
        }
    }

    static <T extends Number> void divide(T num1, T num2) {
        if (num2.doubleValue() == 0) {
            throw new ArithmeticException("Деление на 0 запрещено");
        }
        if (num1 != null && num2 != null) {
            result = num1.doubleValue() / num2.doubleValue();
        } else {
            throw new IllegalArgumentException("Тип данных не поддерживается");
        }
    }

    static <T extends Number> void subtract(T num1, T num2) {
        if (num1 != null && num2 != null) {
            result = num1.doubleValue() - num2.doubleValue();
        } else {
            throw new IllegalArgumentException("Тип данных не поддерживается");
        }
    }
}
