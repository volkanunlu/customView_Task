package com.volkanunlu.ecommerceapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.volkanunlu.ecommerceapp.databinding.ActivityFinalBinding;

import java.util.Objects;

public class FinalActivity extends AppCompatActivity {

    ActivityFinalBinding binding;
    Button goShopButton;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFinalBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

    toolbar=binding.finalToolbar;

    toolbar.setTitleTextColor(Color.WHITE);
    setSupportActionBar(toolbar);
    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {  //toolbar nav. yönlendirme ayarı
        @Override
            public void onClick(View view) {
                finish();
            }
        });

    goShopButton=binding.goShopButton;

    //Buton alışveriş sonu beni ana ekrana götürecek ve kullanıcı alışveriş yapmak isterse bu kısımdan devam edecek.

    goShopButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent=new Intent(FinalActivity.this,MainActivity.class);
            startActivity(intent);
        }
    });




    }
}