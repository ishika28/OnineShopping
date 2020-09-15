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
import android.widget.TextView;
import android.widget.Toast;

import com.ishika.onlineshopping.Adapter.ProductAdapter;
import com.ishika.onlineshopping.BL.ProductBL;
import com.ishika.onlineshopping.Model.Product;
import com.ishika.onlineshopping.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CategoryFragment extends Fragment {
    RecyclerView recyclerViewCategory;
    TextView categoryTitle;
    TextView categorySearch;
    List<Product> productList = new ArrayList<>();
    ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category,container,false);
        recyclerViewCategory= view.findViewById(R.id.recyclerViewCategory);
        categoryTitle = view.findViewById(R.id.categoryTitle);
        categorySearch = view.findViewById(R.id.categorySearch);
        String category="";
        Bundle bundle = this.getArguments();
        if (bundle != null){
            category=bundle.getString("category");
            getProductByCategory(category);
        }
        if (productList.size() == 0){
            Toasty.warning(getContext(),"No products found", Toast.LENGTH_SHORT).show();
        }
        productAdapter = new ProductAdapter(getContext(),productList);
        recyclerViewCategory.setAdapter(productAdapter);
        recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),2));
        categorySearch.addTextChangedListener(new TextWatcher() {
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

    public void getProductByCategory(String category){
        ProductBL productBL=new ProductBL();
        StrictMode();

        if(productBL.getProduct() != null){
            productList = productBL.getProductByCategory(category);
        }
        else {
            Toast.makeText(getActivity(),"Error while displaying products",Toast.LENGTH_LONG).show();
        }
    }
}
