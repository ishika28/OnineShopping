package com.ishika.onlineshopping.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.ishika.onlineshopping.Adapter.ShopAdapter;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

public class ShopDetailsActivity extends AppCompatActivity {
    ImageView iv_shop;
    TextView tv_shopName,tv_shopDesc,tv_shopRating;
    public MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoicm9oYW5tYWhhcmphbjI3IiwiYSI6ImNrMXlzdWNlaDBxdG4zbXFvd2FkYmQwYXIifQ.6junG8I2vm--1DebTkX8tw");
        setContentView(R.layout.activity_shop_details);
        getSupportActionBar().hide();

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                float longtitude = Float.parseFloat(ShopAdapter.Log);
                float latitude = Float.parseFloat(ShopAdapter.Lat);
                MarkerOptions options = new MarkerOptions();
                options.title("Shop Location");
                options.position(new LatLng(latitude, longtitude));
                mapboxMap.addMarker(options);
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                    }

                });
            }
        });


        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        iv_shop = findViewById(R.id.shop_image);
        tv_shopName=findViewById(R.id.shop_name);
        tv_shopDesc=findViewById(R.id.shop_desc);
        tv_shopRating=findViewById(R.id.shop_rating);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String url= URL.BASE_URL+"images/"+bundle.getString("shopImageName2");
            String name= bundle.getString("shopName");
            String description = bundle.getString("shopDescription");
            String rating = bundle.getString("shopRating");
            Picasso.with(getApplicationContext()).load(url).into(iv_shop);

            tv_shopName.setText(name);
            tv_shopDesc.setText(description);
            tv_shopRating.setText(rating);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
