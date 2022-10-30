package com.volkanunlu.ecommerceapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.volkanunlu.ecommerceapp.databinding.ActivityPaymentBinding;

import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    Toolbar toolbar;
    TextView subTotal,discount,shipping,total;
    Button payButton;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPaymentBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        //Toolbar düzenlemeleri

        toolbar=binding.paymentToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //TextView düzenlemeleri

        subTotal=binding.subTotal;
        discount=binding.discount;
        shipping=binding.shipping;
        total=binding.totalAmt;
        payButton=binding.payBtn;

        double amount=0.0;
        amount=getIntent().getDoubleExtra("amount",0.0);

        //Ödeme ekranında yer alan view düzenlemelerim.
        subTotal.setText(amount+"₺");
        discount.setText("%10");
        shipping.setText("20₺");
        total.setText((amount+20)-(amount/100*10)+"₺");

        //buton onclick ile birlikte bir alertdialog oluşturup final aktivitesine yönlendirme sağlanacak.
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(PaymentActivity.this);
                alertDialog.setTitle("Ödeme Ekranı Bildirimi");
                alertDialog.setMessage("Bakiyenizden düşülecektir.Onaylıyor musunuz?");
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(PaymentActivity.this,FinalActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();

            }
        });

    }
}