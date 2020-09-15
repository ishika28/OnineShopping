package com.ishika.onlineshopping.Model;

public class Cart {
    private String _id;
    private String email;
    private String product_name;
    private String product_price;
    private String product_quantity;
    private String product_imagename;
    private String payment_type;

    public Cart(String _id, String email, String product_name, String product_price, String product_quantity, String product_imagename, String payment_type) {
        this._id = _id;
        this.email = email;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_imagename = product_imagename;
        this.payment_type = payment_type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
}
