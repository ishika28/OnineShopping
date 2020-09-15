package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Shop;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ShopBL {
    Api api;
    public static List<Shop> shopList1;

    public List<Shop>  getShop(){
        api= URL.getInstance().create(Api.class);
        Call<List<Shop>> shopList=api.getShops();

        try {
            Response<List<Shop>> shopResponse = shopList.execute();
            System.out.println("Getting data");
            if (shopResponse.isSuccessful()) {
                shopList1 = shopResponse.body();

                return shopList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return shopList1;
    }
}
