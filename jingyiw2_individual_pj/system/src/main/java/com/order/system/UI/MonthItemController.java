package com.order.system.UI;
import com.order.system.DAO.ItemDAO;
import com.order.system.DTO.MonthItem;
import com.order.system.DTO.QuarterSales;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/** monthly item fxml controller
 */

public class MonthItemController {
    @FXML
    private Button Butserach;

    @FXML
    private TableView<MonthItem> Item_table;

    @FXML
    private TableColumn<MonthItem, String> colcate;

    @FXML
    private TableColumn<MonthItem, String> coldescription;

    @FXML
    private TableColumn<MonthItem, Number> colprice;

    @FXML
    private TableColumn<MonthItem, Number> colrevenue;

    @FXML
    private TableColumn<MonthItem, String> colsku_name;

    @FXML
    private BarChart<String, Integer> item_bar;

    @FXML
    private TextField txtend;

    @FXML
    private TextField txtstart;

    @FXML
    void searchItem(ActionEvent event) {
        initialize();
        //table view
        ResultSet rs = new ItemDAO().getMonthItemData(txtstart.getText(),txtend.getText());
        colcate.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        colsku_name.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colprice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        coldescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        colrevenue.setCellValueFactory(cellData -> cellData.getValue().revenueProperty());

        ObservableList<MonthItem> monthItemList = ItemDAO.getMonthItemObjects(rs);
        Item_table.setItems(monthItemList);

    }

    @FXML
    public void initialize(){
        //bar chart
        ArrayList<Map> result = new ItemDAO().getItemBarData();
        Map<String,Integer> barData_shirt = result.get(0);
        Map<String,Integer> barData_trousers = result.get(1);
        Map<String,Integer> barData_jacket = result.get(2);

        //series shirt
        XYChart.Series<String,Integer> series_shirt = new XYChart.Series();
        series_shirt.setName("Shirt");
        for (String key: barData_shirt.keySet()){
            //System.out.println(key+" "+barData_shirt.get(key));
            series_shirt.getData().add(new XYChart.Data(key,barData_shirt.get(key)));
        }
        //series trousers
        XYChart.Series<String,Integer> series_trousers = new XYChart.Series();
        series_trousers.setName("trousers");
        for (String key: barData_trousers.keySet()){
            //System.out.println(key+" "+barData.get(key));
            series_trousers.getData().add(new XYChart.Data(key,barData_trousers.get(key)));
        }
        //series jacket
        XYChart.Series<String,Integer> series_jacket = new XYChart.Series();
        series_jacket.setName("Item");
        for (String key: barData_jacket.keySet()){
            //System.out.println(key+" "+barData.get(key));
            series_jacket.getData().add(new XYChart.Data(key,barData_jacket.get(key)));
        }
        item_bar.getData().addAll(series_shirt,series_trousers,series_jacket);
    }
}
