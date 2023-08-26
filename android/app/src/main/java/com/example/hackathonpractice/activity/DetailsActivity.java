//package com.example.hackathonpractice.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.example.hackathonpractice.R;
//import com.example.hackathonpractice.entity.Quote;
//import com.example.hackathonpractice.entity.Orders;
//import com.example.hackathonpractice.utils.RetrofitClient;
//import com.google.gson.JsonObject;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class DetailsActivity extends AppCompatActivity {
//
//    ImageView imgaeMobile;
//    TextView textName,textCompany,textPrice;
//    Button btnBuy,btnCancel;
//    Quote quote;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
//        Mapping();
//
//        quote = new Quote();
//        quote = (Quote) getIntent().getSerializableExtra("mobile");
//        getMobileDetails();
//        btnBuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Orders orders = new Orders();
//                int uid = getSharedPreferences("mobileStore",MODE_PRIVATE).getInt("uid",0);
//
//                orders.setUid(uid);
//                orders.setMid(quote.getId());
//                Log.e("aaa",orders.toString());
//                RetrofitClient.getInstance().getApi().InsertOrders(orders).enqueue(new Callback<JsonObject>() {
//                    @Override
//                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<JsonObject> call, Throwable t) {
//                        Toast.makeText(DetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }
//
//    private void getMobileDetails() {
//
//        textName.setText(quote.getMname());
//        textCompany.setText(quote.getCompany());
//        textPrice.setText(""+ quote.getPrice());
//        textPrice.setText(""+ quote.getPrice());
//        Glide.with(this).load("http://10.42.0.1:4000/"+ quote.getImage()).into(imgaeMobile);
//
//    }
//
//    private void Mapping() {
//
//        imgaeMobile = findViewById(R.id.imgaeMobile);
//        textName = findViewById(R.id.textName);
//        textCompany = findViewById(R.id.textCompany);
//        textPrice = findViewById(R.id.textPrice);
//        btnBuy = findViewById(R.id.btnBuy);
//        btnCancel = findViewById(R.id.btnCancel);
//    }
//}