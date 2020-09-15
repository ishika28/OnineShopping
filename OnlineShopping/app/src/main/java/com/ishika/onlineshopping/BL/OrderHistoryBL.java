package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.OrderHistory;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class OrderHistoryBL {
    Api api;
    public static List<OrderHistory> orderHistoryList1;

    public List<OrderHistory>  getOrderHistory(String userEmail){
        api= URL.getInstance().create(Api.class);
        Call<List<OrderHistory>> orderHistoryList=api.getOrderHistory(userEmail);

        try {
            Response<List<OrderHistory>> orderHistoryResponse = orderHistoryList.execute();
            System.out.println("Getting data");
            if (orderHistoryResponse.isSuccessful()) {
                orderHistoryList1 = orderHistoryResponse.body();

                return orderHistoryList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return orderHistoryList1;
    }

}
