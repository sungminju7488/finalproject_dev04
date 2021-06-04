package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        navigateTO(new LoginFragment(), false);
    }

    public void navigateTO(Fragment fragment, boolean addToBackStack){
        if(!getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getName(), 0)){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);

            if(addToBackStack){
                transaction.addToBackStack(null);
            }

            transaction.commit();
        }
    }
}