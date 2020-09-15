package com.ishika.onlineshopping.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ishika.onlineshopping.Adapter.CartAdapter;
import com.ishika.onlineshopping.BL.CartBL;
import com.ishika.onlineshopping.BL.OrderBL;
import com.ishika.onlineshopping.Model.Cart;
import com.ishika.onlineshopping.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import khalti.checkOut.api.Config;
import khalti.checkOut.api.OnCheckOutListener;
import khalti.widget.KhaltiButton;


public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    public static CartAdapter cartAdapter;
    OrderBL orderBL;
    public static long amount=0L;
    CartBL cartBL;
    SharedPreferences preferences;
    Button btn_checkout;
    TextView fa_tv_total;
    List<Cart> cartList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart,container,false);
        recyclerView= view.findViewById(R.id.recyclerViewCart);

        fa_tv_total=view.findViewById(R.id.fa_tv_total);
        getCart();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartAdapter = new CartAdapter(getContext(),cartList,CartFragment.this);
        recyclerView.setAdapter(cartAdapter);
        orderBL = new OrderBL();
        cartBL = new CartBL();
        fa_tv_total.setText(""+cartAdapter.grandTotal());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_checkout=view.findViewById(R.id.cart_btn_checkout);
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.payment_layout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fa_tv_total.getText().toString().equals("0")){
                    Toasty.error(getContext(),"Add items to cart first",Toast.LENGTH_SHORT).show();
                    return;
                }
                amount=Long.parseLong(fa_tv_total.getText().toString())*100;
                final List<Cart> orderList=cartAdapter.getOrderList();

                Button btnCashAtDelivery = dialog.findViewById(R.id.btnCashAtDelivery);
                final KhaltiButton btnPayWithKhalti = dialog.findViewById(R.id.btnPayWithKhalti);
                TextView txtClose = dialog.findViewById(R.id.close);

                btnPayWithKhalti.setText("Pay with Khalti");
                btnCashAtDelivery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (Cart cart : orderList) {
                            cart.setPayment_type("Cash");
                            if (orderBL.addOrder(cart)) {
                                cartBL.deleteFromCart(cart.get_id());
                            }
                        }
                        refreshFragment();
                        amount = 0L;
                        Toasty.success(getContext(), "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


                final Config config = new Config("test_public_key_d52cc67036aa4724aed1068e729ce80e", "TechCenter", "Order Item", "", amount, new OnCheckOutListener() {
                    @Override
                    public void onSuccess(HashMap<String, Object> data) {
                        Toasty.success(getContext(),"Order Placed Successfully",Toast.LENGTH_SHORT).show();
                        afterSuceess(orderList);
                    }

                    @Override
                    public void onError(String action, String message) {
                        Toasty.error(getContext(), "Failed to make payment", Toast.LENGTH_SHORT).show();
                    }
                });


                btnPayWithKhalti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnPayWithKhalti.showCheckOut(config);
                        dialog.dismiss();

                    }
                });

                txtClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        return view;
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getCart(){

        preferences = getContext().getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        String cartEmail = preferences.getString("email", "");

        CartBL cartBL=new CartBL();
        StrictMode();

        if(cartBL.getCart(cartEmail) != null) {
            cartList = cartBL.getCart(cartEmail);
        }
        else {
            Toasty.error(getActivity(),"Error while displaying cart",Toasty.LENGTH_LONG).show();
        }

    }

    public void addTotal(int total){
        total+=Integer.parseInt(fa_tv_total.getText().toString());
        fa_tv_total.setText(""+total);
    }

    private void afterSuceess(List<Cart> orderList){
        for (Cart cart : orderList) {
            cart.setPayment_type("Khalti");
            if (orderBL.addOrder(cart)) {
                cartBL.deleteFromCart(cart.get_id());
            }
        }
        refreshFragment();
    }

    public void refreshFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CartFragment()).commitAllowingStateLoss();
    }

    public void reduceTotal(int total){
        int old=Integer.parseInt(fa_tv_total.getText().toString());
        old-=total;
        fa_tv_total.setText(""+old);
    }





}