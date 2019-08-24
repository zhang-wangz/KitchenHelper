package model;

import java.util.Objects;

public class BeanFoodInfo {
    private String foodId;
    private String foodName;
    private Integer foodPrice;
    private Integer foodNum;
    private String foodTypeNameOfFoodInfo;


    private String foodDes = "该食材暂时没有信息描述";

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodTypeNameOfFoodInfo() {
        return foodTypeNameOfFoodInfo;
    }

    public void setFoodTypeNameOfFoodInfo(String foodTypeNameOfFoodInfo) {
        this.foodTypeNameOfFoodInfo = foodTypeNameOfFoodInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeanFoodInfo)) return false;
        BeanFoodInfo foodInfo = (BeanFoodInfo) o;
        return Objects.equals(getFoodId(), foodInfo.getFoodId()) &&
                Objects.equals(getFoodName(), foodInfo.getFoodName()) &&
                Objects.equals(getFoodPrice(), foodInfo.getFoodPrice()) &&
                Objects.equals(getFoodNum(), foodInfo.getFoodNum()) &&
                Objects.equals(foodTypeNameOfFoodInfo, foodInfo.foodTypeNameOfFoodInfo) &&
                Objects.equals(getFoodDes(), foodInfo.getFoodDes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFoodId(), getFoodName(), getFoodPrice(), getFoodNum(), getFoodDes());
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }


    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(Integer foodNum) {
        this.foodNum = foodNum;
    }

    public String getFoodDes() {
        return foodDes;
    }

    public void setFoodDes(String foodDes) {
        this.foodDes = foodDes;
    }
}
