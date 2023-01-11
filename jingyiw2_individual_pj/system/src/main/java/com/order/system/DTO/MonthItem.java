package com.order.system.DTO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * class for displaying monthly item table view
 * @author Think
 */
public class MonthItem {
    private StringProperty category,sku_name,description;
    private IntegerProperty price,revenue;

    public MonthItem(){
        this.category = new SimpleStringProperty();
        this.sku_name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.revenue = new SimpleIntegerProperty();
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
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

    public int getRevenue() {
        return revenue.get();
    }

    public IntegerProperty revenueProperty() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue.set(revenue);
    }
}
