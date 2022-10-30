package com.volkanunlu.ecommerceapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.models.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    Context context;
    List<CartModel> cartModelList;
    int totalPay;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    public class CartHolder extends RecyclerView.ViewHolder {

        TextView name,date,price,time,totalPrice,totalQuantity;

        public CartHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.product_name);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            price=itemView.findViewById(R.id.product_price);
            totalPrice=itemView.findViewById(R.id.total_price);
            totalQuantity=itemView.findViewById(R.id.total_quantity);

        }

    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_items,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {

        holder.date.setText(cartModelList.get(position).getOrderDate());
        holder.time.setText(cartModelList.get(position).getOrderTime());
        holder.price.setText(cartModelList.get(position).getProductPrice()+"TL");
        holder.name.setText(cartModelList.get(position).getProductName());
        holder.totalQuantity.setText(String.valueOf(cartModelList.get(position).getTotalCount()));
        holder.totalPrice.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }


}
