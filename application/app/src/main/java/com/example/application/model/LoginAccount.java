package com.example.application.model;


public class LoginAccount {
    private static LoginAccount instance;

    public static LoginAccount getInstance() {
        if (instance == null) {
            instance = new LoginAccount();
        }
        return instance;
    }

    private MemberVO memberVO;

    private LoginAccount() { }

    public void setMember(MemberVO member) {
        this.memberVO = memberVO;
    }

    public MemberVO getMemberVO() {
        return memberVO;
    }
}

