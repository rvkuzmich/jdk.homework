package ru.gb.jdk.homework.hw3.task3;

public class Main {
    public static void main(String[] args) {
        Pair<String, Double> p1 = new Pair<>("test", 3.14);
        Pair<Boolean, Integer> p2 = new Pair<>(true, 5);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.getFirst());
        System.out.println(p2.getSecond());
    }
}
