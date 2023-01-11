package com.order.system.DTO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Think
 * class for displaying quarterly sales table
 */
public class QuarterSales {
    private StringProperty category;
    private IntegerProperty q1,q2,q3,q4;

    public QuarterSales(){
        this.category = new SimpleStringProperty();
        this.q1 = new SimpleIntegerProperty();
        this.q2 = new SimpleIntegerProperty();
        this.q3 = new SimpleIntegerProperty();
        this.q4 = new SimpleIntegerProperty();
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

    public int getQ1() {
        return q1.get();
    }

    public IntegerProperty q1Property() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1.set(q1);
    }

    public int getQ2() {
        return q2.get();
    }

    public IntegerProperty q2Property() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2.set(q2);
    }

    public int getQ3() {
        return q3.get();
    }

    public IntegerProperty q3Property() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3.set(q3);
    }

    public int getQ4() {
        return q4.get();
    }

    public IntegerProperty q4Property() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4.set(q4);
    }
}
