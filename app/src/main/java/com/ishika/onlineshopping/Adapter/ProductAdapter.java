package com.ishika.onlineshopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ishika.onlineshopping.Activity.ProductDetailsActivity;
import com.ishika.onlineshopping.BL.ProductBL;
import com.ishika.onlineshopping.Model.Product;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_product_view,parent,false);
        ProductViewHolder holder=new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductBL productBL = new ProductBL();
        final Product product=productList.get(position);
        holder.tv_name.setText(product.getProduct_name());
        holder.tv_price.setText(product.getProduct_price());
        holder.tv_totalRating.setText(productBL.getTotalRating(product.getProduct_name()));

        String url= URL.BASE_URL+product.getProduct_imagename();
        Picasso.with(context).load(url).into(holder.img_product);

        holder.cv_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path= URL.BASE_URL+product.getProduct_imagename();

                Intent intent=new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productName",product.getProduct_name());
                intent.putExtra("productPrice",product.getProduct_price());
                intent.putExtra("productManufacturer",product.getProduct_manufacturer());
                intent.putExtra("productCategory",product.getProduct_category());
                intent.putExtra("productImageName",path);
                intent.putExtra("productImageName2",product.getProduct_imagename());
                intent.putExtra("productDescription",product.getProduct_description());
                intent.putExtra("productRating",product.getProduct_rating());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView img_product;
        TextView tv_name;
        TextView tv_price;
        TextView tv_totalRating;
        CardView cv_product;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            img_product=itemView.findViewById(R.id.sample_productImage);
            tv_name=itemView.findViewById(R.id.sample_productName);
            tv_price=itemView.findViewById(R.id.sample_productPrice);
            tv_totalRating=itemView.findViewById(R.id.sample_productRating);
            cv_product=itemView.findViewById(R.id.product_card);

        }

    }

    public void filterList(List<Product> productList1){
        productList = productList1;
        notifyDataSetChanged();

    }
}
