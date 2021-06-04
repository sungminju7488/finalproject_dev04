package com.example.application.model;

public class AuthVO {

    private int memberSeq;
    private String memberId;
    private String name;
    private String nickName;
    private String grade;
    private String alarmSet;
    private String followSet;

    public int getMemberSeq() {
        return memberSeq;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGrade() {
        return grade;
    }

    public String getAlarmSet() {
        return alarmSet;
    }

    public String getFollowSet() {
        return followSet;
    }

    public void setMemberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAlarmSet(String alarmSet) {
        this.alarmSet = alarmSet;
    }

    public void setFollowSet(String followSet) {
        this.followSet = followSet;
    }
}
