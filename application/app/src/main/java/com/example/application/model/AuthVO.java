package com.example.application.model;

public class AuthVO {

    private AuthVO() { }
    private static AuthVO instance;
    public static AuthVO getInstance() {
        if (instance == null) {
            instance = new AuthVO();
        }
        return instance;
    }

    MemberVO memberVO;

    public MemberVO getMemberVO() {
        return memberVO;
    }

    public void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }
}
