package com.example.application.repository;


import com.example.application.model.LoginInfo;
import com.example.application.model.MemberVO;
import com.example.application.repository.MemberService;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

public class MemberRepository {
    private MemberService memberService;

    public MemberRepository(MemberService memberService) {
        this.memberService = memberService;
    }

    public void loginMember(LoginInfo loginInfo, Callback<MemberVO> callback) {
        memberService.loginMember(loginInfo).enqueue(callback);
    }

    public void JoinInMember(MemberVO memberVO, Callback<Void> callback) {
        memberService.JoininMember(memberVO).enqueue(callback);
    }

}



