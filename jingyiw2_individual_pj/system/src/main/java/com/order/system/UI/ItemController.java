package com.order.system.UI;

import com.order.system.DAO.ItemDAO;
import com.order.system.DTO.ItemDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.ResultSet;

/**
 * @author Think
 * class for item scene
 */
public class ItemController {
    @FXML
    private Button ButAdd;

    @FXML
    private Button ButClear;

    @FXML
    private Button ButDelete;

    @FXML
    private Button ButEdit;

    @FXML
    private Button ButSearch;

    @FXML
    private TableColumn<ItemDTO, String> colCate;

    @FXML
    private TableColumn<ItemDTO, String> colColor;

    @FXML
    private TableColumn<ItemDTO, String> colDescription;

    @FXML
    private TableColumn<ItemDTO,Number> colPrice;

    @FXML
    private TableColumn<ItemDTO, String> colSize;

    @FXML
    private TableColumn<ItemDTO, String> colSku;

    @FXML
    private TableColumn<ItemDTO, String> colSpu;

    @FXML
    private TableView itemTable;

    @FXML
    private TextField txtcate_id;

    @FXML
    private TextField txtcate_name;

    @FXML
    private TextField txtcolor_code;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtsearch;

    @FXML
    private TextField txtsize;

    @FXML
    private TextField txtsku_id;

    @FXML
    private TextField txtsku_name;

    @FXML
    private TextField txtspu_id;

    @FXML
    private TextField txtspu_name;

    @FXML
    private TextField txtquantity;

    ItemDTO itemDTO;

    @FXML
    void addItem(ActionEvent event) {
        itemDTO = new ItemDTO();
        itemDTO.setSpu_id(txtspu_id.getText());
        itemDTO.setSpu_name(txtspu_name.getText());
        itemDTO.setSku_id(txtsku_id.getText());
        itemDTO.setSku_name(txtsku_name.getText());
        itemDTO.setCate_id(txtcate_id.getText());
        itemDTO.setCate_name(txtcate_name.getText());
        itemDTO.setColor_code(txtcolor_code.getText());
        itemDTO.setSize(txtsize.getText());
        itemDTO.setPrice(Integer.parseInt(txtprice.getText()));
        itemDTO.setDescription(txtdescription.getText());
        itemDTO.setQuantity(Integer.parseInt(txtquantity.getText()));
        new ItemDAO().addItemDAO(itemDTO);
        loadItemDataSet();
    }

    @FXML
    void clearItem(ActionEvent event) {
        txtspu_id.setText("");
        txtspu_name.setText("");
        txtsku_id.setText("");
        txtsku_name.setText("");
        txtcate_id.setText("");
        txtcate_name.setText("");
        txtcolor_code.setText("");
        txtsize.setText("");
        txtprice.setText("");
        txtdescription.setText("");
        txtquantity.setText("");
    }

    @FXML
    void deleteItem(ActionEvent event) {
        new ItemDAO().deleteItemDAO(txtsku_id.getText());
        loadItemDataSet();
    }

    @FXML
    void editItem(ActionEvent event) {
        itemDTO = new ItemDTO();
        itemDTO.setSpu_id(txtspu_id.getText());
        itemDTO.setSpu_name(txtspu_name.getText());
        itemDTO.setSku_id(txtsku_id.getText());
        itemDTO.setSku_name(txtsku_name.getText());
        itemDTO.setCate_id(txtcate_id.getText());
        itemDTO.setCate_name(txtcate_name.getText());
        itemDTO.setColor_code(txtcolor_code.getText());
        itemDTO.setSize(txtsize.getText());
        itemDTO.setPrice(Integer.parseInt(txtprice.getText()));
        itemDTO.setDescription(txtdescription.getText());
        itemDTO.setQuantity(Integer.parseInt(txtquantity.getText()));
        new ItemDAO().editItemDAO(itemDTO);
        loadItemDataSet();

    }

    @FXML
    void searchItem(ActionEvent event) {
        loadSearchItemData(txtsearch.getText());
    }

    private void loadSearchItemData(String text) {
        ResultSet rs = new ItemDAO().getItemSearch(text);
        colSpu.setCellValueFactory(cellData -> cellData.getValue().spu_nameProperty());
        colSku.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colCate.setCellValueFactory(cellData -> cellData.getValue().cate_nameProperty());
        colColor.setCellValueFactory(cellData -> cellData.getValue().color_codeProperty());
        colSize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        colDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        ObservableList<ItemDTO> itemList = new ItemDAO().getItemObjects(rs);
        itemTable.setItems(itemList);
    }

    @FXML
    public void initialize(){
        ResultSet rs = new ItemDAO().getItemQueryResult();
        colSpu.setCellValueFactory(cellData -> cellData.getValue().spu_nameProperty());
        colSku.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colCate.setCellValueFactory(cellData -> cellData.getValue().cate_nameProperty());
        colColor.setCellValueFactory(cellData -> cellData.getValue().color_codeProperty());
        colSize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        colDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        ObservableList<ItemDTO> itemList = new ItemDAO().getItemObjects(rs);
        itemTable.setItems(itemList);
    }

    /**
     * class for load data set after each action
     */
    public void loadItemDataSet(){
        ResultSet rs = new ItemDAO().getItemQueryResult();
        colSpu.setCellValueFactory(cellData -> cellData.getValue().spu_nameProperty());
        colSku.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colCate.setCellValueFactory(cellData -> cellData.getValue().cate_nameProperty());
        colColor.setCellValueFactory(cellData -> cellData.getValue().color_codeProperty());
        colSize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        colDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        ObservableList<ItemDTO> itemList = new ItemDAO().getItemObjects(rs);
        itemTable.setItems(itemList);
    }
}
