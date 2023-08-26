package com.example.hackathonpractice.activity;

import static com.example.hackathonpractice.R.id.toolbar3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.adapter.ReceiptListAdapter;
import com.example.hackathonpractice.entity.OrderProducts;
import com.example.hackathonpractice.entity.OrderProductsDetail;
import com.example.hackathonpractice.entity.Products;
import com.example.hackathonpractice.utils.FuncUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    Toolbar toolbar3;
    TextView txtCode, txtAddress, txtEmail, txtPhone, txtShipName;
    OrderProducts orderProducts;

    TextView txtSubTotalDetailOrder, txtShipDelivery, txtTaxOrder, txtTotalOrder;

    RecyclerView recycleOrderDetails;
    List<OrderProductsDetail> orderProductsDetailList;
    ReceiptListAdapter receiptListAdapter;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();

        //user_id = getSharedPreferences("mobileStore",MODE_PRIVATE).getInt("uid",0);
        //Log.d("aaa",""+user_id);
        orderProducts = (OrderProducts) intent.getSerializableExtra("orderProducts");
        toolbar3 =findViewById(R.id.toolbar3);
        txtCode = findViewById(R.id.txtCode);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtShipName = findViewById(R.id.txtShipName);


        txtSubTotalDetailOrder = findViewById(R.id.txtSubTotalDetailOrder);
        txtShipDelivery = findViewById(R.id.txtShipDelivery);
        txtTaxOrder = findViewById(R.id.txtTaxOrder);
        txtTotalOrder = findViewById(R.id.txtTotalOrder);

        toolbar3.setTitle("Receipt");
        recycleOrderDetails = findViewById(R.id.recycleOrderDetails);
        orderProductsDetailList = new ArrayList<>();
        receiptListAdapter = new ReceiptListAdapter(this,orderProductsDetailList);
        recycleOrderDetails.setAdapter(receiptListAdapter);
        recycleOrderDetails.setLayoutManager(new GridLayoutManager(this,1));

        setSupportActionBar(toolbar3);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        txtCode.setText("Code:" + orderProducts.getCodeOrder());

        Log.d("aaa", orderProducts.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FuncUtils.LoadAddressById(txtShipName,txtAddress,txtEmail,txtPhone,orderProducts.getAddressUserId());
        FuncUtils.LoadOrderProductsDetail(orderProductsDetailList,this,receiptListAdapter,orderProducts);
        FuncUtils.LoadSumaryOrderProductsDetail(txtSubTotalDetailOrder, txtShipDelivery, txtTaxOrder, txtTotalOrder,orderProducts);
    }
}