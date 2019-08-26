package model;

import util.KitchenSystemUtil;

import java.io.Serializable;

//��������
public class BeanRecipematerials implements Serializable {
    private static final long serialVersionUID = 1L;
    private String recipeId;
    private String foodId;
    private Integer numOfFood;
    private String workAddress;//��λ

    private String foodName;

    public String getFoodName() {
        return KitchenSystemUtil.foodInfoController.findFoodById(this.foodId).getFoodName();
    }

    public BeanRecipematerials() {
    }

    @Override
    public String toString() {
        return getFoodId();
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
        this.numOfFood = numOfFood;
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

