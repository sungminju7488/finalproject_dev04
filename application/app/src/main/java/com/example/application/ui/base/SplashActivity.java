package com.example.application.ui.base;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.R;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private int distanceTime = 5;
    private Button btnHomepage;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SplashActivity", "isTaskRoot :" + isTaskRoot());
        if (!isTaskRoot()) {
           
            finish();
            return;
        }

        initView();
        initCutDown();
    }

    private void initView() {
    }

    private void initCutDown() {
        handler.post(runnable);
    }


    private void toHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
