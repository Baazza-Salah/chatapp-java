package com.poo.chatapp.dao;

import com.poo.chatapp.metier.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageDAO class to handle message persistence.
 */
public class MessageDAO {
    private List<Message> messageList; // List to store messages temporarily

    /**
     * Constructor to initialize the MessageDAO.
     */
    public MessageDAO() {
        messageList = new ArrayList<>(); // Initialize the message list
    }

    /**
     * Method to add a message to the list.
     *
     * @param message The message to be added.
     */
    public void addMessage(Message message) {
        messageList.add(message); // Add message to the list
    }

    /**
     * Method to retrieve all messages.
     *
     * @return List of all messages.
     */
    public List<Message> getAllMessages() {
        return messageList; // Return the list of messages
    }

    /**
     * Method to clear all messages (for testing or reset purposes).
     */
    public void clearMessages() {
        messageList.clear(); // Clear the list of messages
    }
}