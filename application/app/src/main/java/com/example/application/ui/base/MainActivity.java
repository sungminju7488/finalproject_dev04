package com.example.application.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.example.application.R;
import com.example.application.model.AuthVO;
import com.example.application.model.MemberVO;
import com.example.application.ui.alarm.AlarmListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("hans");
        memberVO.setAlarmSet("6,2,3,4,5,");
        memberVO.setMemberSeq(1);

        AuthVO.getInstance().setMemberVO(memberVO);

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