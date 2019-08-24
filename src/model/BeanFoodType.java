package model;

public class BeanFoodType {
    private String foodTypeId;
    private String foodTypeName;
    private String foodTypeDes = "该类型暂时没有描述";

    public String getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(String foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    public String getFoodTypeDes() {
        return foodTypeDes;
    }

    public void setFoodTypeDes(String foodTypeDes) {
        this.foodTypeDes = foodTypeDes;
    }

    @Override
    public String toString() {
        return getFoodTypeName();
    }
}
