package com.example.application.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.application.R;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHomepage;


    public SplashActivity(Button btnHomepage) {
        this.btnHomepage = btnHomepage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SplashActivity", "isTaskRoot :" + isTaskRoot());
        if (!isTaskRoot()) {

            finish();
            return;
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_homepage) {
            toHome();
        }
    }

    private void toHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}