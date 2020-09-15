package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Category;
import com.ishika.onlineshopping.Model.Product;
import com.ishika.onlineshopping.Model.Rating;
import com.ishika.onlineshopping.Model.TotalRatingModel;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductBL {
    Api api;
    public static List<Product> productList1;
    List<Rating> ratingList;


    public List<Product>  getProduct(){
        api= URL.getInstance().create(Api.class);
        Call<List<Product>> productList=api.getProduct();

        try {
            Response<List<Product>> productResponse = productList.execute();
            if (productResponse.isSuccessful()) {
                productList1 = productResponse.body();

                return productList1;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return productList1;
    }

    public String getTotalRating(String productName){
        api= URL.getInstance().create(Api.class);
        Call<TotalRatingModel> productList=api.getTotalRating(productName);

        try {
            Response<TotalRatingModel> productResponse = productList.execute();
            if (productResponse.isSuccessful()) {
                ratingList = productResponse.body().getRatings();
                int count = productResponse.body().getCount();
                float total = 0.0f;
                for (Rating rating:ratingList) {
                    total += Float.parseFloat(rating.getRating());
                }
                float rating = total/count;
                return ""+rating;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "0.0";
    }

    public List<Product> getProductByCategory(String category){
        api= URL.getInstance().create(Api.class);
        Call<List<Product>> productList=api.getProductByCategory(category);

        try {
            Response<List<Product>> productResponse = productList.execute();
            System.out.println("Getting data");
            if (productResponse.isSuccessful()) {
                productList1 = productResponse.body();

                return productList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return productList1;
    }

    public List<Category> getCategory(){
        api= URL.getInstance().create(Api.class);
        List<Category> categoryList1 = new ArrayList<>();
        Call<List<Category>> categoryList=api.getCategories();

        try {
            Response<List<Category>> categoryResponse = categoryList.execute();
            System.out.println("Getting data");
            if (categoryResponse.isSuccessful()) {
                categoryList1 = categoryResponse.body();

                return categoryList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return categoryList1;
    }



}
