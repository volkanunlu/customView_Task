package com.volkanunlu.ecommerceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.volkanunlu.ecommerceapp.databinding.ActivityRegistirationBinding;

public class RegistirationActivity extends AppCompatActivity {

    ActivityRegistirationBinding binding;   //view binding kullanıyorum.
    EditText name, email,password;  //Sırasıyla login ekranında bulunan edittextlerimi verdim.
    private FirebaseAuth auth;   //Firebase kullanıyorum,authentication işlemi için tanımladım.

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistirationBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        name=binding.name;    //verdiğim edittextler ile xml üzerindeki viewleri bağladım.
        email=binding.email;  //verdiğim edittextler ile xml üzerindeki viewleri bağladım.
        password=binding.password; //verdiğim edittextler ile xml üzerindeki viewleri bağladım.

        auth=FirebaseAuth.getInstance();

        if (auth.getCurrentUser()!=null){  //Eğer mevcut bir kullanıcı varsa, direk main activity kısmına intent işlemi gerçekleştiriyoruz.
            Intent intent=new Intent(RegistirationActivity.this,MainActivity.class);
            startActivity(intent);
        }

        sharedPreferences=getSharedPreferences("TransitionScreen",MODE_PRIVATE);
        boolean isFirst=sharedPreferences.getBoolean("firstTime",true);

        if (isFirst){  //ilk defa aktivite açıldığında, karşılama ekranını 1 seferlik açması için sharedPreferences ile boolean değer tutuyoruz.
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.apply();
            Intent intent=new Intent(RegistirationActivity.this,TransitionActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signUp(View view){

        String userName=name.getText().toString();     //input olarak girilecek değerleri getText ile çekip, bir değişkene aktardım kullanmak için.
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();

        if(TextUtils.isEmpty(userName)){ //TextUtils boolean bir değer tutar, burada validasyon olması amacı ile ui tarafında toast message gösteriyorum.

            Toast.makeText(this, "Lütfen Adınızı Giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){ //TextUtils boolean bir değer tutar, burada validasyon olması amacı ile ui tarafında toast message gösteriyorum.

            Toast.makeText(this, "Lütfen Email Adresinizi Giriniz.", Toast.LENGTH_SHORT).show();
            return;
        } if(TextUtils.isEmpty(userPassword)){ //TextUtils boolean bir değer tutar, burada validasyon olması amacı ile ui tarafında toast message gösteriyorum.

            Toast.makeText(this, "Lütfen Parolanızı Giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<6){ //Kullanıcı parolasının 6 karakterden fazla olması gerek(Firebase Kuralları), burada validasyon olması amacı ile ui tarafında toast message gösteriyorum.

            Toast.makeText(this, "Parolanız En Az 6 Karakterden Oluşmalıdır!", Toast.LENGTH_SHORT).show();
            return;
        }


        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(RegistirationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){ //Başarılı giriş yapıldığında Main activity ekranımıza yönlendirme işlemi.

                            Toast.makeText(RegistirationActivity.this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegistirationActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{  //Kullanıcı bilgilendiriliyor, aldığı hata ile birlikte
                            Toast.makeText(RegistirationActivity.this, "Kullanıcı Maili ya da Şifre Yanlış!"+task.getException(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
    public void singIn(View view){

        Intent intent=new Intent(RegistirationActivity.this, LoginActivity.class);
        startActivity(intent);

    }

}