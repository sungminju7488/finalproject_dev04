package com.example.application.model;

public class AuthVO {

    public AuthVO() { }
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



    private String id;
    private String password;

    public AuthVO(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}