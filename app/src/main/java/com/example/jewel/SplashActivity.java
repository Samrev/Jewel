package com.example.jewel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent splash = new Intent(SplashActivity.this , MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(splash);
                finish();
            }
        },2000);

        TextView splashAnim;
        splashAnim = findViewById(R.id.splashid);
        Animation anim = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splashanim);
        splashAnim.startAnimation(anim);

    }
}