package com.ishika.onlineshopping.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ishika.onlineshopping.Fragment.CartFragment;
import com.ishika.onlineshopping.Fragment.HomeFragment;
import com.ishika.onlineshopping.Fragment.WishlistFragment;
import com.ishika.onlineshopping.Fragment.ProductFragment;
import com.ishika.onlineshopping.Fragment.ProfileFragment;
import com.ishika.onlineshopping.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        BottomNavigationView bottomNav=findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.action_home);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,new HomeFragment(),"Dashboard").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;

                    switch (menuItem.getItemId()){
                        case R.id.action_product:
                            selectedFragment=new ProductFragment();
                            break;

                        case R.id.action_wishlist:
                            selectedFragment=new WishlistFragment();
                            break;

                        case R.id.action_home:
                            selectedFragment=new HomeFragment();
                            break;

                        case R.id.action_cart:
                            selectedFragment=new CartFragment();
                            break;

                        case R.id.action_profile:
                            selectedFragment=new ProfileFragment();
                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}

