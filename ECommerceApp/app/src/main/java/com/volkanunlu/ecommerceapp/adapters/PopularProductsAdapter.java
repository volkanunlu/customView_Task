package com.volkanunlu.ecommerceapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.models.PopularProductModel;
import com.volkanunlu.ecommerceapp.view.DetailsActivity;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.PopularHolder> {

    private Context context;
    private List<PopularProductModel> popularProductModelList;

    public PopularProductsAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }


    public class PopularHolder extends RecyclerView.ViewHolder {

        ImageView popImage;
        TextView popName,popPrice;


        public PopularHolder(@NonNull View itemView) {
            super(itemView);
            popImage=itemView.findViewById(R.id.all_img);
            popName=itemView.findViewById(R.id.all_product_name);
            popPrice=itemView.findViewById(R.id.all_price);

        }
    }


    @NonNull
    @Override
    public PopularHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PopularHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_product,parent,false));
        //yapıyı yarattık

    }

    @Override
    public void onBindViewHolder(@NonNull PopularHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.popImage);
        holder.popName.setText(popularProductModelList.get(position).getName());
        holder.popPrice.setText(String.valueOf(popularProductModelList.get(position).getPrice()));

        //Bağladık yapıyı

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("details",popularProductModelList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { //listede ne kadar eleman varsa o kadar dön dedik.
        return popularProductModelList.size();
    }


}
