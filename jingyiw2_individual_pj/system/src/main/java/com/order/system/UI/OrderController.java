package com.order.system.UI;
import com.order.system.DAO.ItemDAO;
import com.order.system.DTO.ItemDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Think
 * class for order fxml
 */
public class OrderController {
    @FXML
    private Button ButAdd;

    @FXML
    private Button ButClear;

    @FXML
    private Button ButDelete;

    @FXML
    private Button ButSearch;

    @FXML
    private TableView Order_table;

    @FXML
    private TableColumn<ItemDTO, Number> colAmount;

    @FXML
    private TableColumn<ItemDTO, Date> colOrder_date;

    @FXML
    private TableColumn<ItemDTO, Number> colOrder_id;

    @FXML
    private TableColumn<ItemDTO, Number> colRevenue;

    @FXML
    private TableColumn<ItemDTO, String> colSku_id;

    @FXML
    private TableColumn<ItemDTO,String > colSku_name;

    @FXML
    private TableColumn<ItemDTO, String> colStatus;

    @FXML
    private TableColumn<ItemDTO, Number> colUid;

    @FXML
    private TextArea sku_info_box;

    @FXML
    private TextField txtamount;

    @FXML
    private TextField txtorder_date;

    @FXML
    private TextField txtorder_id;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtrevenue;

    @FXML
    private TextField txtsearch;

    @FXML
    private TextField txtsku_id;

    @FXML
    private TextField txtstatus;

    @FXML
    private TextField txtuid;

    @FXML
    private TextArea uid_info_box;

    int quantity,price;
    String sku_id;
    int revenue;

    @FXML
    void showUid(ActionEvent event){
        if (uid_info_box.getText()!=null){
            try{
                uid_info_box.setText(new ItemDAO().getBuyerInfoText(Integer.parseInt(txtuid.getText())));
            } catch(SQLException e){
                uid_info_box.setText("Invalid uid");
            }
        }
    }

    @FXML
    void showSku(ActionEvent event){
        if (sku_info_box.getText()!=null){
            try{
                sku_info_box.setText("Price: "+String.valueOf(new ItemDAO().getItemPrice(txtsku_id.getText()))+
                        "| Stock quantity: "+String.valueOf(new ItemDAO().getStock(txtsku_id.getText())));
                txtprice.setText(String.valueOf(new ItemDAO().getItemPrice(txtsku_id.getText())));
            }catch (SQLException e){
                sku_info_box.setText("Invalid sku_id");
            }
        }
    }

    @FXML
    void addOrder(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO();
        try{
            quantity = Integer.parseInt(txtamount.getText());
            price = new ItemDAO().getItemPrice(txtsku_id.getText());

            itemDTO.setUid(Integer.parseInt(txtuid.getText()));
            itemDTO.setSku_id(txtsku_id.getText());
            itemDTO.setQuantity(quantity);
            itemDTO.setOrder_date(java.sql.Date.valueOf(txtorder_date.getText()));
            revenue = quantity*price;

            if (quantity>new ItemDAO().getStock(txtsku_id.getText())){
                itemDTO.setRevenue(0);
                itemDTO.setStatus("back order");
            }else{
                itemDTO.setRevenue(revenue);
                itemDTO.setStatus("Successful");
            }
            new ItemDAO().sellItemDAO(itemDTO);
            loadOrderDataSet();
        }catch (SQLException e){
            sku_info_box.setText("Invalid sku_id");
        }

    }

    @FXML
    void clearOrder(ActionEvent event) {
        txtorder_id.setText("");
        txtuid.setText("");
        txtsku_id.setText("");
        txtprice.setText("");
        txtamount.setText("");
        txtorder_date.setText("");
        txtrevenue.setText("");
        txtstatus.setText("");
        loadOrderDataSet();
    }

    @FXML
    void deleteOrder(ActionEvent event) {
        new ItemDAO().deleteOrderDAO(Integer.parseInt(txtorder_id.getText()));
        new ItemDAO().deletOrderAddStock(txtsku_id.getText(),Integer.parseInt(txtamount.getText()));
        loadOrderDataSet();
    }

    @FXML
    void searchOrder(ActionEvent event) {
        loadSearchOrderData(txtsearch.getText());
    }

    private void loadSearchOrderData(String text) {
        //    public ResultSet getOrderSearch(String text)
        ResultSet rs = new ItemDAO().getOrderSearch(text);
        colOrder_id.setCellValueFactory(cellData -> cellData.getValue().order_idProperty());
        colUid.setCellValueFactory(cellData -> cellData.getValue().uidProperty());
        colSku_id.setCellValueFactory(cellData -> cellData.getValue().sku_idProperty());
        colSku_name.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colAmount.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        colOrder_date.setCellValueFactory(cellData -> cellData.getValue().order_dateProperty());
        colRevenue.setCellValueFactory(cellData -> cellData.getValue().revenueProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ObservableList<ItemDTO> orderList = ItemDAO.getOrderObjects(rs);
        Order_table.setItems(orderList);
    }

    @FXML
    public void initialize(){
        //getOrderInfo getOrderObjects
        ResultSet rs = new ItemDAO().getOrderInfo();
        colOrder_id.setCellValueFactory(cellData -> cellData.getValue().order_idProperty());
        colUid.setCellValueFactory(cellData -> cellData.getValue().uidProperty());
        colSku_id.setCellValueFactory(cellData -> cellData.getValue().sku_idProperty());
        colSku_name.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colAmount.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        colOrder_date.setCellValueFactory(cellData -> cellData.getValue().order_dateProperty());
        colRevenue.setCellValueFactory(cellData -> cellData.getValue().revenueProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ObservableList<ItemDTO> orderList = ItemDAO.getOrderObjects(rs);
        Order_table.setItems(orderList);
    }

    /**
     * class for loading dataset after each action
     */
    public void loadOrderDataSet(){
        ResultSet rs = new ItemDAO().getOrderInfo();
        colOrder_id.setCellValueFactory(cellData -> cellData.getValue().order_idProperty());
        colUid.setCellValueFactory(cellData -> cellData.getValue().uidProperty());
        colSku_id.setCellValueFactory(cellData -> cellData.getValue().sku_idProperty());
        colSku_name.setCellValueFactory(cellData -> cellData.getValue().sku_nameProperty());
        colAmount.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        colOrder_date.setCellValueFactory(cellData -> cellData.getValue().order_dateProperty());
        colRevenue.setCellValueFactory(cellData -> cellData.getValue().revenueProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ObservableList<ItemDTO> orderList = ItemDAO.getOrderObjects(rs);
        Order_table.setItems(orderList);
    }
}
