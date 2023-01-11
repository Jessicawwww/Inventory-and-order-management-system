package com.order.system.UI;

import com.order.system.DAO.BuyerDAO;
import com.order.system.DAO.ItemDAO;
import com.order.system.DTO.BuyerDTO;
import com.order.system.DTO.QuarterSales;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * class for quarterly sales fxml
 */
public class QuarterSalesController {
    @FXML
    private TableView<QuarterSales> cate_rev_table;

    @FXML
    private TableColumn<QuarterSales, Number> colQ1;

    @FXML
    private TableColumn<QuarterSales, Number> colQ2;

    @FXML
    private TableColumn<QuarterSales, Number> colQ3;

    @FXML
    private TableColumn<QuarterSales, Number> colQ4;

    @FXML
    private TableColumn<QuarterSales, String> colcate;

    @FXML
    private PieChart salesPie;

    @FXML
    public void initialize(){
        //table view
        ResultSet rs = new ItemDAO().getQuarterSales();
        colcate.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        colQ1.setCellValueFactory(cellData -> cellData.getValue().q1Property());
        colQ2.setCellValueFactory(cellData -> cellData.getValue().q2Property());
        colQ3.setCellValueFactory(cellData -> cellData.getValue().q3Property());
        colQ4.setCellValueFactory(cellData -> cellData.getValue().q4Property());

        ObservableList<QuarterSales> salesList = ItemDAO.getQuarterSalesObjects(rs);
        cate_rev_table.setItems(salesList);

        //pie chart
        ArrayList<Integer> rev_list = new ItemDAO().getCateRatio();//shirt,trousers,jacket
        ObservableList<PieChart.Data> salePieData = FXCollections.observableArrayList(
                new PieChart.Data("Shirt",rev_list.get(0)),
                new PieChart.Data("Trousers",rev_list.get(1)),
                new PieChart.Data("Jacket",rev_list.get(2))
        );
        salePieData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(),"amount: ",data.pieValueProperty())));
        salesPie.getData().addAll(salePieData);

    }
}
