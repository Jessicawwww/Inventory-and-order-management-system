import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import java.util.Arrays;
import javafx.scene.chart.StackedBarChart;

public class StackBarChart extends Application {
    //add five countries here
    final String country1 = "United States";
    final String country2 = "Germany";
    final String country3 = "Denmark";
    final String country4 = "Mexico";
    final String country5 = "India";

    @Override public void start(Stage primaryStage) {
        //add x and y axes for this chart
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Country");
        yAxis.setLabel("Number");

        //create stacked bar chart
        final StackedBarChart<String,Number> stackedBarChart = new StackedBarChart<String, Number>(xAxis,yAxis);
        stackedBarChart.setTitle("Sales of Three Sources for 5 Countries ");

        //add numbers of three sources for 5 countries
        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(country1, country2, country3, country4, country5)));
        series1.setName("Consumer");
        series1.getData().add(new XYChart.Data<>("United States", 25000));
        series1.getData().add(new XYChart.Data<>("Germany", 30000));
        series1.getData().add(new XYChart.Data<>("Denmark", 12000));
        series1.getData().add(new XYChart.Data<>("Mexico", 40000));
        series1.getData().add(new XYChart.Data<>("India", 15000));

        final XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Corporate");
        series2.getData().add(new XYChart.Data<>("United States", 60000));
        series2.getData().add(new XYChart.Data<>("Germany", 35000));
        series2.getData().add(new XYChart.Data<>("Denmark", 45000));
        series2.getData().add(new XYChart.Data<>("Mexico", 100000));
        series2.getData().add(new XYChart.Data<>("India", 10000));

        final XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Home Office");
        series3.getData().add(new XYChart.Data<>("United States", 65000));
        series3.getData().add(new XYChart.Data<>("Germany", 45000));
        series3.getData().add(new XYChart.Data<>("Denmark", 18000));
        series3.getData().add(new XYChart.Data<>("Mexico", 25000));
        series3.getData().add(new XYChart.Data<>("India", 90000));

        stackedBarChart.getData().addAll(series1, series2, series3);
        //set the properties for the bar chart
        stackedBarChart.setCategoryGap(40);
        stackedBarChart.getStylesheets().add(getClass().getResource("colored-chart.css").toExternalForm());
        //create a scene to place the chart
        Scene scene  = new Scene(stackedBarChart);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sales Data");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
