package com.example.application.repository;

import com.example.application.model.MemberVO;
import com.example.application.model.LoginInfo;


import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;

import retrofit2.http.POST;

import retrofit2.http.Part;



public interface MemberService {

    @GET("member")
    Call<List<MemberVO>> getAllMembers();

    @POST("member/login")
    Call<MemberVO> loginMember(@Body LoginInfo loginInfo);


    @POST("member/Joinin")
    Call<Void> JoininMember(@Part("member") MemberVO memberVO);
}