package com.ishika.onlineshopping.Model;

public class Wishlist {
    private String _id;
    private String email;
    private String product_name;
    private String product_price;
    private String product_category;
    private String product_rating;
    private String date_added;
    private String product_imagename;

    public Wishlist(String _id, String email, String product_name, String product_price, String product_category, String product_rating, String date_added, String product_imagename) {
        this._id = _id;
        this.email = email;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_category = product_category;
        this.product_rating = product_rating;
        this.date_added = date_added;
        this.product_imagename = product_imagename;
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

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(String product_rating) {
        this.product_rating = product_rating;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getProduct_imagename() {
        return product_imagename;
    }

    public void setProduct_imagename(String product_imagename) {
        this.product_imagename = product_imagename;
    }
}
