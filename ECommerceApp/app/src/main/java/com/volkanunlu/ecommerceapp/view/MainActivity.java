package com.volkanunlu.ecommerceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.databinding.ActivityMainBinding;
import com.volkanunlu.ecommerceapp.fragments.HomeFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Fragment homeFragment;
    Toolbar toolbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        toolbar=binding.homeToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        auth=FirebaseAuth.getInstance();

        homeFragment=new HomeFragment();
        loadFragment(homeFragment); //metotu çağırdım ui tarafında activity açıldığında yüklenmesi adına.

    }

    private void loadFragment(Fragment homeFragment) {

        //fragmentımı yüklemek adına bir metot yarattım ve bunu oncreate içerisinde çağırdım.
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.homeContainer.getId(), homeFragment);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Menüyü bağladık.

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Menü item seçeneklerine göre intentlerini verdik.
        int id=item.getItemId();

        if (id==R.id.menu_log_out){
            auth.signOut();
            Intent intent=new Intent(MainActivity.this,RegistirationActivity.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.menu_favourite_cart){

            Intent intent=new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
        }
        return true;
    }
}