package com.ishika.onlineshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ishika.onlineshopping.Fragment.HomeFragment;
import com.ishika.onlineshopping.Model.Category;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    Context context;
    List<Category> categoryList;
    Fragment fragment;

    public CategoryAdapter(Context context, List<Category> categoryList, Fragment fragment) {
        this.context = context;
        this.categoryList = categoryList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_category_view,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Category category = categoryList.get(position);

        holder.category_name.setText(category.getProduct_category());
        Picasso.with(context).load(URL.BASE_URL+category.getProduct_category_imagename()).into(holder.category_img);

        holder.category_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(context,category.getProduct_category(), Toast.LENGTH_SHORT).show();
                ((HomeFragment)fragment).showByCategory(category.getProduct_category());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView category_img;
        TextView category_name;
        LinearLayout category_layout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            category_img = itemView.findViewById(R.id.category_img);
            category_name = itemView.findViewById(R.id.category_name);
            category_layout = itemView.findViewById(R.id.category_layout);
        }
    }
}
