package com.ishika.onlineshopping.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import com.ishika.onlineshopping.Adapter.OrderHistoryAdapter;
import com.ishika.onlineshopping.BL.OrderHistoryBL;
import com.ishika.onlineshopping.Model.OrderHistory;
import com.ishika.onlineshopping.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class OrderHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<OrderHistory> orderHistoryList = new ArrayList<>();
    public static OrderHistoryAdapter orderHistoryAdapter;
    SharedPreferences preferences;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Purchase History");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3d5afe")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView= findViewById(R.id.recyclerViewOrderHistory);
        getOrderHistory();
        orderHistoryAdapter= new OrderHistoryAdapter(OrderHistoryActivity.this,orderHistoryList);
        recyclerView.setAdapter(orderHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getOrderHistory(){
        preferences = OrderHistoryActivity.this.getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        String userEmail = preferences.getString("email", "");

        OrderHistoryBL orderHistoryBL=new OrderHistoryBL();
        StrictMode();

        if(orderHistoryBL.getOrderHistory(userEmail) != null){
            orderHistoryList = orderHistoryBL.getOrderHistory(userEmail);
        }
        else {
            Toasty.error(this,"Error while displaying order history", Toasty.LENGTH_LONG).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }
}
