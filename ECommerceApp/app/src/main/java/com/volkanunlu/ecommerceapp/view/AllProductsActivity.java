package com.volkanunlu.ecommerceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.volkanunlu.ecommerceapp.adapters.AllProductAdapter;
import com.volkanunlu.ecommerceapp.databinding.ActivityAllProductsBinding;
import com.volkanunlu.ecommerceapp.models.AllProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllProductsActivity extends AppCompatActivity {

    ActivityAllProductsBinding binding;
    RecyclerView recyclerView;
    List<AllProductModel> allProductModelList;
    AllProductAdapter allProductAdapter;
    FirebaseFirestore firestore;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllProductsBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        toolbar=binding.allProductToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //intent ile gelen type değerini bir stringe alıp,kullanacağım.
        String type=getIntent().getStringExtra("type");

        firestore=FirebaseFirestore.getInstance();

        recyclerView=binding.showAllRec;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        allProductModelList=new ArrayList<>();



        //type değerine göre kategori olarak ürünleri gösterelim.bir if bloku içerisinde şarta bağlayalım.

        if (type==null || type.isEmpty()) {  //Eğer type boş ise tüm ürünleri gösterelim.
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    AllProductModel allProductModel = document.toObject(AllProductModel.class);
                                    allProductModelList.add(allProductModel);
                                    allProductAdapter = new AllProductAdapter(getApplicationContext(), allProductModelList);
                                    recyclerView.setAdapter(allProductAdapter);
                                    allProductAdapter.notifyDataSetChanged();

                                }
                            } else {

                                Toast.makeText(AllProductsActivity.this, "Error:" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

            if (type!=null && type.equalsIgnoreCase("baby")){  //type değeri baby ise bu kategoriye ait ürünleri listele

                firestore.collection("ShowAll").whereEqualTo("type","baby")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        AllProductModel allProductModel=document.toObject(AllProductModel.class);
                                        allProductModelList.add(allProductModel);
                                        allProductAdapter=new AllProductAdapter(getApplicationContext(),allProductModelList);
                                        recyclerView.setAdapter(allProductAdapter);
                                        allProductAdapter.notifyDataSetChanged();

                                    }
                                }
                                else{

                                    Toast.makeText(AllProductsActivity.this, "Error:"+ task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            if (type!=null && type.equalsIgnoreCase("men")){ //type değeri men ise bu kategoriye ait ürünleri listele

                firestore.collection("ShowAll").whereEqualTo("type","men")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        AllProductModel allProductModel=document.toObject(AllProductModel.class);
                                        allProductModelList.add(allProductModel);
                                        allProductAdapter=new AllProductAdapter(getApplicationContext(),allProductModelList);
                                        recyclerView.setAdapter(allProductAdapter);
                                        allProductAdapter.notifyDataSetChanged();

                                    }
                                }
                                else{

                                    Toast.makeText(AllProductsActivity.this, "Error:"+ task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            if (type!=null && type.equalsIgnoreCase("shoes")){ //type değeri shoes ise bu kategoriye ait ürünleri listele

                firestore.collection("ShowAll").whereEqualTo("type","shoes")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        AllProductModel allProductModel=document.toObject(AllProductModel.class);
                                        allProductModelList.add(allProductModel);
                                        allProductAdapter=new AllProductAdapter(getApplicationContext(),allProductModelList);
                                        recyclerView.setAdapter(allProductAdapter);
                                        allProductAdapter.notifyDataSetChanged();

                                    }
                                }
                                else{

                                    Toast.makeText(AllProductsActivity.this, "Error:"+ task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            if (type!=null && type.equalsIgnoreCase("camera")){ //type değeri camera ise bu kategoriye ait ürünleri listele

                firestore.collection("ShowAll").whereEqualTo("type","camera")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        AllProductModel allProductModel=document.toObject(AllProductModel.class);
                                        allProductModelList.add(allProductModel);
                                        allProductAdapter=new AllProductAdapter(getApplicationContext(),allProductModelList);
                                        recyclerView.setAdapter(allProductAdapter);
                                        allProductAdapter.notifyDataSetChanged();

                                    }
                                }
                                else{

                                    Toast.makeText(AllProductsActivity.this, "Error:"+ task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            if (type!=null && type.equalsIgnoreCase("woman")){  //type değeri woman ise bu kategoriye ait ürünleri listele

                firestore.collection("ShowAll").whereEqualTo("type","woman")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        AllProductModel allProductModel=document.toObject(AllProductModel.class);
                                        allProductModelList.add(allProductModel);
                                        allProductAdapter=new AllProductAdapter(getApplicationContext(),allProductModelList);
                                        recyclerView.setAdapter(allProductAdapter);
                                        allProductAdapter.notifyDataSetChanged();

                                    }
                                }
                                else{

                                    Toast.makeText(AllProductsActivity.this, "Error:"+ task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            if (type!=null && type.equalsIgnoreCase("computer")){ //type değeri computer ise bu kategoriye ait ürünleri listele

                firestore.collection("ShowAll").whereEqualTo("type","computer")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        AllProductModel allProductModel=document.toObject(AllProductModel.class);
                                        allProductModelList.add(allProductModel);
                                        allProductAdapter=new AllProductAdapter(getApplicationContext(),allProductModelList);
                                        recyclerView.setAdapter(allProductAdapter);
                                        allProductAdapter.notifyDataSetChanged();

                                    }
                                }
                                else{

                                    Toast.makeText(AllProductsActivity.this, "Error:"+ task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        if (type!=null && type.equalsIgnoreCase("watch")){ //type değeri watch ise bu kategoriye ait ürünleri listele

            firestore.collection("ShowAll").whereEqualTo("type","watch")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    AllProductModel allProductModel=document.toObject(AllProductModel.class);
                                    allProductModelList.add(allProductModel);
                                    allProductAdapter=new AllProductAdapter(getApplicationContext(),allProductModelList);
                                    recyclerView.setAdapter(allProductAdapter);
                                    allProductAdapter.notifyDataSetChanged();

                                }
                            }
                            else{

                                Toast.makeText(AllProductsActivity.this, "Error:"+ task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        }



    }
