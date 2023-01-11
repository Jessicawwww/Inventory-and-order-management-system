package com.order.system.DAO;

import com.order.system.DTO.BuyerDTO;
import com.order.system.DTO.MonthItem;
import com.order.system.DTO.QuarterSales;
import com.order.system.Database.MysqlConnection;
import com.order.system.DTO.ItemDTO;
import com.order.system.UI.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.PublicKey;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/** item actions implementation
 * @author Think
 */
public class ItemDAO {
    //connection to database
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    PreparedStatement preparedStatement2 = null;
    Statement statement2 = null;
    ResultSet resultSet = null;

    //constructor
    public ItemDAO() {
        try {
            conn = new MysqlConnection().getConn();
            statement = conn.createStatement();
            statement2 = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** get buyer table query result
     */
    //methods for getting results from database tables
    public ResultSet getBuyerInfo() {
        try {
            String query = "select * from buyer";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("get buyer info failed");
        }
        return resultSet;
    }
    //stock table
    /** get item stock table query result
     */
    public ResultSet getItemStock() {
        try{
            String query = "select * from itemstock";
            resultSet = statement.executeQuery(query);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("get stock info failed");
        }
        return resultSet;
    }
    //item table
    /** get item table query result
     */
    public ResultSet getItemInfo(){
        try{
            String query = "select * from item";
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get item info failed");
        }
        return resultSet;
    }
    //using sku_id to get sku price
    /** using sku_id to get sku price
     */
    public Integer getItemPrice(String sku_id) throws SQLException{
        Integer price = null;
        try{
            String query = "select price from item where sku_id='"+sku_id+"'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                price = resultSet.getInt("price");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get item price failed");
            throw e;
        }
        return price;
    }

    String sku_id;
    /** from name to id (backup methods
     */
    public String getSku_id(String sku_name){
        try {
            String query = "select sku_id from item where sku_name='"+sku_name+"'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                sku_id = resultSet.getString("sku_id");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("get sku_id from sku_name failed");
        }
        return sku_id;
    }

    /** using user name to get uid
     */
    Integer uid;

    public Integer getUid(String user_name){
        try{
            String query = "select uid from buyer where user_name='"+user_name+"'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                uid = resultSet.getInt("uid");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("get uid from user_name failed");
        }
        return uid;
    }
    /** method for check the availability of stock
     */
    //method for check the availability of stock
    boolean flag = false;
    public int getStock(String sku_id) throws SQLException{
        int stockquantity=0;
        try {
            String query = "SELECT * FROM itemstock WHERE sku_id='" +sku_id+ "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                stockquantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check stock info failed");
            throw e;
        }
        return stockquantity;
    }


