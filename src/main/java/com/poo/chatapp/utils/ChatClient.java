package com.poo.chatapp.utils;
import javafx.application.Platform;

import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private MessageCallback messageCallback;
    private ConnectionCallback connectionCallback;
    private volatile boolean running = false;

    public interface MessageCallback {
        void onMessage(String message);
    }

    public interface ConnectionCallback {
        void onConnectionStatus(boolean connected, String message);
    }

    public ChatClient(MessageCallback messageCallback, ConnectionCallback connectionCallback) {
        this.messageCallback = messageCallback;
        this.connectionCallback = connectionCallback;
    }

    public void connect() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            running = true;

            // Start message receiving thread
            new Thread(this::receiveMessages).start();

            Platform.runLater(() ->
                    connectionCallback.onConnectionStatus(true, "Connected to server")
            );
        } catch (IOException e) {
            Platform.runLater(() ->
                    connectionCallback.onConnectionStatus(false, "Failed to connect: " + e.getMessage())
            );
        }
    }

    private void receiveMessages() {
        while (running && !socket.isClosed()) {
            try {
                String message = in.readLine();
                if (message == null) {
                    break;
                }
                Platform.runLater(() -> messageCallback.onMessage(message));
            } catch (IOException e) {
                if (running) {
                    Platform.runLater(() ->
                            connectionCallback.onConnectionStatus(false, "Lost connection to server")
                    );
                }
                break;
            }
        }
        disconnect();
    }

    public void sendMessage(String message) {
        if (out != null && !socket.isClosed()) {
            out.println(message);
        }
    }

    public void disconnect() {
        running = false;
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
