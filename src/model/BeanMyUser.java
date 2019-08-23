package model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class BeanMyUser {
    private String userId;
    private String userName;
    private String userTel;
    private String userEmail;
    private String sex;
    private String pwd;
    private String userContact;
    private String city;
    private Timestamp registerDate;

    public String getUserId() {
        return userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanMyUser that = (BeanMyUser) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userTel, that.userTel) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userContact, that.userContact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userTel, userEmail, userContact);
    }

    @Override
    public String toString() {
        return getUserName();
    }
}
