package com.example.application.repository;

import com.example.application.model.AuthVO;
import com.example.application.model.FoodVO;
import com.example.application.model.LoginVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppService {

    @POST("member/login")
    Call<LoginVO> loginMember(@Body LoginVO memberVO);

    @POST("bakery/useAlarm")
    Call<List<FoodVO>> useAlarm(@Body AuthVO authVO);
}
