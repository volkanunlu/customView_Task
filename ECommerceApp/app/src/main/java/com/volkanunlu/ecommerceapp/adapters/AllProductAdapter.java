package com.volkanunlu.ecommerceapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.models.AllProductModel;
import com.volkanunlu.ecommerceapp.view.AllProductsActivity;
import com.volkanunlu.ecommerceapp.view.DetailsActivity;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductHolder> {

    private Context context;
    private List<AllProductModel> allProductModelList;


    public AllProductAdapter(Context context, List<AllProductModel> allProductModelList) {
        this.context = context;
        this.allProductModelList = allProductModelList;
    }


    public class AllProductHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView cost;
        private TextView name;


        public AllProductHolder(@NonNull View itemView) {
            super(itemView);

            itemImage=itemView.findViewById(R.id.item_image);
            cost=itemView.findViewById(R.id.item_cost);
            name=itemView.findViewById(R.id.item_name);

        }
    }

    @NonNull
    @Override
    public AllProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllProductHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_product,parent,false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AllProductHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(allProductModelList.get(position).getImg_url()).into(holder.itemImage);
        holder.cost.setText("₺"+ allProductModelList.get(position).getPrice());
        holder.name.setText(allProductModelList.get(position).getName());

        //herhangi bir ürüne tıkladığımızda

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context.getApplicationContext(),DetailsActivity.class);
                intent.putExtra("details",allProductModelList.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allProductModelList.size();
    }


}
