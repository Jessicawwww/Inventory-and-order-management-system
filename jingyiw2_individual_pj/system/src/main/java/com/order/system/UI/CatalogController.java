package com.order.system.UI;

import com.order.system.DAO.ItemDAO;
import com.order.system.DTO.ItemDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;

/**
 * @author Think
 * class for catalog scene
 */
public class CatalogController {
    @FXML
    private TableView<ItemDTO> catalog_table;

    @FXML
    private TableColumn<ItemDTO, String> colcategory;

    @FXML
    private TableColumn<ItemDTO, String> colcolor;

    @FXML
    private TableColumn<ItemDTO, String> coldescription;

    @FXML
    private TableColumn<ItemDTO, Number> coldiscount_price;

    @FXML
    private TableColumn<ItemDTO, Number> colprice;

    @FXML
    private TableColumn<ItemDTO, String> colsize;

    @FXML
    private TableColumn<ItemDTO,String> colsku_id;

    @FXML
    private TableColumn<ItemDTO, String> colsku_name;

    @FXML
    private TableColumn<ItemDTO, String> colspu_id;

    @FXML
    private TableColumn<ItemDTO, String> colspu_name;


    @FXML
    private void initialize() throws Exception{
        //getCatalogQueryResult
        //getCatalogObjects
        ResultSet rs = new ItemDAO().getCatalogQueryResult();
        colspu_id.setCellValueFactory(cellData -> cellData.getValue().spu_idProperty());
        colspu_name.setCellValueFactory(cellData -> cellData.getValue().spu_nameProperty());
        colsku_id.setCellValueFactory(cellData -> cellData.getValue().sku_idProperty());
        colsku_name.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colcategory.setCellValueFactory(cellData -> cellData.getValue().cate_nameProperty());
        coldescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        colsize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        colprice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        coldiscount_price.setCellValueFactory(cellData -> cellData.getValue().discount_priceProperty());
        colcolor.setCellValueFactory(cellData -> cellData.getValue().color_codeProperty());

        ObservableList<ItemDTO> catalogList = ItemDAO.getCatalogObjects(rs);
        catalog_table.setItems(catalogList);
    }
}
