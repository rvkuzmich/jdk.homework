package ru.gb.jdk.homework.hw1.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class ServerWindow extends JFrame {
    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    private final String TITLE = "Chat server";
    private final String LOG_PATH = "src/main/resources/log.txt";
    private JTextArea log;
    private boolean isServerUp;
    private String[] users;

    public ServerWindow() {
        isServerUp = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle(TITLE);
        setResizable(false);

        add(buildBottomPanel(), BorderLayout.SOUTH);
        add(buildTextArea());
        setVisible(true);
    }

    private JPanel buildBottomPanel() {
        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        JButton btnStart = new JButton("Start");
        JButton btnStop = new JButton("Stop");

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerUp) {
                    saveLogFile(LOG_PATH);
                    isServerUp = false;
                    appendToLog("Server stopped");
                } else {
                    appendToLog("Server is not running");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerUp) {
                    isServerUp = true;
                    appendToLog("Server started");
                } else {
                    appendToLog("Server is already running");
                }
            }
        });

        panBottom.add(btnStart);
        panBottom.add(btnStop);
        return panBottom;
    }

    public void appendToLog(String text) {
        log.append(text + "\n");
    }

    private JScrollPane buildTextArea() {
        log = new JTextArea();
        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        return scrollPane;
    }

    private void loadLogFile(String logPath) {
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(logPath))) {
            log.append(Arrays.toString(stream.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLogFile(String logPath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logPath))) {
            String text = log.getText();
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JTextArea getLog() {
        return log;
    }

    public boolean isServerUp() {
        return isServerUp;
    }
}