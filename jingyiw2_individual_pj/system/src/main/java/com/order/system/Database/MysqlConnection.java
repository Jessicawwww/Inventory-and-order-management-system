package com.order.system.Database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

//class for connection to database and login
public class MysqlConnection {

    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/company_info";
    static String username;
    static String password;

    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public MysqlConnection(){
        username = "root";
        password = "Wjy19710929!";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            statement = conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//connection to mysql database
    public Connection getConn(){
        try{
            Class.forName(driver);
            conn =DriverManager.getConnection(url,username,password);
            System.out.println("Successfully connected to the database!");
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    //Login verification method
    public boolean checkLogin(String username, String password){
        String query = "SELECT * FROM users WHERE username='"
                + username
                + "' AND password='"
                + password
                + "' LIMIT 1";

        try {
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }



}
