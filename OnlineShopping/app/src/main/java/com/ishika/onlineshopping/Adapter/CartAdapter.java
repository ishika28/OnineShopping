package com.ishika.onlineshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ishika.onlineshopping.BL.CartBL;
import com.ishika.onlineshopping.Fragment.CartFragment;
import com.ishika.onlineshopping.Model.Cart;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    List<Cart> cartList;
    Fragment fragment;

    public CartAdapter(Context context, List<Cart> cartList, Fragment fragment) {
        this.context = context;
        this.cartList = cartList;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_cart_view,parent,false);
        CartAdapter.CartViewHolder cvh=new CartAdapter.CartViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        final Cart cart=cartList.get(position);

        final String id=cart.get_id();
        holder.tv_name.setText(cart.getProduct_name());
        holder.tv_price.setText(cart.getProduct_price());
        holder.et_quantity.setText(cart.getProduct_quantity());

        String url= URL.BASE_URL+cart.getProduct_imagename();
        Picasso.with(context).load(url).into(holder.img_product);

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(holder.et_quantity.getText().toString());

                if (qty>=10) {
                    Toasty.warning(context,"Maximum quantity reached", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    qty+=1;
                    holder.et_quantity.setText(qty+"");

                    int food_total_price = Integer.parseInt(cart.getProduct_price()) * qty;
                    holder.tv_price.setText("" + food_total_price);
                    cartList.get(position).setProduct_quantity(""+qty);
                    ((CartFragment)fragment).addTotal(Integer.parseInt(cart.getProduct_price()));

                }
            }
        });

        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(holder.et_quantity.getText().toString());

                if (qty<=1) {
                    Toasty.warning(context,"Minimum quantity reached",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    qty-=1;
                    holder.et_quantity.setText(qty+"");

                    int food_total_price = Integer.parseInt(cart.getProduct_price()) * qty;
                    cartList.get(position).setProduct_quantity(""+qty);
                    holder.tv_price.setText("" + food_total_price);
                    ((CartFragment)fragment).reduceTotal(Integer.parseInt(cart.getProduct_price()));
                }




            }
        });


        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartBL cartBL = new CartBL();

                if (cartBL.deleteFromCart(cart.get_id())) {
                    ((CartFragment) fragment).refreshFragment();
                    Toasty.success(context, "Product Removed from Cart", Toasty.LENGTH_LONG).show();

                }else {
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public List<Cart> getOrderList(){
        return cartList;
    }

    public int grandTotal(){
        int totalPrice = 0;
        for(int i = 0 ; i < cartList.size(); i++) {
            totalPrice += Integer.parseInt(cartList.get(i).getProduct_price());
        }
        return totalPrice;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView img_product;
        TextView tv_name;
        TextView tv_price;
        EditText et_quantity;
        Button btn_add,btn_minus,btn_remove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            img_product=itemView.findViewById(R.id.sample_cart_img);
            tv_name=itemView.findViewById(R.id.sample_cart_name);
            tv_price=itemView.findViewById(R.id.sample_cart_price);
            et_quantity=itemView.findViewById(R.id.sample_cart_quantity);
            btn_minus=itemView.findViewById(R.id.sample_cart_minus);
            btn_add=itemView.findViewById(R.id.sample_cart_add);
            btn_remove=itemView.findViewById(R.id.sample_cart_remove);

        }

    }
}
