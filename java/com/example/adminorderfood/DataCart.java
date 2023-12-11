package com.example.adminorderfood;

public class DataCart {
    String foodName ;
    Long foodPrice ;
    String foodDesc ;
    int foodQte ;

    public DataCart(String foodName, Long foodPrice, String foodDesc, int totalQte) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDesc = foodDesc;
        this.foodQte = totalQte;
    }
    public DataCart(){

    }
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }



    public Long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Long foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public int getFoodQte() {
        return foodQte;
    }

    public void setFoodQte(int foodQte) {
        this.foodQte = foodQte;
    }
}
