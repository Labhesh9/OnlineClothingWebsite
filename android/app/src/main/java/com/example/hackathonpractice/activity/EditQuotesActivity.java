package com.example.hackathonpractice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.utils.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.util.Queue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQuotesActivity extends AppCompatActivity {
    Quote quote;
    Button button;
    TextInputLayout editAutho,editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quotes);
        editAutho= findViewById(R.id.editAuthoedit);
        editText= findViewById(R.id.editTextedit);
        button =findViewById(R.id.update_button);
        Intent intent = getIntent();
        quote = (Quote) intent.getSerializableExtra("quota");
        editAutho.getEditText().setText(quote.getAuthor());
        editText.getEditText().setText(quote.getText());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quote.setText(editText.getEditText().getText().toString());
                quote.setAuthor(editAutho.getEditText().getText().toString());



                RetrofitClient.getInstance().getApi().UpdateQuote(quote).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        finish();
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
    }



   
}