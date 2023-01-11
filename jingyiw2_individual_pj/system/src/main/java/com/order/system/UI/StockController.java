package com.order.system.UI;

import com.order.system.DAO.BuyerDAO;
import com.order.system.DAO.ItemDAO;
import com.order.system.DTO.BuyerDTO;
import com.order.system.DTO.ItemDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;

/**
 * class for stock scene
 */
public class StockController {
    @FXML
    private TableColumn<ItemDTO, String> colcategory;

    @FXML
    private TableColumn<ItemDTO, String> coldescription;

    @FXML
    private TableColumn<ItemDTO, Number> colprice;

    @FXML
    private TableColumn<ItemDTO, Number> colquantity;

    @FXML
    private TableColumn<ItemDTO, String> colsku_name;

    @FXML
    private TableColumn<ItemDTO, String> colspu_name;

    @FXML
    private TableView stock_table;

    @FXML
    private void initialize() throws Exception{
        //getStockInfo
        //getStockObjects
        ResultSet rs = new ItemDAO().getStockInfo();
        colspu_name.setCellValueFactory(cellData -> cellData.getValue().spu_nameProperty());
        colsku_name.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colcategory.setCellValueFactory(cellData -> cellData.getValue().cate_nameProperty());
        colquantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        colprice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        coldescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        ObservableList<ItemDTO> stockList = ItemDAO.getStockObjects(rs);
        stock_table.setItems(stockList);
    }

}
