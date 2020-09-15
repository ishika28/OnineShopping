package com.ishika.onlineshopping.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ishika.onlineshopping.Adapter.ProductAdapter;
import com.ishika.onlineshopping.BL.ProductBL;
import com.ishika.onlineshopping.Model.Product;
import com.ishika.onlineshopping.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class ProductFragment extends Fragment {
    RecyclerView recyclerView;
    List<Product> productList = new ArrayList<>();
    public static ProductAdapter productAdapter;
    EditText searchProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product,container,false);

        recyclerView= view.findViewById(R.id.recyclerViewProducts);
        searchProduct = view.findViewById(R.id.searchProduct);
        getProducts();
        productAdapter = new ProductAdapter(getContext(),productList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        searchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return view;
    }

    private void filter(String text){
        List<Product> filterList = new ArrayList<>();

        for (Product product:productList){
            if (product.getProduct_name().toLowerCase().contains(text.toLowerCase())){
                filterList.add(product);
            }
        }
        productAdapter.filterList(filterList);
    }


    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getProducts(){
        ProductBL productBL=new ProductBL();
        StrictMode();

        if(productBL.getProduct() != null){
            productList = productBL.getProduct();
            System.out.print(productList);
        }
        else {

            Toasty.error(getActivity(),"Error while displaying products",Toasty.LENGTH_LONG).show();
        }
    }

}
