package com.example.hackathonpractice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hackathonpractice.R;
import com.example.hackathonpractice.entity.Carts;
import com.example.hackathonpractice.entity.Products;
import com.example.hackathonpractice.entity.WistList;
import com.example.hackathonpractice.utils.API;
import com.example.hackathonpractice.utils.FuncUtils;

public class ProductActivity extends AppCompatActivity {
    Toolbar toolbar2;
    Products products;
    TextView txtNameProductdetail, txtPriceProductsdetail, txtSub, txtAdd, txtQuantity, txtDescription;
    ImageView imgProducts, imageWishList;
    Button btnAddCart;
    int user_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();

        user_id = getSharedPreferences("mobileStore",MODE_PRIVATE).getInt("uid",0);
        Log.d("aaa",""+user_id);
        products = (Products) intent.getSerializableExtra("products");



        txtNameProductdetail = findViewById(R.id.txtNameProductdetail);
        txtPriceProductsdetail = findViewById(R.id.txtPriceProductsdetail);
        btnAddCart = findViewById(R.id.btnAddCart);
        txtDescription = findViewById(R.id.txtDescription);
        txtSub = findViewById(R.id.txtSub);
        txtAdd = findViewById(R.id.txtAdd);
        txtQuantity = findViewById(R.id.txtQuantity);
        imageWishList = findViewById(R.id.imageWishList);
        txtQuantity.setText(""+1);
        imgProducts = findViewById(R.id.imgProducts);
        toolbar2 =findViewById(R.id.toolbar2);

        toolbar2.setTitle(products.getNameProduct());
        txtNameProductdetail.setText(products.getNameProduct());
        txtPriceProductsdetail.setText("₹" + products.getPriceProduct());
        txtDescription.setText(products.getDescription());
        Glide.with(this).load(API.BASE_URL + "/" + products.getImageProduct()).into(imgProducts);
        FuncUtils.LoadWisListIcon(imageWishList,products.getIdProduct());

        setSupportActionBar(toolbar2);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carts cart = new Carts();
                cart.setProductId(products.getIdProduct());
                cart.setUserId(user_id);
                cart.setQuantity( Integer.parseInt(txtQuantity.getText().toString()) );
                cart.setStatusId(1);
                Log.d("aaa","cart :" + cart.toString());

                FuncUtils.addCart(cart);

                finish();
            }
        });

        txtSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(txtQuantity.getText().toString()) - 1;
              //  txtQuantity.setText( );
                if (temp < 1)
                {
                    temp =1;
                }
                txtQuantity.setText( "" + temp);

            }
        });

        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(txtQuantity.getText().toString()) + 1;
                //  txtQuantity.setText( );
                if (temp > 10)
                {
                    temp =10;
                }
                txtQuantity.setText( "" + temp);
            }
        });

        imageWishList.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"UseCompatLoadingForDrawables", "ResourceType"})
            @Override
            public void onClick(View view) {
                WistList wistList = new WistList();
                wistList.setUserId(user_id);
                wistList.setProductId(products.getIdProduct());

                FuncUtils.ToogleWisList(imageWishList,wistList);

              //  Log.d("abc","test 1"+ fDraw.hashCode() );
              //  Log.d("abc","test 1"+ sDraw.hashCode() );

//                if (test == test2)
//                {
//                    Toast.makeText(view.getContext(), "Image is ivPic", Toast.LENGTH_LONG).show();
//                    // new RegisterAsyntaskNew().execute();
//                }
//                else
//                {
//                    Toast.makeText(view.getContext(), "Image isn't ivPic", Toast.LENGTH_LONG).show();
//                    // new RegisterAsyntask().execute();
//                }



//                if(!imageWishList.getDrawable().toString().equals("android.graphics.drawable.VectorDrawable@5df29e5"))
//                {
//                    FuncUtils.addWistList(wistList);
//                    FuncUtils.LoadWisListIcon(imageWishList,products.getIdProduct());
//                }
               //
                //
            }
        });

       // getSupportActionBar().show();
//        if(getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
       // this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().show();
      //  getActionBar().setDisplayShowHomeEnabled(false);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeButtonEnabled(true);
//        }
//        else
//        {       getActionBar().setDisplayHomeAsUpEnabled(true);
//            getActionBar().setHomeButtonEnabled(true);
//        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.mainmenu,menu);
////        menu.add("Profile");
////        menu.add("Settings");
////        menu.add("Logout");
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}