package com.example.application.model;

public class FoodVO {

    private int foodSeq;
    private String foodName;
    private String kind;
    private String foodPath;
    private int price;
    private String saleTime;
    private int bakerySeq;

    public int getFoodSeq() {
        return foodSeq;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getKind() {
        return kind;
    }

    public String getFoodPath() {
        return foodPath;
    }

    public int getPrice() {
        return price;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public int getBakerySeq() {
        return bakerySeq;
    }

    public void setFoodSeq(int foodSeq) {
        this.foodSeq = foodSeq;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setFoodPath(String foodPath) {
        this.foodPath = foodPath;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public void setBakerySeq(int bakerySeq) {
        this.bakerySeq = bakerySeq;
    }
}
