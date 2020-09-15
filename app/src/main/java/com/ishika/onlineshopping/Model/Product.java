package com.ishika.onlineshopping.Model;

public class Product {
    private String product_name,product_price,product_category,product_description,product_manufacturer,product_imagename,product_rating,product_offer;

    public Product(String product_name, String product_price, String product_category, String product_description, String product_manufacturer, String product_imagename, String product_rating, String product_offer) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_category = product_category;
        this.product_description = product_description;
        this.product_manufacturer = product_manufacturer;
        this.product_imagename = product_imagename;
        this.product_rating = product_rating;
        this.product_offer = product_offer;
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

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_manufacturer() {
        return product_manufacturer;
    }

    public void setProduct_manufacturer(String product_manufacturer) {
        this.product_manufacturer = product_manufacturer;
    }

    public String getProduct_imagename() {
        return product_imagename;
    }

    public void setProduct_imagename(String product_imagename) {
        this.product_imagename = product_imagename;
    }

    public String getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(String product_rating) {
        this.product_rating = product_rating;
    }

    public String getProduct_offer() {
        return product_offer;
    }

    public void setProduct_offer(String product_offer) {
        this.product_offer = product_offer;
    }
}
