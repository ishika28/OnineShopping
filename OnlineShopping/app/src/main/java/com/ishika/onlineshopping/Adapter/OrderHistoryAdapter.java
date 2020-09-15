package com.ishika.onlineshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ishika.onlineshopping.Model.OrderHistory;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>{
    Context context;
    List<OrderHistory> orderHistoryList;

    public OrderHistoryAdapter(Context context, List<OrderHistory> orderHistoryList) {
        this.context = context;
        this.orderHistoryList = orderHistoryList;
    }

    @NonNull
    @Override
    public OrderHistoryAdapter.OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_order_history_view,parent,false);
        OrderHistoryViewHolder ohvh=new OrderHistoryViewHolder(view);
        return ohvh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.OrderHistoryViewHolder holder, int position) {

        final OrderHistory orderHistory=orderHistoryList.get(position);
        holder.tv_name.setText("Item: "+orderHistory.getProduct_name());

        int quantity = Integer.parseInt(orderHistory.getProduct_quantity());
        int total = Integer.parseInt(orderHistory.getProduct_price())*quantity;

        holder.tv_price.setText(total+"");
        holder.tv_qty.setText("Quantity: "+quantity);
        String[] date = orderHistory.getDate().split("T",0);
        holder.tv_date.setText("Order Date: "+date[0]);
        holder.tv_paymentType.setText(orderHistory.getPayment_type());
        String url= URL.BASE_URL+orderHistory.getProduct_imagename();
        Picasso.with(context).load(url).into(holder.img_product);

    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder{
        ImageView img_product;
        TextView tv_name;
        TextView tv_price;
        TextView tv_qty;
        TextView tv_date;
        TextView tv_paymentType;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            img_product=itemView.findViewById(R.id.sample_order_history_img);
            tv_name=itemView.findViewById(R.id.sample_order_history_name);
            tv_price=itemView.findViewById(R.id.sample_order_history_price);
            tv_qty=itemView.findViewById(R.id.sample_order_history_qty);
            tv_date=itemView.findViewById(R.id.sample_order_history_date);
            tv_paymentType = itemView.findViewById(R.id.sample_order_history_payment);

        }

    }
}
