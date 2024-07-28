package ru.gb.jdk.homework.hw2;

import ru.gb.jdk.homework.hw2.client.ClientController;
import ru.gb.jdk.homework.hw2.client.ClientGUI;
import ru.gb.jdk.homework.hw2.server.FileStorage;
import ru.gb.jdk.homework.hw2.server.ServerController;
import ru.gb.jdk.homework.hw2.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        ServerController serverController = new ServerController(new ServerWindow(), new FileStorage());
        new ClientController(new ClientGUI(), serverController);
        new ClientController(new ClientGUI(), serverController);
    }
}