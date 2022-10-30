package com.volkanunlu.ecommerceapp.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.adapters.CategoryAdapter;
import com.volkanunlu.ecommerceapp.adapters.NewProductsAdapter;
import com.volkanunlu.ecommerceapp.adapters.PopularProductsAdapter;
import com.volkanunlu.ecommerceapp.models.CategoryModel;
import com.volkanunlu.ecommerceapp.models.NewProductsModel;
import com.volkanunlu.ecommerceapp.models.PopularProductModel;
import com.volkanunlu.ecommerceapp.view.AllProductsActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    TextView catAll,popularAll,newProductAll;

    LinearLayout linearLayout;
    ProgressDialog progressDialog;

    RecyclerView catRecyclerView,newProductsRecyclerView,popRecyclerView;

    //Kategori için recyclerview,adapter,list kullanacağım.
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //Yeni Ürünler için recyclerview,adapter,list kullanacağım.
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel>newProductsModelList;

    //Popüler ürünler için recyclerview,adapter,list kullanacağım.
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductModel> popularProductModelList;


    FirebaseFirestore db;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_home, container, false);

        db=FirebaseFirestore.getInstance();  //firestore içerisinde bulunan instanceları çektim.

        progressDialog=new ProgressDialog(getActivity()); //bir progressbar oluşturdum.


        //recycler bağlama işlemleri
        catRecyclerView=root.findViewById(R.id.rec_category);
        newProductsRecyclerView=root.findViewById(R.id.new_product_rec);
        popRecyclerView=root.findViewById(R.id.popular_rec);

        //tüm ürünleri göstermek adına, textviewleri sırası ile bağladım.
        catAll=root.findViewById(R.id.category_see_all);
        popularAll=root.findViewById(R.id.popular_see_all);
        newProductAll=root.findViewById(R.id.newProducts_see_all);

        //tümünü gör textine listener ile intent verdim, aktivite geçişi sağlayacak.
        catAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), AllProductsActivity.class);
                startActivity(intent);
            }
        });
        //tümünü gör  textine listener ile intent verdim, aktivite geçişi sağlayacak.

        popularAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(),AllProductsActivity.class);
                startActivity(intent);
            }
        });
        //tümünü gör textine listener ile intent verdim, aktivite geçişi sağlayacak.

        newProductAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),AllProductsActivity.class);
                startActivity(intent);
            }
        });


        //linearlayout bağladım,progressbar için gizleyip yükleme sonrası görünür kıldım.
        linearLayout=root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);


        //image sliderı dahil ediyorum
        ImageSlider imageSlider=root.findViewById(R.id.image_slider);
        List<SlideModel> slideModelList=new ArrayList<>(); //modelim için bir arraylist oluşturdum.

        //Sırası ile listeme görüntülerimi ve başlıklarımı verdim.
        slideModelList.add(new SlideModel(R.drawable.bannerfirst,"Sana Özel Giyim Kampanyası", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.bannersecond,"Kış İndirimleri Başladı", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.bannerthird,"Parfümün Seni Bekliyor", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModelList);

        //progressbar düzenlemeleri

        progressDialog.setTitle("Uygulamaya Hoş Geldiniz");
        progressDialog.setMessage("Lütfen Bekleyiniz");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        //Recyclerview üzerinde firebaseden (Kategori bölümü) çekilen verilerimin alanı
        catRecyclerView.setLayoutManager(new LinearLayoutManager (getActivity(), RecyclerView.HORIZONTAL,false));
        categoryModelList=new ArrayList<>();

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter = new CategoryAdapter(getContext(), categoryModelList);
                                catRecyclerView.setAdapter(categoryAdapter);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        }
                    }
                });


        //Recyclerview üzerinde firebaseden (Yeni ürünler bölümü) çekilen verilerimin alanı

        newProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList=new ArrayList<>();

        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter = new NewProductsAdapter(getContext(), newProductsModelList);
                                newProductsRecyclerView.setAdapter(newProductsAdapter);
                                newProductsAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        //Recyclerview üzerinde firebaseden (Popüler ürünler bölümü) çekilen verilerimin alanı


        popRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductModelList=new ArrayList<>();

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document: task.getResult()){

                            PopularProductModel popularProductModel=document.toObject(PopularProductModel.class);
                            popularProductModelList.add(popularProductModel);
                            popularProductsAdapter=new PopularProductsAdapter(getContext(),popularProductModelList);
                            popRecyclerView.setAdapter(popularProductsAdapter);
                            popularProductsAdapter.notifyDataSetChanged();

                        }
                    }
                    }
                });

        return root;
    }
}