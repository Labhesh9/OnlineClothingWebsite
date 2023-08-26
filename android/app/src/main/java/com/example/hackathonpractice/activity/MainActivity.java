package com.example.hackathonpractice.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.adapter.QuoteFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
      Toolbar toolBarMain;
      ViewPager2 viewPager2;
      TabLayout tabLayout;
      QuoteFragmentAdapter quoteFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();

        setSupportActionBar(toolBarMain);
        quoteFragmentAdapter = new QuoteFragmentAdapter(this);
        viewPager2.setAdapter(quoteFragmentAdapter);



        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {



            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                       // toolBarMain.setTitle("Home - All Quotes");
                        tab.setIcon(R.drawable.explore);
                        tab.setText("Explore");
                        break;
                    case 1:
                      //  toolBarMain.setTitle("Home - All My Quotes Add");
                        tab.setIcon(R.drawable.cart);
                        tab.setText("Cart");
                        break;
                    case 2:
                      //  toolBarMain.setTitle("Home - All My Quotes Favorite");
                        tab.setIcon(R.drawable.wishlist);
                        tab.setText("Wishlist");
                        break;
                    case 3:
                        //  toolBarMain.setTitle("Home - All My Quotes Favorite");
                        tab.setIcon(R.drawable.profile);
                        tab.setText("Profile");
                        break;

                }
            }

        }).attach();

    }


    private void Mapping() {

        toolBarMain= findViewById(R.id.toolBarMain);
        tabLayout= findViewById(R.id.tabLayout);
        viewPager2= findViewById(R.id.viewPager2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mainmenu,menu);
//        menu.add("Profile");
//        menu.add("Settings");
//        menu.add("Logout");
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.addAccount)
//        {
//            Intent intent=new Intent(this, AddQuoteActivity.class);
//            startActivity(intent);
//
//        }
//        else
            if(item.getItemId()==R.id.logout)

        {
               getSharedPreferences("Login_status",MODE_PRIVATE)
                       .edit().putBoolean("login",false).apply();
               Intent intent = new Intent(this,LoginActivity.class);
               startActivity(intent);
               finish();
        }
        else if(item.getItemId()==R.id.profiles)

        {
           // int  uid = this.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
            Intent intent = new Intent(this, ProfilesMainActivity.class);
          //  intent.putExtra("uid",uid);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}