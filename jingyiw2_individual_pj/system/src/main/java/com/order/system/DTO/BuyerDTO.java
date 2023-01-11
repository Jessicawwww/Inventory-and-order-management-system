package com.order.system.DTO;
import javafx.beans.property.*;

/**
 * Buyer property class
 */
public class BuyerDTO {
    private IntegerProperty uid;
    private StringProperty user_name,address,payment_info,phone, email;
    private StringProperty age,gender,income;
    public BuyerDTO(){
        this.uid = new SimpleIntegerProperty();
        this.user_name = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.payment_info = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.age = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.income = new SimpleStringProperty();

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

    public String getUser_name() {
        return user_name.get();
    }

    public StringProperty user_nameProperty() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name.set(user_name);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPayment_info() {
        return payment_info.get();
    }

    public StringProperty payment_infoProperty() {
        return payment_info;
    }

    public void setPayment_info(String payment_info) {
        this.payment_info.set(payment_info);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getIncome() {
        return income.get();
    }

    public StringProperty incomeProperty() {
        return income;
    }

    public void setIncome(String income) {
        this.income.set(income);
    }
}
