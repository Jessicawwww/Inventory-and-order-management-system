package com.order.system.DTO;
import javafx.beans.property.*;
import java.sql.Date;

/**
 * @author Think
 * order table view class property
 */
public class OrderDTO {
    private IntegerProperty order_id,uid,amount,revenue;
    private StringProperty sku_id,status;
    private ObjectProperty<Date> order_date; //java.sql.Date

    public OrderDTO(){
        this.order_id = new SimpleIntegerProperty();
        this.uid = new SimpleIntegerProperty();
        this.amount = new SimpleIntegerProperty();
        this.revenue = new SimpleIntegerProperty();

        this.sku_id = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.order_date = new SimpleObjectProperty<>();

    }

    public int getOrder_id() {
        return order_id.get();
    }

    public IntegerProperty order_idProperty() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id.set(order_id);
    }

    public int getUid() {
        return uid.get();
    }

    public IntegerProperty uidProperty() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid.set(uid);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getRevenue() {
        return revenue.get();
    }

    public IntegerProperty revenueProperty() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue.set(revenue);
    }

    public String getSku_id() {
        return sku_id.get();
    }

    public StringProperty sku_idProperty() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id.set(sku_id);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Date getOrder_date() {
        return order_date.get();
    }

    public ObjectProperty<Date> order_dateProperty() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date.set(order_date);
    }
}
