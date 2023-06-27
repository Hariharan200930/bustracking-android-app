package com.example.bus_trail2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
// SPLIT SCREEN VIDEO!!!!
public class split_scr_vid extends AppCompatActivity {
    Handler handler;
    SharedPreferences preferences;  //for intro screen
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_scr_vid);

        preferences = this.getSharedPreferences("splash",MODE_PRIVATE);
        editor = preferences.edit();

        ImageView imv = findViewById(R.id.split_scrn);      //split video
        Glide .with(this).asGif().load(R.raw.di).into(imv);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if(preferences.getBoolean("isMain",false)){     //if not first time
                    Intent norm_op = new Intent(split_scr_vid.this,MainActivity.class);
                    startActivity(norm_op);
                    finish();
                }else{
                    editor.putBoolean("isMain",true);
                    editor.apply();         //if its first time opening tus true

                    TaskStackBuilder.create(split_scr_vid.this)     //works like stack sends one by one
                            .addNextIntentWithParentStack(new Intent(split_scr_vid.this,MainActivity.class))     //2nd pushed
                            .addNextIntent(new Intent(split_scr_vid.this,introACT.class))       //top of the stack-1st pushed
                            .startActivities();
                }

            }
        },4000);


    }
}