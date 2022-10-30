package com.volkanunlu.ecommerceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.volkanunlu.ecommerceapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;   //view binding kullanıyorum tanımladım.
    EditText email,password;   //Xml üzerinde var olan edittextleri değişkene bağlayarak ihtiyaçlarıma göre kullanacağım.
    private FirebaseAuth auth;  //Firebase kullanıyorum,authentication işlemi için tanımladım.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //status bar gizleme
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        auth=FirebaseAuth.getInstance();

        email=binding.email;
        password=binding.password;

    }

    public void signIn(View view){

        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();


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

        //Bu kısımda firebase'de userslarda bulunan kullanıcı maili ve şifreye göre intent işlemini gerçekleştireceğim.
        //Eğer girilen input users kayıtları ile eşleşirse intent olacak, yoksa ui tarafında hata bildirilecek.
        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Hata:" +task.getException(), Toast.LENGTH_SHORT).show();
                    System.out.println("Hata: " + task.getException());
                }

            }
        });


    }

    public void singUp(View view){
        Intent intent=new Intent(LoginActivity.this,RegistirationActivity.class);
        startActivity(intent);

    }
}