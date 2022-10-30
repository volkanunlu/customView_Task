package com.volkanunlu.ecommerceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.databinding.ActivityAddAddressBinding;
import com.volkanunlu.ecommerceapp.models.AddAddressModel;
import com.volkanunlu.ecommerceapp.models.AddAdressEntityModel;
import com.volkanunlu.ecommerceapp.roomdb.AddAddressDao;
import com.volkanunlu.ecommerceapp.roomdb.AddAddressDatabase;
import com.volkanunlu.ecommerceapp.service.AddAddressAPI;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddAddressActivity extends AppCompatActivity {

    ActivityAddAddressBinding binding;

    EditText nameEditText,addressEditText,disctrictEditText,postCodeEditText,phoneEditText;
    String selectionCountry,selectionCity;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    private String BASE_URL="https://raw.githubusercontent.com/";
    AddAddressAPI addAddressAPI; //api dahil ettim.

    AddAddressDatabase db;
    AddAddressDao addressDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddAddressBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();


        db= Room.databaseBuilder(getApplicationContext(),AddAddressDatabase.class,"Address").build();
        addressDao=db.addAddressDao();

        //CUSTOM VIEW KULLANDIĞIM ACTIVITY
        //Bu aktivitede viewler bir json dosyası dahilinde oluşacaktır.

        LinearLayout viewLayout=(LinearLayout)binding.linear;
        LinearLayout linearLayoutView=(LinearLayout)binding.linearView;
        LinearLayout linearLayoutMakeView=(LinearLayout) binding.makeOtherView;


        //https://raw.githubusercontent.com/volkanunlu/addressTaskJson/main/dataJson.json --> json dosyamın bulunduğu adres.

        ///RETROFİT İLE JSON BAĞLANTISI VE CUSTOMVİEW OLUŞTURMA
        //json dosyamdan çektiğim veride bir switch case yapısı oluşturarak ui tarafında viewler oluşturacağım.

        addAddressAPI=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AddAddressAPI.class);

        addAddressAPI.getData().enqueue(new Callback<List<AddAddressModel>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<List<AddAddressModel>> call, Response<List<AddAddressModel>> response) {
                if (response.isSuccessful()){

                    for (AddAddressModel addAddressModel:response.body()){

                    switch (addAddressModel.getType()){

                        case "Header":{
                            Toolbar toolbar=new Toolbar(AddAddressActivity.this);
                            toolbar.setTitle(addAddressModel.getProperties().getHeader());
                            toolbar.setTitleTextColor(Color.WHITE);
                            viewLayout.addView(toolbar);
                            break;
                        }
                        case "TextInputUserName":{
                            nameEditText=new EditText(AddAddressActivity.this);
                            nameEditText.setHint(addAddressModel.getProperties().getHint());
                            linearLayoutView.addView(nameEditText);
                            break;
                        }
                        case "TextInputAddres":{
                            addressEditText=new EditText(AddAddressActivity.this);
                            addressEditText.setHint(addAddressModel.getProperties().getHint());
                            linearLayoutView.addView(addressEditText);
                            break;
                        }
                        case "ComboboxCountry":{
                            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(AddAddressActivity.this,
                                    android.R.layout.simple_spinner_item,addAddressModel.getProperties().getContent());
                            Spinner spinner=new Spinner(AddAddressActivity.this);
                            spinner.setAdapter(arrayAdapter);
                            linearLayoutView.addView(spinner);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    selectionCountry=adapterView.getSelectedItem().toString();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        break;

                        }
                        case "ComboboxCity":{
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(AddAddressActivity.this, android.R.layout.simple_spinner_item,addAddressModel.getProperties().getContent());
                        Spinner spinner=new Spinner(AddAddressActivity.this);
                            spinner.setAdapter(arrayAdapter);
                            linearLayoutView.addView(spinner);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                selectionCity=adapterView.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        break;
                        }
                        case "TextInputDisctrict":{
                            disctrictEditText=new EditText(AddAddressActivity.this);
                            disctrictEditText.setHint(addAddressModel.getProperties().getHint());
                            linearLayoutView.addView(disctrictEditText);
                            break;
                        }
                        case "TextInputPostCode":{
                            postCodeEditText=new EditText(AddAddressActivity.this);
                            postCodeEditText.setHint(addAddressModel.getProperties().getHint());
                            linearLayoutView.addView(postCodeEditText);
                            break;
                        }
                        case "TextInputPhone":{
                            phoneEditText=new EditText(AddAddressActivity.this);
                            phoneEditText.setHint(addAddressModel.getProperties().getHint());
                            phoneEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                            phoneEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                            linearLayoutView.addView(phoneEditText);
                            break;
                        }
                        case "Toast":{
                            Toast.makeText(AddAddressActivity.this, addAddressModel.getProperties().getHint(), Toast.LENGTH_LONG).show();

                            }
                         }
                    }
                    //Butonum hem firebase hem roomdb tarafına girilen inputları ekleyecek kayıt işlemi sağlayacak.

                    Button saveButton = new Button(AddAddressActivity.this);
                    saveButton.setText("Kaydet");
                    saveButton.setBackgroundColor(Color.parseColor("#03A9F4"));
                    saveButton.setTextColor(Color.parseColor("#FFFFFF"));
                    linearLayoutMakeView.addView(saveButton);
                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String userName=nameEditText.getText().toString();
                            String userAddress=addressEditText.getText().toString();
                            String userCountry=selectionCountry;
                            String userCity=selectionCity;
                            String userDiscrict=disctrictEditText.getText().toString();
                            String userPostCode=postCodeEditText.getText().toString();
                            String userPhone=phoneEditText.getText().toString();

                            String final_address="";

                            if (!userName.isEmpty()){
                                final_address+=" "+userName;
                            }
                            if (!userAddress.isEmpty()){
                                final_address+=" "+userAddress;
                            }
                            if (!userCountry.isEmpty()){
                                final_address+=" "+userCountry;
                            }
                            if (!userCity.isEmpty()){
                                final_address+=" "+userCity;
                            }
                            if (!userDiscrict.isEmpty()){
                                final_address+=""+userDiscrict;
                            }
                            if (!userPostCode.isEmpty()){
                                final_address+=" "+userPostCode;
                            }
                            if (!userPhone.isEmpty()){
                                final_address+=" "+userPhone;
                            }
                            if (!userName.isEmpty() && !userAddress.isEmpty() && !userCountry.isEmpty() && !userCity.isEmpty()
                                    && !userDiscrict.isEmpty() && !userPostCode.isEmpty() && !userPhone.isEmpty()){

                                Map<String,String> addresMap=new HashMap<>();
                                addresMap.put("address",final_address);

                                firestore.collection("UserLocation").document(auth.getCurrentUser().getUid())
                                        .collection("Address").add(addresMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                if (task.isSuccessful()){

                                                    Toast.makeText(AddAddressActivity.this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                            saveData();
                            Intent intent=new Intent(AddAddressActivity.this,AddressActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<AddAddressModel>> call, Throwable t) {
                t.printStackTrace(); //call başarısızsa hatayı bana dönsün.
            }
        });

    }

    public void saveData(){ //Roomdb için kayıt yaptığım metot

        AddAdressEntityModel addAdressEntityModel=new AddAdressEntityModel(
            nameEditText.getText().toString(),
            addressEditText.getText().toString(),
            selectionCountry,
            selectionCity,
            disctrictEditText.getText().toString(),
            postCodeEditText.getText().toString(),
            phoneEditText.getText().toString()
        );

        compositeDisposable.add(addressDao.insert(addAdressEntityModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(AddAddressActivity.this::handleResponse)
        );
    }

    private void handleResponse(){
        nameEditText.setText("");
        addressEditText.setText("");
        disctrictEditText.setText("");
        postCodeEditText.setText("");
        phoneEditText.setText("");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}