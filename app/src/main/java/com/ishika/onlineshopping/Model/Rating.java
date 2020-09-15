package com.ishika.onlineshopping.Model;

public class Rating {
    String email,product_name,_id,rating;

    public Rating(String pda_email, String product_name) {
        this.email=pda_email;
        this.product_name = product_name;
    }

    public Rating(String email, String product_name, String rating) {
        this.email = email;
        this.product_name = product_name;
        this.rating = rating;
    }

    public Rating(String _id, Float rating) {
        this._id = _id;
        this.rating = rating.toString();
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
