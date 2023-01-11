package com.order.system.DAO;

import com.order.system.Database.MysqlConnection;
import com.order.system.DTO.BuyerDTO;
import com.order.system.UI.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author jingyiwu
 * This method is used for implementing all buyer-realted actions.
 */

//data access objects for customers
public class BuyerDAO {
    //connection to database
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    //constructor
    /** Creates a buyerdao
     */
    public BuyerDAO(){
        try{
            conn = new MysqlConnection().getConn();
            statement = conn.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //add a new buyer
    /** add a new buyer
     * @param buyerDTO
     */
    public void addBuyerDAO(BuyerDTO buyerDTO){
        try{
            String query ="SELECT * FROM buyer WHERE user_name='"
                    +buyerDTO.getUser_name()
                    + "' AND address='"
                    +buyerDTO.getAddress()
                    + "' AND phone='"
                    +buyerDTO.getPhone()
                    + "' AND email='"
                    +buyerDTO.getEmail()
                    + "' AND age='"
                    +buyerDTO.getAge()
                    + "' AND gender='"
                    +buyerDTO.getGender()
                    + "' AND income='"
                    +buyerDTO.getIncome()
                    +"'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                LoginController.infoBox("Already have this buyer",null,"Failed");
            else
                addFunction(buyerDTO);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /** add a new buyer
     * @param buyerDTO
     */
    public void addFunction(BuyerDTO buyerDTO){
        try {
            //uid & user_name & address & payment &phone & email&age&gender&income
            String query = "INSERT INTO buyer VALUES(null,?,?,?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, buyerDTO.getUser_name());
            preparedStatement.setString(2, buyerDTO.getAddress());
            preparedStatement.setString(3, buyerDTO.getPayment_info());
            preparedStatement.setString(4, buyerDTO.getPhone());
            preparedStatement.setString(5, buyerDTO.getEmail());
            preparedStatement.setString(6, buyerDTO.getAge());
            preparedStatement.setString(7, buyerDTO.getGender());
            preparedStatement.setString(8, buyerDTO.getIncome());
            preparedStatement.executeUpdate();
            LoginController.infoBox("New Buyer Added",null,"Added");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /** edit an existing buyer using info on txtfield
     * @param buyerDTO
     */
    //edit the current buyer information
    public  void editBuyerDAO(BuyerDTO buyerDTO) {
        try {
            String query = "UPDATE buyer SET user_name=?,address=?,payment_info=?, phone=?, email=?, age=?,gender=?,income=? WHERE uid=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, buyerDTO.getUser_name());
            preparedStatement.setString(2, buyerDTO.getAddress());
            preparedStatement.setString(3, buyerDTO.getPayment_info());
            preparedStatement.setString(4, buyerDTO.getPhone());
            preparedStatement.setString(5, buyerDTO.getEmail());
            preparedStatement.setString(6, buyerDTO.getAge());
            preparedStatement.setString(7, buyerDTO.getGender());
            preparedStatement.setString(8, buyerDTO.getIncome());
            preparedStatement.setInt(9, buyerDTO.getUid());

            preparedStatement.executeUpdate();
            LoginController.infoBox("Buyer information has been updated",null,"Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** delete a buyer by its uid
     * @param uid
     */
    //delete existing buyer
    public void deleteBuyerDAO(int uid) {
        try {
            String query = "DELETE FROM buyer WHERE uid=" +uid;
            statement.executeUpdate(query);
            LoginController.infoBox("Buyer deleted",null,"Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** get buyer table query result
     */
    //Retrieve data from Mysql and display buyer table
    public ResultSet getBuyerQueryResult() {
        try {
            String query = "SELECT * FROM buyer";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    /** get buyer table search query result
     * @param text
     */
    // Method to retrieve search data
    public ResultSet getBuyerSearch(String text) {
        try {
            String query = "SELECT * FROM buyer " +
                    "WHERE uid = '%"+text+"%' OR user_name LIKE '%"+text+"%' OR " +
                    "address LIKE '%"+text+"%' OR payment_info LIKE '%"+text+"%' OR"+
                    " phone like '%"+text+"%' or email like '%"+text+"%' or"+
                    " age like '%"+text+"%' or gender like '%"+text+"%' or income like '%"+text+"%'"
                    ;
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    /** get buyer using its uid
     * @param uid
     */
    //method for getting all buyer info using its uid
    public ResultSet getBuyerName(int uid) {
        try {
            String query = "SELECT * FROM buyer WHERE uid=" +uid;
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    /** get sku name by id
     * @param sku_id
     */
    //method to retrieve stock info
    public ResultSet getItemName(String sku_id) {
        try {
            String query = "SELECT spu_name,item.sku_name,cate_name,itemstock.quantity,price,description FROM item " +
                    "INNER JOIN itemstock ON item.sku_id=itemstock.sku_id " +
                    "WHERE item.sku_id='" +sku_id+ "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    /** get list for table view on javafx
     */
    // to get an observable list of objects in the search result for table view
    public static ObservableList<BuyerDTO> getBuyerObjects(ResultSet resultSet)  {
        ObservableList<BuyerDTO> buyerList = FXCollections.observableArrayList();
        try{
            /*
            ResultSetMetaData metaData = resultSet.getMetaData();
            ArrayList<String> columnNames = new ArrayList<>();
            int colCount = metaData.getColumnCount();
            for (int col=1; col <= colCount; col++){
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
                }*/
            while (resultSet.next()){
                BuyerDTO buyerDTO = new BuyerDTO();
                buyerDTO.setUid(resultSet.getInt("uid"));
                buyerDTO.setUser_name(resultSet.getString("user_name"));
                buyerDTO.setAddress(resultSet.getString("address"));
                buyerDTO.setPayment_info(resultSet.getString("payment_info"));
                buyerDTO.setPhone(resultSet.getString("phone"));
                buyerDTO.setEmail(resultSet.getString("email"));
                buyerDTO.setAge(resultSet.getString("age"));
                buyerDTO.setGender(resultSet.getString("gender"));
                buyerDTO.setIncome(resultSet.getString("income"));
                buyerList.add(buyerDTO);
            }
        }catch(SQLException e){
            System.out.println("Error occurred while fetching data from DB "+e);
            e.printStackTrace();
        }
        return buyerList;
    }


}
