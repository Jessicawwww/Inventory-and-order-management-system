package com.order.system.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * class for displaying multiple view on menu
 */
public class FxmlLoader {
    private Pane view;

    public Pane getPage(String fileName){
        try{
            URL fileUrl = LoginController.class.getResource("/com/order/system/"+fileName+".fxml");
            if (fileUrl==null){
                throw new java.io.FileNotFoundException("FXML file canot be found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No page "+fileName+" please check the FxmlLoader.");
        }
        return view;
    }
}
