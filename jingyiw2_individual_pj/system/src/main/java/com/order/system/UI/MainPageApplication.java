package com.order.system.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.event.Event;

import java.io.IOException;

/**
 * main class
 * entry for order system
 */
public class MainPageApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/order/system/Login.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(MainPageApplication.class.getResource("Login.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CSR Login");
        stage.setScene(new Scene(root, 800,600));
        stage.setOnShown(new EventHandler() {
            @Override
            public void handle(Event event) {
                // TODO Auto-generated method stub
                System.out.println("event=" + event);

            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}