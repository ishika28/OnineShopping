package com.ishika.onlineshopping.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ishika.onlineshopping.BL.CartBL;
import com.ishika.onlineshopping.BL.RatingBL;
import com.ishika.onlineshopping.BL.WishlistBL;
import com.ishika.onlineshopping.Model.Rating;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;


public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView pda_img;
    TextView pda_name,pda_price,pda_category,pda_manufacturer,pda_desc,pda_rating;
    RatingBar pda_userRating;
    Button btn_wishlist,btn_cart;
    SharedPreferences preferences;
    Bundle bundle;
    ActionBar actionBar;
    String pda_email,id,productName;
    RatingBL ratingBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Product Details");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3d5afe")));
        actionBar.setDisplayHomeAsUpEnabled(true);
        bundle = getIntent().getExtras();

        if (bundle != null) {
            productName=bundle.getString("productName");
        }
        init();

        if (bundle != null) {

            String url= URL.BASE_URL+"images/"+bundle.getString("productImageName2");
            Picasso.with(getApplicationContext()).load(url).into(pda_img);

            pda_name.setText(bundle.getString("productName"));
            pda_price.setText("Price: "+bundle.getString("productPrice"));
            pda_category.setText("Category: "+bundle.getString("productCategory"));
            pda_manufacturer.setText("Manufacturer: "+bundle.getString("productManufacturer"));
            pda_rating.setText(bundle.getString("productRating"));
            pda_desc.setText(bundle.getString("productDescription"));
        }

    }

    public void init(){
        ratingBL = new RatingBL();
        preferences = getApplicationContext().getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        pda_email = preferences.getString("email", "");

        pda_img=findViewById(R.id.pda_img);

        pda_name=findViewById(R.id.pda_name);
        pda_price=findViewById(R.id.pda_price);
        pda_category=findViewById(R.id.pda_category);
        pda_manufacturer=findViewById(R.id.pda_manufacturer);
        pda_desc=findViewById(R.id.pda_desc);
        pda_rating=findViewById(R.id.pda_productRating);

        pda_userRating=findViewById(R.id.pda_userRating);

        btn_wishlist=findViewById(R.id.btn_wishlist);
        btn_wishlist.setOnClickListener(this);

        btn_cart=findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(this);

        Rating rating1 = new Rating(pda_email,productName);
        if (ratingBL.getMyRating(rating1).size() !=0){
            String myRating = ratingBL.getMyRating(rating1).get(0).getRating();
            pda_userRating.setRating(Float.parseFloat(myRating));
            id = ratingBL.getMyRating(rating1).get(0).get_id();
        }
        pda_userRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float myRating=ratingBar.getRating();
                Toasty.info(getApplicationContext(),myRating+"/5.0",Toast.LENGTH_SHORT).show();
                Rating rating1 = new Rating(pda_email,productName,""+myRating);
                if (ratingBL.addRating(rating1)==null) {

                    ratingBL.updateRating(id,rating1);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_wishlist:
                addtoWishlist();
                break;

            case R.id.btn_cart:
                addToCart();
                break;


        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void addtoWishlist(){
        Bundle bundle = getIntent().getExtras();
        String pda_imagename = bundle.getString("productImageName2");
        String pda_productName1= bundle.getString("productName");
        String pda_productPrice2= bundle.getString("productPrice");
        String pda_productCategory= bundle.getString("productCategory");
        String pda_productRating= bundle.getString("productRating");
        Date date=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
        String strDate=formatter.format(date);

        final WishlistBL wishlistBL=new WishlistBL(pda_email,pda_productName1,pda_productPrice2,pda_productCategory,pda_productRating,strDate,pda_imagename);
        StrictMode();

        if (wishlistBL.addToWishlist()){
            Toasty.success(ProductDetailsActivity.this,"Added to Wishlist!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toasty.warning(ProductDetailsActivity.this,"Product already in Wishlist!",Toast.LENGTH_SHORT).show();

        }
    }

    private void addToCart(){
        Bundle bundle = getIntent().getExtras();
        String pda_imagename = bundle.getString("productImageName2");
        String pda_productName= bundle.getString("productName");
        String pda_productPrice= bundle.getString("productPrice");
        String pda_quantity=String.valueOf(1);

        final CartBL cartBL=new CartBL(pda_email,pda_productName,pda_productPrice,pda_quantity,pda_imagename);
        StrictMode();

        if (cartBL.addToCart()){
            Toasty.success(ProductDetailsActivity.this,"Added to Cart!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toasty.warning(ProductDetailsActivity.this,"Item already in Cart!",Toast.LENGTH_SHORT).show();

        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }
}
