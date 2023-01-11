module com.order.system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.order.system to javafx.fxml;
    //exports com.order.system;
    exports com.order.system.UI;
    opens com.order.system.UI to javafx.fxml;
}