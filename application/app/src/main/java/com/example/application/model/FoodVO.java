package com.example.application.model;

public class FoodVO {

    private int foodSeq;
    private String foodName;
    private String kind;
    private String foodPath;
    private int price;
    private String saleTime;
    private BakeryVO bakeryVO;

    private boolean isChecked = false;

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

    public BakeryVO getBakeryVO() {
        return bakeryVO;
    }

    public void setBakeryVO(BakeryVO bakeryVO) {
        this.bakeryVO = bakeryVO;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
