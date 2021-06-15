package com.example.application.model;

public class BakeryVO {

    private String copRegNum;
    private String manager;
    private String storeName;
    private String storeAddress1;
    private String storeAddress2;
    private String storeContact;
    private String bakeryPath;
    private String businessHour;
    private String holiday;
    private String specialHoliday;
    private char eatable;
    private String latitude;
    private String longitude;
    private char boardSet;
    private MemberVO memberVO;

    public String getCopRegNum() {
        return copRegNum;
    }

    public void setCopRegNum(String copRegNum) {
        this.copRegNum = copRegNum;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress1() {
        return storeAddress1;
    }

    public void setStoreAddress1(String storeAddress1) {
        this.storeAddress1 = storeAddress1;
    }

    public String getStoreAddress2() {
        return storeAddress2;
    }

    public void setStoreAddress2(String storeAddress2) {
        this.storeAddress2 = storeAddress2;
    }

    public String getStoreContact() {
        return storeContact;
    }

    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }

    public String getBakeryPath() {
        return bakeryPath;
    }

    public void setBakeryPath(String bakeryPath) {
        this.bakeryPath = bakeryPath;
    }

    public String getBusinessHour() {
        return businessHour;
    }

    public void setBusinessHour(String businessHour) {
        this.businessHour = businessHour;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getSpecialHoliday() {
        return specialHoliday;
    }

    public void setSpecialHoliday(String specialHoliday) {
        this.specialHoliday = specialHoliday;
    }

    public char getEatable() {
        return eatable;
    }

    public void setEatable(char eatable) {
        this.eatable = eatable;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public char getBoardSet() {
        return boardSet;
    }

    public void setBoardSet(char boardSet) {
        this.boardSet = boardSet;
    }

    public MemberVO getMemberVO() {
        return memberVO;
    }

    public void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }
}
