package com.example.application.repository;

import com.example.application.model.AuthVO;
import com.example.application.model.FoodVO;
import com.example.application.model.MemberVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppService {

    @POST("member/login")
    Call<MemberVO> loginMember(@Body AuthVO authVO,@Body MemberVO memberVO);

    @POST("bakery/useAlarm")
    Call<List<FoodVO>> useAlarm(@Body MemberVO memberVO);

    @POST("bakeyr/deleteAlarm")
    Call<MemberVO> deleteAlarm(@Body MemberVO memberVO, @Body FoodVO foodVO);

    Call<MemberVO> loginMember(@Body AuthVO authVO);
}
