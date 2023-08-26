package com.example.hackathonpractice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.utils.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuoteActivity extends AppCompatActivity {
    TextInputLayout editAutho,editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);
        editAutho= findViewById(R.id.editAutho);
        editText= findViewById(R.id.editText);

    }

    public void saveQuote(View view) {

        Quote quote = new Quote();
        quote.setAuthor(editAutho.getEditText().getText().toString());
        quote.setText(editText.getEditText().getText().toString());
        int uid = getSharedPreferences("mobileStore",MODE_PRIVATE).getInt("uid",0);
        quote.setUser_id(uid);

        RetrofitClient.getInstance().getApi().InsertQuote(quote).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                finish();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(AddQuoteActivity.this, "Somthing went gone", Toast.LENGTH_SHORT).show();
            }
        });

    }
}