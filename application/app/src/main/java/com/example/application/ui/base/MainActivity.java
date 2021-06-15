package com.example.application.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.application.R;
import com.example.application.ui.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MemberVO memberVO = new MemberVO();
//        memberVO.setMemberId("hans");
//        memberVO.setAlarmSet("6,2,3,4,5,");
//        memberVO.setMemberSeq(1);
//
//        AuthVO.getInstance().setMemberVO(memberVO);

        createNotificationChannel();
        navigateTo(new LoginFragment(), false);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean moveTaskToBack(boolean b) {
        return b;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
        super.onBackPressed();

    }

    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Test Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}