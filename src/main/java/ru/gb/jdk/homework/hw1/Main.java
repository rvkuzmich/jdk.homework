package ru.gb.jdk.homework.hw1;

import ru.gb.jdk.homework.hw1.client.ClientWindow;
import ru.gb.jdk.homework.hw1.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow);
        new ClientWindow(serverWindow);
    }
}