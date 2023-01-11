package com.order.system.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/** menu fxml controller
 * @author Think
 */
public class MenuController {
    @FXML
    private Button buyers;

    @FXML
    private Button catalog;

    @FXML
    private Button demoreport;

    @FXML
    private Button itemreport;

    @FXML
    private Button items;

    @FXML
    private Button orders;

    @FXML
    private Button qreport;

    @FXML
    private Button stock;

    @FXML
    private BorderPane mainPane;

    @FXML
    void buyerReportAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("BuyerReport");
        mainPane.setCenter(view);
    }

    @FXML
    void handleBuyerAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("BuyerScene");
        mainPane.setCenter(view);
    }

    @FXML
    void handleCatalogAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("CatalogScene");
        mainPane.setCenter(view);
    }

    @FXML
    void handleItemAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ItemScene");
        mainPane.setCenter(view);
    }

    @FXML
    void handleOrderAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("OrderScene");
        mainPane.setCenter(view);
    }

    @FXML
    void handleStockAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("StockScene");
        mainPane.setCenter(view);
    }

    @FXML
    void monthlyItemAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("MonthItems");
        mainPane.setCenter(view);
    }

    @FXML
    void qReportAction(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("QuarterSales");
        mainPane.setCenter(view);
    }


}
