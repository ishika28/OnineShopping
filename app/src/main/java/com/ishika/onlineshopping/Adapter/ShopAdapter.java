package com.ishika.onlineshopping.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ishika.onlineshopping.Activity.ShopDetailsActivity;
import com.ishika.onlineshopping.Model.Shop;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    Context context;
    List<Shop> shopList;

    public ShopAdapter(Context context, List<Shop> shopList) {
        this.context = context;
        this.shopList = shopList;
    }

    public static  String Lat;
    public static  String Log;

    @NonNull
    @Override
    public ShopAdapter.ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_shop_view,parent,false);
        ShopViewHolder holder = new ShopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ShopViewHolder holder, int position) {
        final Shop shop = shopList.get(position);

        String url= URL.BASE_URL+"images/"+shop.getShop_imagename();
        Picasso.with(context).load(url).into(holder.img_shop);

        holder.tv_shop_name.setText(shop.getShop_name());
        holder.tv_rating.setText(shop.getShop_rating());

        holder.cardShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShopDetailsActivity.class);

                String path= URL.BASE_URL+shop.getShop_imagename();

                intent.putExtra("shopName",shop.getShop_name());
                intent.putExtra("Longitude",shop.getShop_longitude());
                intent.putExtra("Latitude",shop.getShop_latitude());
                intent.putExtra("shopDescription",shop.getShop_description());
                intent.putExtra("shopImageName",path);
                intent.putExtra("shopImageName2",shop.getShop_imagename());
                intent.putExtra("shopRating",shop.getShop_rating());
                ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.img_shop, ViewCompat.getTransitionName(holder.img_shop));
                Log=shop.getShop_longitude();
                Lat=shop.getShop_latitude();
                context.startActivity(intent,options.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        ImageView img_shop;
        TextView tv_shop_name,tv_rating;
        RelativeLayout shop_layout;
        CardView cardShop;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            img_shop = itemView.findViewById(R.id.sample_shop_img);
            tv_shop_name = itemView.findViewById(R.id.sample_shop_name);
            tv_rating=itemView.findViewById(R.id.sample_shop_rating);
            shop_layout=itemView.findViewById(R.id.shop_layout);
            cardShop=itemView.findViewById(R.id.cardShop);
        }
    }
}
