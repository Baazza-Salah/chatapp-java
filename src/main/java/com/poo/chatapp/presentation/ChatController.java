package com.poo.chatapp.presentation;


import com.poo.chatapp.utils.ChatClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ChatController {
    @FXML private TextArea chatArea;
    @FXML private TextField messageField;
    @FXML private Button sendButton;
    @FXML private Label statusLabel;
    @FXML private VBox rootContainer;

    private ChatClient client;
    private String username = "Unknown";

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    public void initialize() {
        client = new ChatClient(
                // Message callback
                message -> {
                    Platform.runLater(() -> {
                        chatArea.appendText(message + "\n");
                        chatArea.setScrollTop(Double.MAX_VALUE);
                    });
                },
                // Connection callback
                (connected, message) -> {
                    Platform.runLater(() -> {
                        statusLabel.setText(message);
                        statusLabel.setStyle(connected ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
                        messageField.setDisable(!connected);
                        sendButton.setDisable(!connected);

                        if (!connected) {
                            // Try to reconnect after 5 seconds
                            new Thread(() -> {
                                try {
                                    Thread.sleep(5000);
                                    client.connect();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                    });
                }
        );

        sendButton.setOnAction(e -> sendMessage());
        messageField.setOnAction(e -> sendMessage());

        // Initial connection
        new Thread(() -> client.connect()).start();
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            client.sendMessage(username + ": " + message);
            messageField.clear();
        }
    }
}