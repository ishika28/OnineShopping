package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Wishlist;
import com.ishika.onlineshopping.Model.WishlistMessageModel;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class WishlistBL {
    private String email;
    private String product_name;
    private String product_price;
    private String product_category;
    private String product_rating;
    private String date_added;
    private String product_imagename;
    boolean isSuccess=false;
    boolean isDeleted=false;

    Api api;

    public static List<Wishlist> wishlist1;

    public WishlistBL(String email, String product_name, String product_price, String product_category, String product_rating, String date_added, String product_imagename) {
        this.email = email;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_category = product_category;
        this.product_rating = product_rating;
        this.date_added = date_added;
        this.product_imagename = product_imagename;
    }

    public WishlistBL(){}

    public boolean addToWishlist(){
        Api api= URL.getInstance().create(Api.class);
        Wishlist wishlist =new Wishlist("",email,product_name,product_price,product_category,product_rating,date_added,product_imagename);
        Call<WishlistMessageModel> wishlistModelCall=api.addToWishlist(wishlist);

        try {
            Response<WishlistMessageModel> wishlistResponse = wishlistModelCall.execute();
            if (wishlistResponse.body().getMessage_success()!= null) {
                isSuccess=true;
            }
            else{
                isSuccess=false;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<Wishlist> getWishlist(String wishlistEmail){
        api= URL.getInstance().create(Api.class);
        Call<List<Wishlist>> wishList=api.getWishlist(wishlistEmail);

        try {
            Response<List<Wishlist>> wishlistResponse = wishList.execute();
            System.out.println("Getting data");
            if (wishlistResponse.isSuccessful()) {
                wishlist1 = wishlistResponse.body();

                return wishlist1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return wishlist1;
    }

    public boolean deleteFromWishlist(String id) {
        api = URL.getInstance().create(Api.class);
        Call<Void> voidCall = api.deleteWishlistRow(id);
        try {
            Response<Void> deleteResponse = voidCall.execute();
            if (deleteResponse.isSuccessful()) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
