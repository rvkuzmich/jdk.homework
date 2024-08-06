package ru.gb.jdk.homework.hw5;

import java.util.Random;

public class Philosopher extends Thread {
    private String name;
    private Random random;
    private final Object leftFork;
    private final Object rightFork;
    private int timesFoodTaken;

    public Philosopher(String name, Object leftFork, Object rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        random = new Random();
        timesFoodTaken = 0;

    }

    @Override
    public void run() {
        while (timesFoodTaken < 3) {
            think();
            synchronized (leftFork) {
                takeLeftFork();
                synchronized (rightFork) {
                    takeRightFork();
                    takeFood();
                    releaseRightFork();
                }
                releaseLeftFork();
            }
        }
        System.out.println(name + " is full");
    }

    private void think() {
        System.out.println(name + " is thinking");
        try {
            sleep(random.nextInt(3000, 5000));
            System.out.println(name + " is hungry");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeFood() {
        System.out.println(name + " is taking food");
        try {
            sleep(random.nextInt(3000, 5000));
            System.out.println(name + " stops taking food");
            timesFoodTaken++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeLeftFork() {
        System.out.println(name + " takes left fork");
    }

    private void takeRightFork() {
        System.out.println(name + " takes right fork");
    }

    private void releaseLeftFork() {
        System.out.println(name + " releases left fork");
    }

    private void releaseRightFork() {
        System.out.println(name + " releases right fork");
    }
}
