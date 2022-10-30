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
import com.volkanunlu.ecommerceapp.models.NewProductsModel;
import com.volkanunlu.ecommerceapp.view.DetailsActivity;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.NewProductsHolder> {


    private Context context;
    private List<NewProductsModel> newProductsModelList;

    public NewProductsAdapter(Context context, List<NewProductsModel> newProductsModelList) {
        this.context = context;
        this.newProductsModelList = newProductsModelList;
    }



    public class NewProductsHolder extends RecyclerView.ViewHolder {

        ImageView newImg;
        TextView newName,newPrice;

        public NewProductsHolder(@NonNull View itemView) {  //xml ile kodu bağladık.
            super(itemView);
            newImg=itemView.findViewById(R.id.new_img);
            newName=itemView.findViewById(R.id.new_product_name);
            newPrice=itemView.findViewById(R.id.new_price);

        }
    }


    @NonNull
    @Override
    public NewProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewProductsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_products_area,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull NewProductsHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(newProductsModelList.get(position).getImg_url()).into(holder.newImg);
        holder.newName.setText(newProductsModelList.get(position).getName());
        holder.newPrice.setText(String.valueOf(newProductsModelList.get(position).getPrice()));

        //bir ürüne tıklandığında

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("details",newProductsModelList.get(position));  //diğer activitye intent ile bilgi gönderiyorum.
                context.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return newProductsModelList.size();
    }

}
