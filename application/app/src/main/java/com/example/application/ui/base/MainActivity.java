package com.example.application.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.application.R;
import com.example.application.ui.alarm.AlarmListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigateTo(new AlarmListFragment(), false);
    }

    public void navigateTo(Fragment fragment, boolean addToBackStack){
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