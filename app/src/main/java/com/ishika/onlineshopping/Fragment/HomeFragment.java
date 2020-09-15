package com.ishika.onlineshopping.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ishika.onlineshopping.Adapter.CategoryAdapter;
import com.ishika.onlineshopping.Adapter.ShopAdapter;
import com.ishika.onlineshopping.Adapter.SliderAdapter;
import com.ishika.onlineshopping.BL.ProductBL;
import com.ishika.onlineshopping.BL.ShopBL;
import com.ishika.onlineshopping.Model.Category;
import com.ishika.onlineshopping.Model.Shop;
import com.ishika.onlineshopping.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView_shops,recyclerView_categories;
    List<Shop> shopList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_shops=view.findViewById(R.id.recyclerViewShops);
        recyclerView_categories=view.findViewById(R.id.recyclerViewCategories);

        createCategoryList();
        if(categoryList.size() !=0) {
            recyclerView_categories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView_categories.setAdapter(new CategoryAdapter(getContext(), categoryList, HomeFragment.this));
        }else {
            recyclerView_categories.setVisibility(View.GONE);
        }

        SliderView sliderView = view.findViewById(R.id.imageSlider);

        SliderAdapter adapter= new SliderAdapter(getContext());

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();



        getShops();
        recyclerView_shops.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView_shops.setAdapter(new ShopAdapter(getContext(),shopList));
        return view;
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void getShops(){
        ShopBL shopBL=new ShopBL();
        StrictMode();

        if(shopBL.getShop() != null){
            shopList = shopBL.getShop();
        }
        else {
            Toasty.error(getActivity(),"Error while displaying shops",Toasty.LENGTH_LONG).show();
        }
    }

    private void createCategoryList(){
        ProductBL productBL =  new ProductBL();
        StrictMode();
        categoryList = productBL.getCategory();
    }

    public void showByCategory(String category){
        Bundle bundle = new Bundle();
        bundle.putString("category",category);
        Fragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment,"TestFrag").commit();

    }


}
