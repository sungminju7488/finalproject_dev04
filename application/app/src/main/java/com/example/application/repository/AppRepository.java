package com.example.application.repository;

import com.example.application.model.AuthVO;
import com.example.application.model.FoodVO;
import com.example.application.model.MemberVO;

import java.util.List;

import retrofit2.Callback;

public class AppRepository {
    private AppService appService;

    public AppRepository(AppService appService) {
        this.appService = appService;
    }

    public void useAlarm(MemberVO memberVO, Callback<List<FoodVO>> callback){
        appService.useAlarm(memberVO).enqueue(callback);
    }
    public void loginMember(MemberVO memberVO, Callback<MemberVO> callback) {
        appService.loginMember(memberVO).enqueue(callback);
    }
}
