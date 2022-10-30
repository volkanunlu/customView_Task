package com.volkanunlu.ecommerceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.volkanunlu.ecommerceapp.adapters.CartAdapter;
import com.volkanunlu.ecommerceapp.databinding.ActivityCartBinding;
import com.volkanunlu.ecommerceapp.models.CartModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<CartModel> cartModelList;
    CartAdapter cartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        //firestore ve auth instance işlemleri
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        //toolbar düzenlemeleri
        toolbar=binding.myCartToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //favori ürünlerimi firebaseden çektiğim recyclerview üzerinde gösterdiğim yapım.
        recyclerView=binding.cartRec;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList=new ArrayList<>();

        firestore.collection("AddCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){

                                CartModel cartModel=documentSnapshot.toObject(CartModel.class);
                                cartModelList.add(cartModel);
                                cartAdapter=new CartAdapter(getApplicationContext(),cartModelList);
                                recyclerView.setAdapter(cartAdapter);
                                cartAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });
    }
}