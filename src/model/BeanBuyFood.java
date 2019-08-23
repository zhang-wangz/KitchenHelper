package model;

import java.io.Serializable;
// 管理员 食材采购
public class BeanBuyFood implements Serializable {
    private static final long serialVersionUID = 1L;

    private String buyOrderId;
    private String foodId;
    private Integer num;
    private Integer status;

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(String buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
