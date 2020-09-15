package com.ishika.onlineshopping.Fragment;


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

import com.ishika.onlineshopping.Adapter.WishlistAdapter;
import com.ishika.onlineshopping.BL.WishlistBL;
import com.ishika.onlineshopping.Model.Wishlist;
import com.ishika.onlineshopping.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class WishlistFragment extends Fragment {
    RecyclerView recyclerView;
    WishlistAdapter wishlistAdapter;
    List<Wishlist> wishList = new ArrayList<>();
    SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_wishlist,container,false);
        recyclerView= view.findViewById(R.id.recyclerViewWishlist);
        getWishlist();
        wishlistAdapter = new WishlistAdapter(getContext(),wishList,WishlistFragment.this);
        recyclerView.setAdapter(wishlistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    public void getWishlist(){

        preferences = getActivity().getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        String wishlistEmail = preferences.getString("email", "");

        WishlistBL wishlistBL=new WishlistBL();
        StrictMode();

        if( wishlistBL.getWishlist(wishlistEmail) != null) {
            wishList = wishlistBL.getWishlist(wishlistEmail);
        }
        else {
            Toasty.error(getContext(),"Error while displaying wishlist",Toasty.LENGTH_LONG).show();
        }
    }

    public void refreshFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new WishlistFragment()).commitAllowingStateLoss();
    }
}
