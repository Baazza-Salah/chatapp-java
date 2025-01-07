package com.poo.chatapp.presentation;


import com.poo.chatapp.utils.ChatServer;

public class ServerApplication {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.startServer();
    }
}