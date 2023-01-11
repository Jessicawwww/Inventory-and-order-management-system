import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;

import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.scene.control.TextFormatter;

public class Calculator extends Application{
    //three text fields
    private TextField number1 = new TextField();
    private TextField number2 = new TextField();
    private TextField result = new TextField();

    //four buttons
    private Button add = new Button("Add");
    private Button subtract = new Button("Subtract");
    private Button multiply = new Button("Multiply");
    private Button divide = new Button("Divide");

    @Override //override the start method in application class
    public void start(Stage primaryStage) {

        //set all button width to 100
        add.setPrefWidth(100);
        subtract.setPrefWidth(100);
        multiply.setPrefWidth(100);
        divide.setPrefWidth(100);
        result.setEditable(false);

        //set the number nodes
        HBox hBox = new HBox();
        hBox.getChildren().addAll(new Label("Number 1: "), number1, new Label("Number 2: "), number2,
                new Label("Result: "), result);
        //set hBox location
        hBox.setAlignment(Pos.TOP_CENTER);

        //set operator nodes
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.add(add,0,0);
        gridPane.add(subtract,1,0);
        gridPane.add(multiply,2,0);
        gridPane.add(divide,3,0);

        try {
            //limit the text fields to only accept numeric data types, other data types are forbidden.
            number1.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("^-?\\d{0,7}([\\.]\\d{0,4})?")) {
                        number1.setText(oldValue);
                    }
                }
            });
            number2.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("^-?\\d{0,7}([\\.]\\d{0,4})?")) {
                        number2.setText(oldValue);
                    }
                }
            });


            //controller
            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    result.setText(String.format("%.7f",Double.parseDouble(number1.getText())+Double.parseDouble(number2.getText())));
                }
            });
            subtract.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    result.setText(String.format("%.7f",Double.parseDouble(number1.getText())-Double.parseDouble(number2.getText())));
                }
            });
            multiply.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    result.setText(String.format("%.7f",Double.parseDouble(number1.getText())*Double.parseDouble(number2.getText())));
                }
            });
            divide.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    double d1 = Double.parseDouble(number1.getText());
                    double d2 = Double.parseDouble(number2.getText());
                    if (d2 == 0.0){
                        String label_content = "Notice: The divisor cannot be 0!";
                        Label noticeLabel =new Label(label_content);
                        gridPane.add(noticeLabel,1,1);
                        noticeLabel.setFont(new Font(30.0));
                    }
                    result.setText(String.format("%.7f",d1/d2));
                }
            });
        }catch (Exception e){ //in case of illegal operations
            String errorMessage = e.getMessage();
            Label noticeLabel =new Label(errorMessage);
            gridPane.add(noticeLabel,1,2);
        }


        //include two panels
        VBox root = new VBox(hBox, gridPane);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 800,300);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {      launch(args);         }


}
