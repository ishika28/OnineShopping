package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Cart;
import com.ishika.onlineshopping.Model.CartMessageModel;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CartBL {
    private String email;
    private String product_name;
    private String product_price;
    private String product_quantity;
    private String product_imagename;
    boolean isSuccess=false;
    boolean isDeleted=false;

    Api api;

    public static List<Cart> cartList1;

    public CartBL(String email, String product_name, String product_price, String product_quantity, String product_imagename) {
        this.email = email;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_imagename = product_imagename;
    }

    public CartBL(){}

    public boolean addToCart(){
        Api api= URL.getInstance().create(Api.class);
        Cart cart =new Cart("",email,product_name,product_price,product_quantity,product_imagename,"Cash");
        Call<CartMessageModel> cartModelCall=api.addToCart(cart);

        try {
            Response<CartMessageModel> cartresponse = cartModelCall.execute();
            if (cartresponse.body().getMessage_success()!= null) {
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

    public List<Cart> getCart(String cartEmail){
        api= URL.getInstance().create(Api.class);
        Call<List<Cart>> cartList=api.getCart(cartEmail);

        try {
            Response<List<Cart>> cartResponse = cartList.execute();
            System.out.println("Getting data");
            if (cartResponse.isSuccessful()) {
                cartList1 = cartResponse.body();

                return cartList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cartList1;
    }

    public boolean deleteFromCart(String id) {
        api = URL.getInstance().create(Api.class);
        Call<Void> voidCall = api.deleteCartRow(id);
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
