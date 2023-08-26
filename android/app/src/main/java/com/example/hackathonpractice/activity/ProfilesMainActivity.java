package com.example.hackathonpractice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.utils.RetrofitClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilesMainActivity extends AppCompatActivity {
    TextView txtFirstname,txtLastname,txtEmail,txtUsername,txtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_main);
        txtFirstname = findViewById(R.id.txtFirstname);
        txtLastname = findViewById(R.id.txtLastname);
        txtEmail = findViewById(R.id.txtEmail);
        txtUsername = findViewById(R.id.txtUsername);
        txtPhone = findViewById(R.id.txtPhone);
        int  uid = this.getSharedPreferences("mobileStore", this.MODE_PRIVATE).getInt("uid",0);
       // Intent intent = getIntent();
        Log.d("abc",""+uid);
        RetrofitClient.getInstance().getApi().getUserById(uid).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

//                if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
//                {
//                    JsonElement jsonObject = response.body().getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonObject();
//                    txtFirstname.setText("First Name: "+jsonObject.getAsJsonObject().get("first_name").getAsString());
//                    txtLastname.setText("Last Name: "+jsonObject.getAsJsonObject().get("last_name").getAsString());
//                    txtEmail.setText("Email: "+jsonObject.getAsJsonObject().get("email").getAsString());
//                    if((jsonObject.getAsJsonObject().get("uname") != null)){
//                        txtUsername.setText("User name: "+jsonObject.getAsJsonObject().get("uname").getAsString());
//                    }
//                   if((jsonObject.getAsJsonObject().get("phone") !=null)){
//                        txtPhone.setText("Phone: "+jsonObject.getAsJsonObject().get("phone").getAsString());
//                   }
//
//
//
//                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    public void back(View view) {
        finish();
    }
}