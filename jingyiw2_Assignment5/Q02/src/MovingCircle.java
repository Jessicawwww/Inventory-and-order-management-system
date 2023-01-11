import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;

public class MovingCircle extends Application{
    @Override
    public void start(Stage primaryStage){
        //create a pane first
        Pane pane = new Pane();

        //create a circle
        Circle circle = new Circle(50,50,50);
        circle.setRadius(30);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);

        //set the animation
        pane.setOnKeyPressed(e->{
            switch (e.getCode()){
                case UP:
                    if (circle.getCenterY()>circle.getRadius()){
                        circle.setCenterY(circle.getCenterY()-10);
                    }
                    break;
                case DOWN:
                    if (circle.getCenterY() < pane.getHeight() - circle.getRadius()){
                        circle.setCenterY(circle.getCenterY()+10);
                    }
                    break;
                case LEFT:
                    if (circle.getCenterX() > circle.getRadius()){
                        circle.setCenterX(circle.getCenterX() - 10);
                    }
                    break;
                case RIGHT:
                    if (circle.getCenterX() < pane.getWidth() - circle.getRadius()){
                        circle.setCenterX(circle.getCenterX() + 10);
                    }
                    break;
            }
        });
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Moving circle with mouse arrow key"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Must request focus after the primary stage is displayed
        pane.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
