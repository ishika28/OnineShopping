package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Cart;
import com.ishika.onlineshopping.Model.CartMessageModel;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class OrderBL {
    boolean isSuccess=false;
    public OrderBL(){}

    public boolean addOrder(Cart cart){
        Api api= URL.getInstance().create(Api.class);
        Call<CartMessageModel> cartModelCall=api.addOrder(cart);

        try {
            Response<CartMessageModel> cartresponse = cartModelCall.execute();
            if (cartresponse.body().getMessage()!= null) {
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
}
