package ru.gb.jdk.homework.hw2.server;

import ru.gb.jdk.homework.hw2.client.ClientController;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    boolean work;
    private List<ClientController> clientsList;
    private final ServerView serverView;
    private final FileStorage fileStorage;

    /**@apiNote
     * Конструктор контроллера сервера
     * @param serverView объект графического интерфейса сервера
     * @param fileStorage объект файлового хранилища
     */
    public ServerController(ServerView serverView, FileStorage fileStorage) {
        this.serverView = serverView;
        this.fileStorage = fileStorage;
        clientsList = new ArrayList<>();
        serverView.setServerController(this);
    }

    /**@apiNote
     * Метод запуска сервера, вызывается из графического интерфейса при нажатии кнопки Start
     */
    void start() {
        if (work) {
            showOnWindow("Server has been started already");
        } else {
            work = true;
            showOnWindow("Server started");
        }
    }

    /**@apiNote
     * Метод остановки сервера, вызывается из графического интерфейса при нажатии кнопки Stop
     */
    public void stop() {
        if (!work) {
            showOnWindow("Server has been stopped already");
        } else {
            work = false;
            while (!clientsList.isEmpty()) {
                disconnectUser(clientsList.get(clientsList.size()-1));
            }
        }
        showOnWindow("Server has been stopped");
    }

    /**@apiNote
     * Метод подключения клиента к серверу, вызывается из контроллера клиента
     * @param clientController Объект подключаемого контроллера клиента
     * @return Возвращает статус подключения (true - подключение успешно, false - подключение неуспешно)
     */
    public boolean connectUser(ClientController clientController) {
        if (!work) {
            return false;
        }
        clientsList.add(clientController);
        return true;
    }

    /**@apiNote
     * Метод отключения клиента от сервера
     * @param clientController Объект отключаемого контроллера клиента
     */
    public void disconnectUser(ClientController clientController) {
        clientsList.remove(clientController);
        if (clientController != null) {
            clientController.disconnectedFromServer();
            showOnWindow(clientController.getName() + " has been disconnected");
        }
    }

    /**@apiNote
     * Метод обработки сообщениий, поступающих на сервер
     * @param text тест обрабатываемых сообщений
     */
    public void message(String text) {
        if (!work) {
            return;
        }
        showOnWindow(text);
        answerAll(text);
        saveLogToFile(text);
    }

    /**@apiNote
     * Метод отправки сообщений клиентам
     * @param text Тект отправляемых сообщений
     */
    private void answerAll(String text) {
        for (ClientController
                clientController : clientsList) {
            clientController.answerFromServer(text);
        }
    }

    /**@apiNote
     * Метод получения истории сообщений из хранилища
     * @return Возвращает лог сообщений
     */
    public String getHistory() {
        return fileStorage.readLog();
    }

    /**@apiNote
     * Метод для сохранения лога сообщений в хранилище
     * @param text текст сохраняемого сообщения
     */
    private void saveLogToFile(String text) {
        fileStorage.saveInLog(text);
    }

    /**@apiNote
     * Метод для отображения сообщения в окне лога сервера
     * @param text текст отображаемого сообщения
     */
    private void showOnWindow(String text) {
        serverView.showMessage(text + "\n");
    }
}
