package com.order.system.UI;

import com.order.system.DAO.BuyerDAO;
import com.order.system.DTO.BuyerDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Think
 * class for buyer scene fxml
 */
public class BuyerController {
    @FXML
    private Button ButAdd;
    @FXML
    private Button ButEdit;
    @FXML
    private Button ButDelete;
    @FXML
    private Button ButClear;
    @FXML
    private Button ButSearch;
    @FXML
    private TextField txtuid;
    @FXML
    private TextField txtuser_name;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtpayment_info;
    @FXML
    private TextField txtphone;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtage;
    @FXML
    private TextField txtgender;
    @FXML
    private TextField txtincome;
    @FXML
    private TextField txtsearch;
    @FXML
    private void addBuyer(ActionEvent event) throws ClassNotFoundException, SQLException{
        BuyerDTO buyerDTO = new BuyerDTO();
        buyerDTO.setUid(Integer.parseInt(txtuid.getText()));
        buyerDTO.setUser_name(txtuser_name.getText());
        buyerDTO.setAddress(txtaddress.getText());
        buyerDTO.setPayment_info(txtpayment_info.getText());
        buyerDTO.setPhone(txtphone.getText());
        buyerDTO.setEmail(txtemail.getText());
        buyerDTO.setAge(txtage.getText());
        buyerDTO.setGender(txtgender.getText());
        buyerDTO.setIncome(txtincome.getText());
        new BuyerDAO().addBuyerDAO(buyerDTO);
        loadBuyerDataSet();
    }
    @FXML
    private void editBuyer(ActionEvent event) throws ClassNotFoundException, SQLException{
        BuyerDTO buyerDTO = new BuyerDTO();
        buyerDTO.setUid(Integer.parseInt(txtuid.getText()));
        buyerDTO.setUser_name(txtuser_name.getText());
        buyerDTO.setAddress(txtaddress.getText());
        buyerDTO.setPayment_info(txtpayment_info.getText());
        buyerDTO.setPhone(txtphone.getText());
        buyerDTO.setEmail(txtemail.getText());
        buyerDTO.setAge(txtage.getText());
        buyerDTO.setGender(txtgender.getText());
        buyerDTO.setIncome(txtincome.getText());
        new BuyerDAO().editBuyerDAO(buyerDTO);
        loadBuyerDataSet();
    }
    @FXML
    private void deleteBuyer(ActionEvent event) throws ClassNotFoundException, SQLException{
        int delUid = Integer.parseInt(txtuid.getText());
        new BuyerDAO().deleteBuyerDAO(delUid);
        loadBuyerDataSet();
    }
    @FXML
    private void clearBuyer(ActionEvent event) throws ClassNotFoundException, SQLException{
        txtuid.setText("");
        txtuser_name.setText("");
        txtaddress.setText("");
        txtpayment_info.setText("");
        txtphone.setText("");
        txtemail.setText("");
        txtage.setText("");
        txtgender.setText("");
        txtincome.setText("");
    }
    @FXML
    private void searchBuyer(ActionEvent event) throws ClassNotFoundException, SQLException{
        loadBuyerSearchData(txtsearch.getText());
    }

    @FXML
    private TableColumn<BuyerDTO,Number> colUid;
    @FXML
    private TableColumn<BuyerDTO,String> colUser_name;
    @FXML
    private TableColumn<BuyerDTO,String> colAddress;
    @FXML
    private TableColumn<BuyerDTO,String> colPayment_info;
    @FXML
    private TableColumn<BuyerDTO,String> colPhone;
    @FXML
    private TableColumn<BuyerDTO,String> colEmail;
    @FXML
    private TableColumn<BuyerDTO,String> colAge;
    @FXML
    private TableColumn<BuyerDTO,String> colGender;
    @FXML
    private TableColumn<BuyerDTO,String> colIncome;
    @FXML
    private TableView buyerTable;


    //load buyer table
    private void loadBuyerDataSet(){
        ResultSet rs = new BuyerDAO().getBuyerQueryResult();
        colUid.setCellValueFactory(cellData -> cellData.getValue().uidProperty());
        colUser_name.setCellValueFactory(cellData -> cellData.getValue().user_nameProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        colPayment_info.setCellValueFactory(cellData -> cellData.getValue().payment_infoProperty());
        colPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colAge.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        colGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        colIncome.setCellValueFactory(cellData -> cellData.getValue().incomeProperty());

        ObservableList<BuyerDTO> buyerList = BuyerDAO.getBuyerObjects(rs);
        buyerTable.setItems(buyerList);
    }


    public void loadBuyerSearchData(String text) {
        colUid.setCellValueFactory(cellData -> cellData.getValue().uidProperty());
        colUser_name.setCellValueFactory(cellData -> cellData.getValue().user_nameProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        colPayment_info.setCellValueFactory(cellData -> cellData.getValue().payment_infoProperty());
        colPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colAge.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        colGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        colIncome.setCellValueFactory(cellData -> cellData.getValue().incomeProperty());
        BuyerDAO bDAO = new BuyerDAO();
        ObservableList<BuyerDTO> buyerList = BuyerDAO.getBuyerObjects(bDAO.getBuyerSearch(text));
        buyerTable.setItems(buyerList);
    }
    @FXML
    private void initialize() throws Exception{
        ResultSet rs = new BuyerDAO().getBuyerQueryResult();
        colUid.setCellValueFactory(cellData -> cellData.getValue().uidProperty());
        colUser_name.setCellValueFactory(cellData -> cellData.getValue().user_nameProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        colPayment_info.setCellValueFactory(cellData -> cellData.getValue().payment_infoProperty());
        colPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colAge.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        colGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        colIncome.setCellValueFactory(cellData -> cellData.getValue().incomeProperty());

        ObservableList<BuyerDTO> buyerList = BuyerDAO.getBuyerObjects(rs);
        buyerTable.setItems(buyerList);
    }

}
