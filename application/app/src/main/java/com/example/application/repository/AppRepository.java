package com.example.application.repository;

import com.example.application.model.AuthVO;
import com.example.application.model.FoodVO;

import java.util.List;

import retrofit2.Callback;

public class AppRepository {
    private AppService appService;

    public AppRepository(AppService appService) {
        this.appService = appService;
    }

    public void useAlarm(AuthVO authVO, Callback<List<FoodVO>> callback){
        appService.useAlarm(authVO).enqueue(callback);
    }
}
