package com.ishika.onlineshopping.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.ishika.onlineshopping.R;

public class AboutUsActivity extends AppCompatActivity {
    TextView tv_aboutUs_text;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        actionBar=getSupportActionBar();
        actionBar.setTitle("About Us");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3d5afe")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_aboutUs_text=findViewById(R.id.tv_aboutUs_text);

        tv_aboutUs_text.setText("OnlineShopping is user-friendly android application for purchasing all types of products"+"\r\n"+"\r\n" +
                "Developer: Ishika Pradhan"+"\r\n"+"\r\n" +
                "This application has been developed to analyze customer satisfaction in online shopping with the development of appropriate shopping application using various technologies."+"\r\n"+"\r\n" +
                "Reftrofit 2 API and MongoDB required.");
    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }
}