    //add a new item
    /** method for adding a new item
     */
    public void addItemDAO(ItemDTO itemDTO){
        try{
            String query ="SELECT * FROM item WHERE spu_id='"
                    +itemDTO.getSpu_id()
                    + "' AND spu_name='"
                    +itemDTO.getSpu_name()
                    + "' AND sku_id='"
                    +itemDTO.getSku_id()
                    + "' AND sku_name='"
                    +itemDTO.getSku_name()
                    + "' AND cate_id='"
                    +itemDTO.getCate_id()
                    + "' AND cate_name='"
                    +itemDTO.getCate_name()
                    + "' AND color_code='"
                    +itemDTO.getColor_code()
                    + "' AND size='"
                    +itemDTO.getSize()
                    + "' AND price='"
                    +itemDTO.getPrice()
                    + "' AND description='"
                    +itemDTO.getDescription()
                    +"'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                LoginController.infoBox("Already have this item",null,"Failed");
            else
                addFunction(itemDTO);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addFunction(ItemDTO itemDTO){
        try {
            //uid & user_name & address & payment &phone & email&age&gender&income
            String query = "INSERT INTO item VALUES(?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, itemDTO.getSpu_id());
            preparedStatement.setString(2, itemDTO.getSpu_name());
            preparedStatement.setString(3, itemDTO.getSku_id());
            preparedStatement.setString(4, itemDTO.getSku_name());
            preparedStatement.setString(5, itemDTO.getCate_id());
            preparedStatement.setString(6, itemDTO.getCate_name());
            preparedStatement.setString(7, itemDTO.getColor_code());
            preparedStatement.setString(8, itemDTO.getSize());
            preparedStatement.setInt(9, itemDTO.getPrice());
            preparedStatement.setString(10, itemDTO.getDescription());
            preparedStatement.executeUpdate();

            String query2 =  "INSERT INTO itemstock VALUES(?,?,?)";
            preparedStatement2 = conn.prepareStatement(query2);
            preparedStatement2.setString(1,itemDTO.getSku_id());
            preparedStatement2.setString(2,itemDTO.getSku_name());
            preparedStatement2.setInt(3,itemDTO.getQuantity());
            preparedStatement2.executeUpdate();

            LoginController.infoBox("New Item Added",null,"Added");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //edit the current item information

    /**
     * edit the current item information
     * @param itemDTO
     */
    public void editItemDAO(ItemDTO itemDTO) {
        try {
            String query = "UPDATE item SET spu_id=?,spu_name=?,sku_name=?,cate_id=?,cate_name=?,color_code=?,size=?,price=?,description=? " +
                    "WHERE sku_id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, itemDTO.getSpu_id());
            preparedStatement.setString(2, itemDTO.getSpu_name());
            preparedStatement.setString(3, itemDTO.getSku_name());
            preparedStatement.setString(4, itemDTO.getCate_id());
            preparedStatement.setString(5, itemDTO.getCate_name());
            preparedStatement.setString(6, itemDTO.getColor_code());
            preparedStatement.setString(7, itemDTO.getSize());
            preparedStatement.setInt(8, itemDTO.getPrice());
            preparedStatement.setString(9, itemDTO.getDescription());
            preparedStatement.setString(10, itemDTO.getSku_id());
            preparedStatement.executeUpdate();
            //the amount to be edited
            String query2 = "UPDATE itemstock SET quantity=quantity+? where sku_id=?";
            preparedStatement2 = conn.prepareStatement(query2);
            preparedStatement2.setInt(1,itemDTO.getQuantity());
            preparedStatement2.setString(2,itemDTO.getSku_id());
            preparedStatement2.executeUpdate();
            LoginController.infoBox("Item information has been updated",null,"Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete existing item and its stock
     * @param sku_id
     */
    //delete existing item and its stock
    public void deleteItemDAO(String sku_id) {
        try {
            String query = "DELETE FROM item WHERE sku_id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, sku_id);

            String query2 = "DELETE FROM itemstock WHERE sku_id=?";
            preparedStatement2 = conn.prepareStatement(query2);
            preparedStatement2.setString(1, sku_id);

            preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();

            LoginController.infoBox("Item deleted",null,"Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error happened when deleting items");
        }
        deleteStock();
    }
    private void deleteStock() {
        try{
            String query ="DELETE FROM sales WHERE sku_id NOT IN(SELECT sku_id FROM item)";
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("error happened by syncing order table");
        }
    }

    //order processing
    public void deleteOrderDAO(int order_id){
        try{
            String query = "DELETE FROM sales WHERE order_id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,order_id);
            preparedStatement.executeUpdate();
            LoginController.infoBox("Order deleted",null,"Deleted");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("error happened when deleting orders");
        }
        deleteStock();
    }

    //handle updating of stocks upon any transaction orders
    //verify if there is stock and adjust the stock based on orders
    public void editSoldStock(String sku_id, int amount){
        try{
            String query  = "SELECT * FROM itemstock WHERE  sku_id='"+sku_id+"'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                String query2 = "UPDATE itemstock SET quantity=quantity-? WHERE sku_id=?";
                preparedStatement = conn.prepareStatement(query2);
                preparedStatement.setInt(1,amount);
                preparedStatement.setString(2,sku_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("error happened in updating stock info");
        }
    }
    public void deletOrderAddStock(String sku_id, int amount){
        try{
            String query  = "SELECT * FROM itemstock WHERE  sku_id='"+sku_id+"'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                String query2 = "UPDATE itemstock SET quantity=quantity+? WHERE sku_id=?";
                preparedStatement = conn.prepareStatement(query2);
                preparedStatement.setInt(1,amount);
                preparedStatement.setString(2,sku_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("error happened in updating stock info");
        }
    }
    //sales transaction handling

    /**
     * make an order
     * @param itemDTO
     */
    public void sellItemDAO(ItemDTO itemDTO){
        int quantity=0;
        String sku_id = null;
        String status = null;
        try{
            String query ="SELECT * FROM itemstock WHERE sku_id='" +itemDTO.getSku_id()+ "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                sku_id = resultSet.getString("sku_id");
                quantity = resultSet.getInt("quantity");
            }
            if (itemDTO.getQuantity()>quantity){
                LoginController.infoBox("Insufficient stock for this SKU",null,"Failed");
                status = "back order";
            }
            else if (itemDTO.getQuantity()<=0)
                LoginController.infoBox("Invalid quantity input",null,"Failed");
            else {
                editSoldStock(itemDTO.getSku_id(),itemDTO.getQuantity());
                //order id&uid&sku_id&amount&order_date&revenue&status
                status = "successful";
            }
            String orderQuery ="INSERT INTO sales(uid,sku_id,amount,order_date,revenue,status)" +
                    "VALUES('"+itemDTO.getUid()+"','"+itemDTO.getSku_id()+"','"+itemDTO.getQuantity()+
                    "','"+itemDTO.getOrder_date()+"','"+itemDTO.getRevenue()+"','"+status+"')";
            statement.executeUpdate(orderQuery);
            //LoginController.infoBox("Order Added",null,"Done");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("error happened when making an order");
        }
    }

    /**
     * item dataset retrieval for display
     * @return
     */
    //item dataset retrieval for display
    public ResultSet getItemQueryResult() {
        try {
            String query = "SELECT spu_name,sku_name,cate_name,color_code,size,price,description FROM item";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get Item table for display failed");
        }
        return resultSet;
    }

    //catalog item for display

    /**
     * catalog item for display
     * @return
     */
    public ResultSet getCatalogQueryResult() {
        try {
            String query = "SELECT spu_id,spu_name,sku_id,sku_name,cate_name,description,size,price,price*0.8 as discount_price,color_code " +
                    "FROM item order by rand() limit 6";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get Catalog Item table for display failed");
        }
        return resultSet;
    }


    //stock table for display

    /**
     * stock table for display
     * @return
     */
    public ResultSet getStockInfo(){
        try {
            String query = "SELECT spu_name,item.sku_name as sku_name,cate_name, quantity,price,description \n" +
                    " FROM itemstock INNER JOIN item ON itemstock.sku_id=item.sku_id";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get stock info failed");
        }
        return resultSet;
    }
    //order table for display

    /**
     * order table for display
     * @return
     */
    public ResultSet getOrderInfo() {
        try {
            String query = "select order_id,uid,sales.sku_id as sku_id, item.sku_name as sku_name,\n" +
                    "amount,order_date,revenue,status\n" +
                    "from sales join item on sales.sku_id = item.sku_id";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get order info failed");
        }
        return resultSet;
    }

    // Method to retrieve search data

    /**
     * Method to retrieve search data
     * @param text
     * @return
     */
    public ResultSet getItemSearch(String text) {
        try {
            String query = "SELECT spu_name,sku_name,cate_name,color_code,size,price,description " +
                    "FROM item " +
                    "WHERE spu_name LIKE '%"+text+"%' OR sku_name LIKE '%"+text+"%' OR " +
                    "cate_name LIKE '%"+text+"%' OR color_code LIKE '%"+text+"%' OR "+
                    "size like '%"+text+"%' or price like '%"+text+"%' or description like '%"+text+"%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get item search failed");
        }
        return resultSet;
    }

    public ResultSet getItemName(String sku_id) {
        try {
            String query = "SELECT sku_name FROM item " +
                    "WHERE sku_id='" +sku_id+ "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get item name by id failed");
        }
        return resultSet;
    }
    //get order search

    /**
     * get order search
     * @param text
     * @return
     */
    public ResultSet getOrderSearch(String text) {
        try {
            String query = "SELECT order_id,uid,sales.sku_id as sku_id,item.sku_name as sku_name, \n" +
                    "amount,order_date,revenue, status \n" +
                    "FROM sales INNER JOIN item  ON sales.sku_id=item.sku_id \n" +
                    "WHERE order_id LIKE '%"+text+"%' OR uid LIKE '%"+text+"%' " +
                    "OR sales.sku_id LIKE '%"+text+"%' OR item.sku_name LIKE '%"+text+"%'" +
                    "OR amount LIKE '%"+text+"%' OR order_date LIKE '%"+text+"%'" +
                    "OR revenue LIKE '%"+text+"%' OR status LIKE '%"+text+"%'" +
                    " ORDER BY order_id;";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get order search failed");
        }
        return resultSet;
    }
    public String getBuyerInfoText(int uid) throws SQLException{
        String user_name = null;
        String payment_info = null;
        String buyerInfoText = null;
        try {
            String query = "SELECT user_name,payment_info FROM buyer " +
                    "WHERE uid='" +uid+ "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                user_name = resultSet.getString("user_name");
                payment_info = resultSet.getString("payment_info");
                buyerInfoText = "user_name: "+user_name+" | payment_info: "+payment_info;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get Buyer name from order_id failed");
            throw e;
        }
        return buyerInfoText;
    }
    /*
    public String getBuyerName(int order_id) {
        String user_name = null;
        try {
            String query = "SELECT user_name FROM order " +
                    "INNER JOIN buyer ON buyer.uid=order.uid " +
                    "WHERE order_id='" +order_id+ "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                user_name = resultSet.getString("user_name");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get Buyer name from order_id failed");
        }
        return user_name;
    }
     */
    public Date getOrderDate(int order_id) {
        Date order_date = null;
        try {
            String query = "SELECT order_date FROM sales WHERE order_id='" +order_id+ "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                order_date = resultSet.getDate("order_date");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get Order date from order_id failed");
        }
        return order_date;
    }

    //for quarterly sales report
    public ResultSet getQuarterSales() {
        try {
            String query = "select category,\n" +
                    "MAX(case when q='Q1' then total_revenue end) as 'Q1',\n" +
                    "MAX(case when q='Q2' then total_revenue end) as 'Q2',\n" +
                    "MAX(case when q='Q3' then total_revenue end) as 'Q3',\n" +
                    "MAX(case when q='Q4' then total_revenue end) as 'Q4'\n" +
                    "from \n" +
                    "(\n" +
                    "select item.cate_name as category,\n" +
                    "case when order_date between '2021-01-01' and '2021-03-31' then 'Q1' \n" +
                    "\twhen order_date between '2021-04-01' and '2021-06-30' then 'Q2'\n" +
                    "\twhen order_date between '2021-07-01' and '2021-09-30' then 'Q3'\n" +
                    "\twhen order_date between '2021-10-01' and '2021-12-31' then 'Q4' end as q,\n" +
                    "sum(revenue) as total_revenue\n" +
                    "from sales join item on item.sku_id = sales.sku_id\n" +
                    "group by item.cate_name,q\n" +
                    ") as t\n" +
                    "group by category";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get quarter sales data for display failed");
        }
        return resultSet;
    }

    public ArrayList<Integer> getCateRatio(){
        ArrayList<Integer> sales = new ArrayList<>();
        try{
            String query = "select item.cate_name as category,sum(revenue) as total_revenue\n" +
                    "from sales join item on item.sku_id = sales.sku_id\n" +
                    "group by item.cate_name";
            resultSet = statement.executeQuery(query);
            int shirt_rev=0;
            int trousers_rev=0;
            int jacket_rev=0;
            String cate;
            while (resultSet.next()){
                cate = resultSet.getString("category");
                int rev = resultSet.getInt("total_revenue");
                switch (cate){
                    case "shirt":shirt_rev+=rev;break;
                    case "trousers":trousers_rev+=rev;break;
                    case "jacket":jacket_rev+=rev;break;
                }
            }
            sales.add(shirt_rev);
            sales.add(trousers_rev);
            sales.add(jacket_rev);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("get category sales ratio failed");
        }
        return sales;
    }

    public ArrayList<Integer> getGenderNum(){
        ArrayList<Integer> genderNum = new ArrayList<>();
        try{
            String query = "select distinct gender,count(*) as num\n" +
                    "from buyer\n" +
                    "group by gender";
            resultSet = statement.executeQuery(query);
            int male =  0;
            int female = 0;
            String gender;
            while (resultSet.next()){
                gender = resultSet.getString("gender");
                int num = resultSet.getInt("num");
                switch (gender){
                    case "Male": male+=num;break;
                    case "Female": female+=num;break;
                }
            }
            genderNum.add(male);
            genderNum.add(female);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("get gender ratio failed");
        }
        return genderNum;
    }

    public ArrayList<Integer> getAgeNum(){
        ArrayList<Integer> ageNum = new ArrayList<>();
        try{
            String query = "select distinct age,count(*) as num\n" +
                    "from buyer\n" +
                    "group by age";
            resultSet = statement.executeQuery(query);
            int num1=0;
            int num2=0;
            int num3=0;
            int num4=0;
            int num5=0;
            String age;
            while (resultSet.next()){
                age = resultSet.getString("age");
                int num = resultSet.getInt("num");
                switch (age){
                    case "<18": num1+=num;break;
                    case "[24,30)": num2+=num;break;
                    case "[18,24)": num3+=num;break;
                    case "[30,50)": num4+=num;break;
                    case ">=50": num5+=num;break;
                }
            }
            ageNum.add(num1);
            ageNum.add(num2);
            ageNum.add(num3);
            ageNum.add(num4);
            ageNum.add(num5);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("get age ratio failed");
        }
        return ageNum;
    }

    public ArrayList<Integer> getIncomeNum(){
        ArrayList<Integer> incomeNum = new ArrayList<>();
        try{
            String query = "select distinct income,count(*) as num\n" +
                    "from buyer\n" +
                    "group by income";
            resultSet = statement.executeQuery(query);
            int num1=0;
            int num2=0;
            int num3=0;
            int num4=0;
            String income;
            while (resultSet.next()){
                income = resultSet.getString("income");
                int num = resultSet.getInt("num");
                switch (income){
                    case "<1200": num1+=num;break;
                    case "[1200,4000)": num2+=num;break;
                    case "[4000,12000)": num3+=num;break;
                    case ">=12000": num4+=num;break;
                }
            }
            incomeNum.add(num1);
            incomeNum.add(num2);
            incomeNum.add(num3);
            incomeNum.add(num4);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("get age ratio failed");
        }
        return incomeNum;
    }

    public Map<String,Integer> getStateNum(){
//        ArrayList<Integer> stateNum = new ArrayList<>();
//        ArrayList<String> stateName = new ArrayList<>();
        Map<String,Integer> addr_map = new HashMap<>();
        try{
            String query = "select distinct address,count(*) as num\n" +
                    "from buyer\n" +
                    "group by address";
            resultSet = statement.executeQuery(query);

            String state;
            while (resultSet.next()){
                state = resultSet.getString("address");
                int num = resultSet.getInt("num");
                addr_map.put(state,num);
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("get age ratio failed");
        }
        return addr_map;
    }

    public ResultSet getMonthItemData(String start,String end){
        try {
            String query = "select category, sku_name,price,description,revenue\n" +
                    "from(\n" +
                    "select t.*,row_number() over (partition by category order by revenue desc) as row_num\n" +
                    "from\n" +
                    "(\n" +
                    "select item.cate_name as category,item.sku_name as sku_name,\n" +
                    "item.price as price,description,sum(revenue) as revenue\n" +
                    "from sales\n" +
                    "join item on sales.sku_id = item.sku_id\n" +
                    "where order_date between '"+start +"' and '"+end+ "'\n" +
                    "group by category,sku_name,price,description\n" +
                    ") as t\n" +
                    ") as t1\n" +
                    "where row_num<=5;";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get monthly best selling item failed");
        }
        return resultSet;
    }

    public ArrayList<Map> getItemBarData() {
        ArrayList<Map> result = new ArrayList<>();
        //Map<String,Integer> barData = new HashMap<>();
        try {
            String query = "select category, sku_name,price,description,revenue\n" +
                    "from(\n" +
                    "select t.*,row_number() over (partition by category order by revenue desc) as row_num\n" +
                    "from\n" +
                    "(\n" +
                    "select item.cate_name as category,item.sku_name as sku_name,\n" +
                    "item.price as price,description,sum(revenue) as revenue\n" +
                    "from sales\n" +
                    "join item on sales.sku_id = item.sku_id\n" +
                    //"where order_date between '" + start + "' and '" + end + "'\n" +
                    "group by category,sku_name,price,description\n" +
                    ") as t\n" +
                    ") as t1\n" +
                    "where row_num<=5;";
            resultSet = statement.executeQuery(query);
            String cate,sku;
            int num=0;
            Map<String,Integer> barData_shirt = new HashMap<>();
            Map<String,Integer> barData_trousers = new HashMap<>();
            Map<String,Integer> barData_jacket = new HashMap<>();
            while (resultSet.next()){
                cate = resultSet.getString("category");
                sku = resultSet.getString("sku_name");
                num = resultSet.getInt("revenue");
                //barData.put(sku,num);
                switch (cate){
                    case "shirt": barData_shirt.put(sku,num);break;
                    case "trousers": barData_trousers.put(sku,num);break;
                    case "jacket": barData_jacket.put(sku,num);break;
                }
            }
            result.add(barData_shirt);
            result.add(barData_trousers);
            result.add(barData_jacket);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get monthly best selling item failed");
        }
        return result;
    }
//objects for table view

    /**
     * getMonthItemObjects
     * @param resultSet
     * @return
     */
    public static ObservableList<MonthItem> getMonthItemObjects(ResultSet resultSet)  {
        ObservableList<MonthItem>  monthItemList = FXCollections.observableArrayList();
        try{
            while (resultSet.next()){
                MonthItem monthItem = new MonthItem();
                monthItem.setCategory(resultSet.getString("category"));
                monthItem.setSku_name(resultSet.getString("sku_name"));
                monthItem.setPrice(resultSet.getInt("price"));
                monthItem.setDescription(resultSet.getString("description"));
                monthItem.setRevenue(resultSet.getInt("revenue"));
                monthItemList.add(monthItem);
            }
        }catch(SQLException e){
            System.out.println("Error occurred while fetching data from DB "+e);
            e.printStackTrace();
        }
        return monthItemList;
    }

    /**
     * getQuarterSalesObjects
     * @param resultSet
     * @return
     */

    public static ObservableList<QuarterSales> getQuarterSalesObjects(ResultSet resultSet)  {
        ObservableList<QuarterSales>  quarterList = FXCollections.observableArrayList();
        try{
            while (resultSet.next()){
                QuarterSales quarterSales = new QuarterSales();
                quarterSales.setCategory(resultSet.getString("category"));
                quarterSales.setQ1(resultSet.getInt("Q1"));
                quarterSales.setQ2(resultSet.getInt("Q2"));
                quarterSales.setQ3(resultSet.getInt("Q3"));
                quarterSales.setQ4(resultSet.getInt("Q4"));
                quarterList.add(quarterSales);
            }
        }catch(SQLException e){
            System.out.println("Error occurred while fetching data from DB "+e);
            e.printStackTrace();
        }
        return quarterList;
    }

    // to get an observable list of objects in the search result for table view
    //item scene
    //getItemQueryResult

    /**
     * getItemObjects
     * @param resultSet
     * @return
     */
    public static ObservableList<ItemDTO> getItemObjects(ResultSet resultSet)  {
        ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();
        try{
            while (resultSet.next()){
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setSpu_name(resultSet.getString("spu_name"));
                itemDTO.setSku_name(resultSet.getString("sku_name"));
                itemDTO.setCate_name(resultSet.getString("cate_name"));
                itemDTO.setColor_code(resultSet.getString("color_code"));
                itemDTO.setSize(resultSet.getString("size"));
                itemDTO.setPrice(resultSet.getInt("price"));
                itemDTO.setDescription(resultSet.getString("description"));
                itemList.add(itemDTO);
            }
        }catch(SQLException e){
            System.out.println("Error occurred while fetching data from DB "+e);
            e.printStackTrace();
        }
        return itemList;
    }
    //getCatalogQueryResult

    /**getCatalogObjects
     *
     * @param resultSet
     * @return
     */
    public static ObservableList<ItemDTO> getCatalogObjects(ResultSet resultSet)  {
        ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();
        try{
            while (resultSet.next()){
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setSpu_id(resultSet.getString("spu_id"));
                itemDTO.setSpu_name(resultSet.getString("spu_name"));
                itemDTO.setSku_id(resultSet.getString("sku_id"));
                itemDTO.setSku_name(resultSet.getString("sku_name"));
                itemDTO.setCate_name(resultSet.getString("cate_name"));
                itemDTO.setDescription(resultSet.getString("description"));
                itemDTO.setSize(resultSet.getString("size"));
                itemDTO.setPrice(resultSet.getInt("price"));
                itemDTO.setDiscount_price(resultSet.getDouble("discount_price"));
                itemDTO.setColor_code(resultSet.getString("color_code"));

                itemList.add(itemDTO);
            }
        }catch(SQLException e){
            System.out.println("Error occurred while fetching data from DB "+e);
            e.printStackTrace();
        }
        return itemList;
    }


    // stock scene
    //public ResultSet getStockInfo()

    /**
     * getStockObjects
     * @param resultSet
     * @return
     */
    public static ObservableList<ItemDTO> getStockObjects(ResultSet resultSet)  {
        ObservableList<ItemDTO> stockList = FXCollections.observableArrayList();
        try{
            while (resultSet.next()){
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setSpu_name(resultSet.getString("spu_name"));
                itemDTO.setSku_name(resultSet.getString("sku_name"));
                itemDTO.setCate_name(resultSet.getString("cate_name"));
                itemDTO.setQuantity(resultSet.getInt("quantity"));
                itemDTO.setPrice(resultSet.getInt("price"));
                itemDTO.setDescription(resultSet.getString("description"));
                stockList.add(itemDTO);
            }
        }catch(SQLException e){
            System.out.println("Error occurred while fetching data from DB "+e);
            e.printStackTrace();
        }
        return stockList;
    }
    //order scene public ResultSet getOrderInfo()

    /**
     * getOrderObjects
     * @param resultSet
     * @return
     */
    public static ObservableList<ItemDTO> getOrderObjects(ResultSet resultSet)  {
        ObservableList<ItemDTO> orderList = FXCollections.observableArrayList();
        try{
            while (resultSet.next()){
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setOrder_id(resultSet.getInt("order_id"));
                itemDTO.setUid(resultSet.getInt("uid"));
                itemDTO.setSku_id(resultSet.getString("sku_id"));
                itemDTO.setSku_name(resultSet.getString("sku_name"));
                itemDTO.setQuantity(resultSet.getInt("amount"));
                itemDTO.setOrder_date(resultSet.getDate("order_date"));
                itemDTO.setRevenue(resultSet.getInt("revenue"));
                itemDTO.setStatus(resultSet.getString("status"));
                orderList.add(itemDTO);
            }
        }catch(SQLException e){
            System.out.println("Error occurred while fetching data from DB "+e);
            e.printStackTrace();
        }
        return orderList;
    }

}
