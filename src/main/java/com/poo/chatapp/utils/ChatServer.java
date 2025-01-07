package com.poo.chatapp.utils;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 5000;
    private static List<ConnectionHandler> handlers = new ArrayList<>();

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server started on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                ConnectionHandler handler = new ConnectionHandler(socket);
                handlers.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to broadcast message to all clients except sender
    public static void broadcastMessage(String message, ConnectionHandler sender) {
        for (ConnectionHandler handler : handlers) {
            if (handler != sender) {
                handler.sendMessage(message);
            }
        }
    }
}