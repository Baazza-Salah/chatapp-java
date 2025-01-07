package com.poo.chatapp.presentation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create first user window
        createUserWindow("User 1", 0);
        // Create second user window
        createUserWindow("User 2", 200);
    }

    private void createUserWindow(String username, int yOffset) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/poo/chatapp/chat.fxml"));
        Parent root = loader.load();

        ChatController controller = loader.getController();
        controller.setUsername(username);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/poo/chatapp/styles.css").toExternalForm());

        stage.setTitle("Chat - " + username);
        stage.setX(yOffset);
        stage.setY(yOffset);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}