package com.volkanunlu.ecommerceapp.view;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.adapters.AddressAdapter;
import com.volkanunlu.ecommerceapp.databinding.ActivityAddAddressBinding;
import com.volkanunlu.ecommerceapp.databinding.ActivityAddressBinding;
import com.volkanunlu.ecommerceapp.models.AddressModel;
import com.volkanunlu.ecommerceapp.models.AllProductModel;
import com.volkanunlu.ecommerceapp.models.NewProductsModel;
import com.volkanunlu.ecommerceapp.models.PopularProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress{


    ActivityAddressBinding binding;
    Button addAddressButton,payButton;
    RecyclerView recyclerView;
    List<AddressModel> addressModelList;
    AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Toolbar toolbar;
    String mAddress= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddressBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        toolbar=binding.addressToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //Detail activityden intent ile veri çekme

        Object object=getIntent().getSerializableExtra("item");




        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        toolbar=binding.addressToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        recyclerView=binding.addressRecycler;
        LinearLayoutManager lm=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        addressModelList=new ArrayList<>();

        firestore.collection("UserLocation").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){

                                AddressModel addressModel=documentSnapshot.toObject(AddressModel.class);
                                addressModelList.add(addressModel);
                                addressAdapter=new AddressAdapter(getApplicationContext(),addressModelList, AddressActivity.this);
                                recyclerView.setAdapter(addressAdapter);
                                System.out.println(addressModel);
                                addressAdapter.notifyDataSetChanged();

                            }
                        }
                        else{
                            Toast.makeText(AddressActivity.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        //ödeme butonu ve onclick intent geçişi
        payButton=binding.paymentBtn;

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double amount=0.0;

                if (object instanceof NewProductsModel){

                    NewProductsModel newProductsModel=(NewProductsModel) object;
                    amount= newProductsModel.getPrice();
                }
                if (object instanceof PopularProductModel){

                    PopularProductModel popularProductModel=(PopularProductModel) object;
                    amount= popularProductModel.getPrice();
                }
                if (object instanceof AllProductModel){

                    AllProductModel allProductModel=(AllProductModel) object;
                    amount= allProductModel.getPrice();
                }

                Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);

            }
        });

        //adres ekleme butonu ve onclick intent geçişi
        addAddressButton=binding.addAddressBtn;

        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(AddressActivity.this,AddAddressActivity.class);
                    startActivity(intent);
                }
            });
    }

    @Override
    public void setAddress(String address) {
        mAddress=address;

    }
}