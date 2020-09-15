package com.ishika.onlineshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ishika.onlineshopping.BL.WishlistBL;
import com.ishika.onlineshopping.Fragment.WishlistFragment;
import com.ishika.onlineshopping.Model.Wishlist;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {
    Context context;
    List<Wishlist> wishList;
    Fragment fragment;

    public WishlistAdapter(Context context, List<Wishlist> wishList, Fragment fragment) {
        this.context = context;
        this.wishList = wishList;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public WishlistAdapter.WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_wishlist_view,parent,false);
        WishlistAdapter.WishlistViewHolder wvh=new WishlistAdapter.WishlistViewHolder(view);
        return wvh;
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.WishlistViewHolder holder, int position) {
        final Wishlist wishlist=wishList.get(position);

        final String id=wishlist.get_id();
        holder.tv_name.setText(wishlist.getProduct_name());
        holder.tv_price.setText(wishlist.getProduct_price());
        holder.tv_category.setText(wishlist.getProduct_category());
        holder.tv_rating.setText(wishlist.getProduct_rating());
        holder.tv_date.setText(wishlist.getDate_added());

        String url= URL.BASE_URL+wishlist.getProduct_imagename();
        Picasso.with(context).load(url).into(holder.img_product);

        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistBL wishlistBL = new WishlistBL();

                if (wishlistBL.deleteFromWishlist(wishlist.get_id())) {
                    ((WishlistFragment) fragment).refreshFragment();
                    Toasty.success(context, "Product Removed from Wishlist ", Toasty.LENGTH_LONG).show();

                }else {
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class WishlistViewHolder extends RecyclerView.ViewHolder{
        ImageView img_product;
        TextView tv_name,tv_price,tv_category,tv_rating,tv_date;
        Button btn_remove;


        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);

            img_product=itemView.findViewById(R.id.sample_wishlist_img);
            tv_name=itemView.findViewById(R.id.sample_wishlist_name);
            tv_price=itemView.findViewById(R.id.sample_wishlist_price);
            tv_category=itemView.findViewById(R.id.sample_wishlist_category);
            tv_rating=itemView.findViewById(R.id.sample_wishlist_rating);
            tv_date=itemView.findViewById(R.id.sample_wishlist_date);

            btn_remove=itemView.findViewById(R.id.sample_wishlist_remove);
        }
    }
}
