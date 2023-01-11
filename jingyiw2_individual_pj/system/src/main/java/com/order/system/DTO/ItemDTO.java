package com.order.system.DTO;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.sql.Date;

/**
 * item class for receiving value from textfields
 */
public class ItemDTO {
    private StringProperty spu_id,spu_name, sku_id, sku_name, cate_id, cate_name, color_code, size,description;
    private IntegerProperty price,quantity;

    private IntegerProperty uid,revenue,order_id;
    //private StringProperty status;
    private ObjectProperty<Date> order_date; //java.sql.Date
    private StringProperty status;
    private DoubleProperty discount_price;

    public ItemDTO() {
        this.spu_id = new SimpleStringProperty();
        this.spu_name = new SimpleStringProperty();
        this.sku_id = new SimpleStringProperty();
        this.sku_name = new SimpleStringProperty();
        this.cate_id = new SimpleStringProperty();
        this.cate_name = new SimpleStringProperty();
        this.color_code = new SimpleStringProperty();
        this.size = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.quantity = new SimpleIntegerProperty();
        this.uid = new SimpleIntegerProperty();
        this.revenue = new SimpleIntegerProperty();
        this.order_date =  new SimpleObjectProperty<>();
        this.order_id = new SimpleIntegerProperty();
        this.status = new SimpleStringProperty();
        this.discount_price = new SimpleDoubleProperty();
    }

    public double getDiscount_price() {
        return discount_price.get();
    }

    public DoubleProperty discount_priceProperty() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price.set(discount_price);
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

    public int getRevenue() {
        return revenue.get();
    }

    public IntegerProperty revenueProperty() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue.set(revenue);
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

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getSpu_id() {
        return spu_id.get();
    }

    public StringProperty spu_idProperty() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id.set(spu_id);
    }

    public String getSpu_name() {
        return spu_name.get();
    }

    public StringProperty spu_nameProperty() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name.set(spu_name);
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

    public String getSku_name() {
        return sku_name.get();
    }

    public StringProperty sku_nameProperty() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name.set(sku_name);
    }

    public String getCate_id() {
        return cate_id.get();
    }

    public StringProperty cate_idProperty() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id.set(cate_id);
    }

    public String getCate_name() {
        return cate_name.get();
    }

    public StringProperty cate_nameProperty() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name.set(cate_name);
    }

    public String getColor_code() {
        return color_code.get();
    }

    public StringProperty color_codeProperty() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code.set(color_code);
    }

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }
}
