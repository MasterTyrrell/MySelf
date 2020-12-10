package com.first.memorandum.entity;
import java.time.LocalDateTime;

public class User extends AbstactEntity {

    private String userName;
    private String password;
    private String mobileNo;
    private Integer status;
    private String imageUrl;
    private String email;
    private String token;
    private Integer userType;

    public User(){
        super.setCreateTime(LocalDateTime.now());
        super.setUpdateTime(LocalDateTime.now());
        super.setDelFlag(false);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", status=" + status +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", userType=" + userType +
                '}';
    }
}
