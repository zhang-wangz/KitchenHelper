package model;

import util.KitchenSystemUtil;

import java.sql.Timestamp;
import java.util.Date;

public class BeanFoodOrder {
    private String orderId;
    private String userId;
    private Date sendTime;//หอด๏
    private String sendAddress;
    private String userTel;
    private Integer orderStatus;


    private String  userName;

    public String getUserName() {
        return KitchenSystemUtil.userController.findUserById(userId).getUserName();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return getOrderId();
    }
}
