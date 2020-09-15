package com.ishika.onlineshopping.Model;

public class Category {
    private String product_category,product_category_imagename;

    public Category(String product_category, String product_category_imagename) {
        this.product_category = product_category;
        this.product_category_imagename = product_category_imagename;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_category_imagename() {
        return product_category_imagename;
    }

    public void setProduct_category_imagename(String product_category_imagename) {
        this.product_category_imagename = product_category_imagename;
    }
}
