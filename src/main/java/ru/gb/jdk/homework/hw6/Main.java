package ru.gb.jdk.homework.hw6;

import java.util.HashMap;

/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Подключить зависимость lombok.
 * Можно подумать о подключении какой-нибудь математической библиотеки для работы со статистикой
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>
 * Вывести на экран статистику по победам и поражениям
 */
public class Main {
    static final int numberOfIterations = 1000;
    static HashMap<Integer, Boolean> resultsWithSameDoor;
    static HashMap<Integer, Boolean> resultsWithOtherDoor;

    public static void main(String[] args) {
        Game game = new Game(3);

        resultsWithSameDoor = game.game(numberOfIterations, false);

        resultsWithOtherDoor = game.game(numberOfIterations, true);
        System.out.println();
        System.out.println("Статистика при отказе от изменения двери");
        System.out.println();
        int winStat = 0;
        for (HashMap.Entry<Integer, Boolean> entry : resultsWithSameDoor.entrySet()) {
            if (entry.getValue()) {
                winStat++;
            }
        }
        System.out.printf("Из %s игр выиграно %s\n", resultsWithSameDoor.size(), winStat);
        System.out.println("------------------------------------------------------------");
        System.out.println();
        System.out.println("Статистика при изменении двери");
        System.out.println();
        winStat = 0;
        for (HashMap.Entry<Integer, Boolean> entry : resultsWithOtherDoor.entrySet()) {
            if (entry.getValue()) {
                winStat++;
            }
        }
        System.out.printf("Из %s игр выиграно %s", resultsWithOtherDoor.size(), winStat);
    }
}
