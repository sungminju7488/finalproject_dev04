package com.example.application.model;

public class MemberVO {

    private int memberSeq;
    private String memberId;
    private String password;
    private String name;
    private String nickName;
    private String address1;
    private String address2;
    private String phoneNumber;
    private String email;
    private String birthDay;
    private String sex;
    private String joinDate;
    private String grade;
    private String alarmSet;
    private String followSet;
    private String flatForm;

    public MemberVO(Object value, String value1, String value2, String value3, String value4, Object o) {
    }

    public int getMemberSeq() {
        return memberSeq;
    }

    public void setMemberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAlarmSet() {
        return alarmSet;
    }

    public void setAlarmSet(String alarmSet) {
        this.alarmSet = alarmSet;
    }

    public String getFollowSet() {
        return followSet;
    }

    public void setFollowSet(String followSet) {
        this.followSet = followSet;
    }

    public String getFlatForm() {
        return flatForm;
    }

    public void setFlatForm(String flatForm) {
        this.flatForm = flatForm;
    }
}
