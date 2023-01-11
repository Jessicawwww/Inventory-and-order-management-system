package com.order.system.UI;

import com.order.system.Database.MysqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class for login fxml
 */
public class LoginController {

    @FXML
    private Button cancel;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user_name;


    @FXML
    void cancel(ActionEvent event) {
        user_name.setText("");
        password.setText("");
    }

    @FXML
    void login() throws SQLException, IOException {
        Window owner = login.getScene().getWindow();
        if (user_name.getText().isEmpty()){
            showAlert(AlertType.ERROR,owner,"Form Error!","Please enter the username");
            return;
        }
        if (password.getText().isEmpty()){
            showAlert(AlertType.ERROR,owner,"Form Error!","Please enter a password");
            return;
        }
        String user_nameText  = user_name.getText();
        String passwordText = password.getText();
        if (new MysqlConnection().checkLogin(user_nameText,passwordText)){
            infoBox("Login Successful!",null,"Done");
            //link to other fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/com/order/system/Menu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 800);
            stage.setTitle("Menu");
            System.out.println("Menu opened successfully");
            stage.setScene(scene);

            stage.show();
            stage.setOnCloseRequest(event -> {
                System.out.println("close current form");
                stage.close();
            });

        }else{
            infoBox("Please enter right username and password",null,"Failed");
        }

    }

    /**
     * class for showing alert messages
     * @param infoMessage
     * @param headerText
     * @param title
     */
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
