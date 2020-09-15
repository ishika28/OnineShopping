package com.ishika.onlineshopping.Model;

public class OrderHistory {
    private String email, product_name, product_price, product_quantity, product_imagename, date, payment_type;

    public OrderHistory(String email, String product_name, String product_price, String product_quantity, String product_imagename, String date, String payment_type) {
        this.email = email;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_imagename = product_imagename;
        this.date = date;
        this.payment_type = payment_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_imagename() {
        return product_imagename;
    }

    public void setProduct_imagename(String product_imagename) {
        this.product_imagename = product_imagename;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
}
