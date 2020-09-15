package com.ishika.onlineshopping.Model;

public class Shop {
    private String shop_name,shop_imagename,shop_latitude,shop_longitude,shop_description,shop_rating;

    public Shop(String shop_name, String shop_imagename, String shop_latitude, String shop_longitude, String shop_description, String shop_rating) {
        this.shop_name = shop_name;
        this.shop_imagename = shop_imagename;
        this.shop_latitude = shop_latitude;
        this.shop_longitude = shop_longitude;
        this.shop_description = shop_description;
        this.shop_rating = shop_rating;
    }


    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_imagename() {
        return shop_imagename;
    }

    public void setShop_imagename(String shop_imagename) {
        this.shop_imagename = shop_imagename;
    }

    public String getShop_latitude() {
        return shop_latitude;
    }

    public void setShop_latitude(String shop_latitude) {
        this.shop_latitude = shop_latitude;
    }

    public String getShop_longitude() {
        return shop_longitude;
    }

    public void setShop_longitude(String shop_longitude) {
        this.shop_longitude = shop_longitude;
    }

    public String getShop_description() {
        return shop_description;
    }

    public void setShop_description(String shop_description) {
        this.shop_description = shop_description;
    }

    public String getShop_rating() {
        return shop_rating;
    }

    public void setShop_rating(String shop_rating) {
        this.shop_rating = shop_rating;
    }
}
