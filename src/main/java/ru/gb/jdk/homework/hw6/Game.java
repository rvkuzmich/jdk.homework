package ru.gb.jdk.homework.hw6;

import lombok.Data;

import java.util.HashMap;
import java.util.Random;

@Data
public class Game {
    Random random;
    int doorsNumber;

    public Game(int doorsNumber) {
        this.random = new Random();
        this.doorsNumber = doorsNumber;
    }

    HashMap<Integer, Boolean> start(int iterations, boolean changeDoor) {
        int hostDoor = -10;
        int playerSecondChoice = -10;
        HashMap<Integer, Boolean> result = new HashMap<>();
        for (int i = 0; i < iterations; i++) {
            int win = random.nextInt(this.doorsNumber);
            int playerFirstChoice = random.nextInt(this.doorsNumber);
            if (!changeDoor) {
                result.put(i, playerFirstChoice == win);
            } else {
                for (int j = 0; j < this.doorsNumber; j++) {
                    if (j != win && j != playerFirstChoice) {
                        hostDoor = j;
                    }
                }
                for (int j = 0; j < this.doorsNumber; j++) {
                    if (j != hostDoor && j != playerFirstChoice) {
                        playerSecondChoice = j;
                        result.put(i, playerSecondChoice == win);
                    }
                }
            }
        }
        return result;
    }
}
