package com.example.hackathonpractice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.hackathonpractice.R;

public class FlashActivity extends AppCompatActivity {
    ImageView imageflower;
    Animation animationFlower ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        imageflower = findViewById(R.id.imageflower);
//        animationFlower = AnimationUtils.loadAnimation(this,R.anim.translation_scale);
//        imageflower.setAnimation(animationFlower);

        YoYo.with(Techniques.FadeIn)
                .duration(1300)
                .repeat(0)
                .playOn(findViewById(R.id.imageflower));

        YoYo.with(Techniques.ZoomIn)
                .duration(1300)
                .repeat(0)
                .playOn(findViewById(R.id.textFlash));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FlashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}