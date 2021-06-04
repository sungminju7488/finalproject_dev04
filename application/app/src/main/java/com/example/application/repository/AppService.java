package com.example.application.repository;

import com.example.application.model.LoginVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppService {

    @POST("member/login")
    Call<LoginVO> loginMember(@Body LoginVO memberVO);
}
