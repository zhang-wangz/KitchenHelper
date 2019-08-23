package model;

import java.io.Serializable;

public class BeanRecipematerials implements Serializable {
    private static final long serialVersionUID = 1L;
    private String recipeId;
    private String foodId;
    private Integer numOfFood;
    private String workAddress;

    public BeanRecipematerials() {
    }

    @Override
    public String toString() {
        return "BeanRecipematerials{" +
                "recipeId='" + recipeId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", NumOfFood=" + numOfFood +
                ", workAddress='" + workAddress + '\'' +
                '}';
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Integer getNumOfFood() {
        return numOfFood;
    }

    public void setNumOfFood(Integer numOfFood) {
        numOfFood = numOfFood;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }
}

