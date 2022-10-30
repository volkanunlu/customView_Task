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
import com.volkanunlu.ecommerceapp.models.CategoryModel;
import com.volkanunlu.ecommerceapp.view.AllProductsActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private Context context;
    private List<CategoryModel> categoryList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryList) { //constructor yapımızı dahil ettik.
        this.context = context;
        this.categoryList = categoryList;
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView catName;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            catImg=itemView.findViewById(R.id.category_img);
            catName=itemView.findViewById(R.id.category_name);

        }
    }


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CategoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_area,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(categoryList.get(position).getImg_url()).into(holder.catImg);
        holder.catName.setText(categoryList.get(position).getName());

        //bir kategoriye tıklandığında neler olsun, bir listener ekleyelim.

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, AllProductsActivity.class);
                intent.putExtra("type",categoryList.get(position).getType());
                context.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {  //geriye ne kadar değer döndürsün, liste boyutum kadar.

        return categoryList.size();
    }






}
