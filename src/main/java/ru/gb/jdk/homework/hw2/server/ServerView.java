package ru.gb.jdk.homework.hw2.server;

public interface ServerView {
    void showMessage(String text);
    void setServerController(ServerController serverController);
}
