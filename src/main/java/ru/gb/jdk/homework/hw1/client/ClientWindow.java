package ru.gb.jdk.homework.hw1.client;

import ru.gb.jdk.homework.hw1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final String TITLE = "Chat client";
    private static final String MESSAGE_FLAG = ">";
    private JTextArea log;
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField ipInput = new JTextField("127.0.0.1");
    private final JTextField portInput = new JTextField("8189");
    private final JTextField loginInput = new JTextField("login");
    private final JPasswordField passwordInput = new JPasswordField("password");
    private final JButton btnLogin = new JButton("Login");
    ServerWindow serverWindow;
    private static boolean isClientConnected = false;

    public ClientWindow(ServerWindow serverWindow) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);

        this.serverWindow = serverWindow;
        checkForMessages();
        panelTop.add(ipInput);
        panelTop.add(portInput);
        panelTop.add(loginInput);
        panelTop.add(passwordInput);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        add(buildBottomPanel(), BorderLayout.SOUTH);

        add(buildTextArea());
        setVisible(true);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isServerUp()) {
                    log.append("Login successfully\n");
                    log.append(getMessages(serverWindow.getLog(), MESSAGE_FLAG));
                    serverWindow.appendToLog(loginInput.getText() + " joined");
                    isClientConnected = true;
                } else {
                    log.append("Connection failed\n");
                }
            }
        });
    }

    private JPanel buildBottomPanel() {
        JPanel panBottom = new JPanel(new BorderLayout());
        JTextField message = new JTextField();
        JButton btnSend = new JButton("Send");

        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(message);
                }
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(message);
            }
        });

        panBottom.add(message, BorderLayout.CENTER);
        panBottom.add(btnSend, BorderLayout.EAST);

        return panBottom;
    }

    private JScrollPane buildTextArea() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private String getMessages(JTextArea serverLog, String messageFlag) {
        StringBuilder messageLog = new StringBuilder();
        String[] messages = serverLog.getText().split("\n");
        for (String s : messages) {
            if (s.contains(messageFlag)) {
                messageLog.append(s).append("\n");
            }
        }
        return messageLog.toString();
    }

    private void sendMessage(JTextField message) {
        if (isClientConnected && serverWindow.isServerUp()) {
            if (!message.getText().isEmpty()) {
                serverWindow.appendToLog(loginInput.getText() + "> " + message.getText());
                updateClientMessages();
                message.setText("");
            }
        } else {
            log.append("Your client has no server connection\n");
        }
    }

    private void updateClientMessages() {

        if (isServerHasNewMessage()) {
            String[] msgsServer = getMessages(serverWindow.getLog(), MESSAGE_FLAG).split("\n");
            String[] msgsClient = getMessages(log, MESSAGE_FLAG).split("\n");

            int clientMessageNumber, serverMessagesNumber;
            if (msgsClient[0].equals("")) {
                clientMessageNumber = 0;
            } else {
                clientMessageNumber = msgsClient.length;
            }
            if (msgsServer[0].equals("")) {
                serverMessagesNumber = 0;
            } else {
                serverMessagesNumber = msgsServer.length;
            }

            for (int i = clientMessageNumber; i < serverMessagesNumber; i++) {
                log.append(msgsServer[i] + "\n");
            }
        }
    }

    private boolean isServerHasNewMessage() {
        int serverMessagesNumber = getMessages(serverWindow.getLog(), MESSAGE_FLAG).split("\n").length;
        int clientMessagesNumber = getMessages(log, MESSAGE_FLAG).split("\n").length;
        if (clientMessagesNumber == 1 && getMessages(log, MESSAGE_FLAG).equals("")) clientMessagesNumber = 0;
        if (serverMessagesNumber == 1 && getMessages(serverWindow.getLog(), MESSAGE_FLAG).equals(""))
            serverMessagesNumber = 0;

        if (serverMessagesNumber > clientMessagesNumber) return true;

        return false;
    }

    private void checkForMessages() {

        Thread checkForMessages = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isClientConnected) {
                        if (!serverWindow.isServerUp()) {
                            isClientConnected = false;
                            log.append("Your client has been disconnected\n");
                        } else if (isServerHasNewMessage()) {
                            updateClientMessages();
                        }
                    }
                }
            }
        });

        checkForMessages.start();
    }
}