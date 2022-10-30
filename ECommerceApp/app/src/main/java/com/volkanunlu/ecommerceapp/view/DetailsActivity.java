package com.volkanunlu.ecommerceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.databinding.ActivityDetailsBinding;
import com.volkanunlu.ecommerceapp.models.AllProductModel;
import com.volkanunlu.ecommerceapp.models.NewProductsModel;
import com.volkanunlu.ecommerceapp.models.PopularProductModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    ImageView detailImg;
    TextView rating,name,description,price,quantity;
    Button addCart,buyCart;
    ImageView addItem,removeItem;
    Toolbar toolbar;


    int totalCount=1;
    int totalPrice=0;

    //Yeni ürümlerim için de  modelimi dahil olarak alalım.
    NewProductsModel newProductsModel=null;

    //Popüler ürünlerim içinde modelimi dahil ediyorum.

    PopularProductModel popularProductModel=null;

    //Tüm ürünlerim için modelimi dahil ediyorum.

    AllProductModel allProductModel=null;


    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        toolbar=binding.detailToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        final Object object=getIntent().getSerializableExtra("details"); //objeme intenti çektim bu activity üzerinde.

        //objemi instanceof ile o sınıfa ait olup olmadığını kontrol ediyorum.

        if (object instanceof NewProductsModel){

            newProductsModel=(NewProductsModel) object;
        }
        else if(object instanceof PopularProductModel){
            popularProductModel=(PopularProductModel) object;

        }
        else if (object instanceof AllProductModel){
            allProductModel=(AllProductModel) object;
        }


        //activity üzerindeki tüm viewlerimi bağladım.
        detailImg=binding.detailsImg;
        rating=binding.rating;
        name=binding.detailName;
        description=binding.detailDesc;
        price=binding.detailPrice;
        addCart=binding.addCart;
        buyCart=binding.buyNow;
        addItem=binding.addItem;
        removeItem=binding.removeItem;
        quantity=binding.quantity;


        //View üzerindeki, intent ile new products değerlerini detail activitye aktardım.
        if (newProductsModel!=null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));
            totalPrice=newProductsModel.getPrice() * totalCount;
        }

        //View üzerindeki, intent ile popular products değerlerini detail activitye aktardım.
        if (popularProductModel!=null){
            Glide.with(getApplicationContext()).load(popularProductModel.getImg_url()).into(detailImg);
            name.setText(popularProductModel.getName());
            rating.setText(popularProductModel.getRating());
            description.setText(popularProductModel.getDescription());
            price.setText(String.valueOf(popularProductModel.getPrice()));
            totalPrice=popularProductModel.getPrice() * totalCount;


        }

        //View üzerindeki, intent ile tüm ürünlerimin değerlerini detail activitye aktardım.

        if (allProductModel!=null){
            Glide.with(getApplicationContext()).load(allProductModel.getImg_url()).into(detailImg);
            name.setText(allProductModel.getName());
            rating.setText(allProductModel.getRating());
            description.setText(allProductModel.getDescription());
            price.setText(String.valueOf(allProductModel.getPrice()));
            totalPrice=allProductModel.getPrice() * totalCount;


        }

        //Siparişi tamamla ekranı bizi adres aktivitesine yönlendirecek.

        buyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailsActivity.this,AddressActivity.class);

                if (newProductsModel!=null){
                    intent.putExtra("item",newProductsModel);
                }
                if (popularProductModel!=null){
                    intent.putExtra("item",popularProductModel);
                }
                if (allProductModel!=null){
                    intent.putExtra("item",allProductModel);
                }

                startActivity(intent);
            }
        });

        //sepete ekle butonuma bir listener verip içerisinde, ürünü sepete eklediğinde gerçekleşecek adımlar için bir metot çağıracağım.
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCart();

            }
        });

        addItem.setOnClickListener(new View.OnClickListener() { //addItem'a tıklandığında sayıyı ve toplam fiyatı arttırma işlemi gerçekleştirecek.
            @Override
            public void onClick(View view) {

                if (totalCount<10){
                    totalCount++;
                    quantity.setText(String.valueOf(totalCount));
                    if (newProductsModel!=null){
                        totalPrice=newProductsModel.getPrice() * totalCount;
                        price.setText(String.valueOf(totalPrice));

                    }
                    if (popularProductModel!=null)
                    {
                        totalPrice=popularProductModel.getPrice() * totalCount;
                        price.setText(String.valueOf(totalPrice));

                    }
                    if (allProductModel!=null){
                        totalPrice=allProductModel.getPrice() * totalCount;
                        price.setText(String.valueOf(totalPrice));

                    }
                }

            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() { //removeItem'a tıklandığında sayıyı ve toplam fiyatı azaltma işlemi gerçekleştirecek.
            @Override
            public void onClick(View view) {
                if (totalCount>1){
                    totalCount--;
                    quantity.setText(String.valueOf(totalCount));
                    if (newProductsModel!=null){
                        totalPrice=newProductsModel.getPrice() * totalCount;
                        price.setText(String.valueOf(totalPrice));

                    }
                    if (popularProductModel!=null)
                    {
                        totalPrice=popularProductModel.getPrice() * totalCount;
                        price.setText(String.valueOf(totalPrice));


                    }
                    if (allProductModel!=null){
                        totalPrice=allProductModel.getPrice() * totalCount;
                        price.setText(String.valueOf(totalPrice));

                    }
                }
            }
        });

    }

    //sepete ürün ekleme metodu,firebase tarafında kayıt oluşturacak.
    private void addCart(){

        String saveCurrentTime,saveCurrentDate;

        Calendar calendar=Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDate=new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate=simpleDate.format(calendar.getTime());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=simpleTime.format(calendar.getTime());
        final HashMap<String,Object> cartMap=new HashMap<>();

        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("orderTime",saveCurrentTime);
        cartMap.put("orderDate",saveCurrentDate);
        cartMap.put("totalPrice",totalPrice);
        cartMap.put("totalCount",totalCount);

        firestore.collection("AddCart").document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(DetailsActivity.this, "Favorilere Eklendi", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

    }
}