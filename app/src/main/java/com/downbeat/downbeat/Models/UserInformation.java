package com.downbeat.downbeat.Models;

public class UserInformation {

    private String userName, userPhoneNumber, userEmail, userPhotoURL;

    public UserInformation(String userName, String userPhoneNumber, String userEmail, String userPhotoURL) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPhotoURL = userPhotoURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhotoURL() {
        return userPhotoURL;
    }

    public void setUserPhotoURL(String userPhotoURL) {
        this.userPhotoURL = userPhotoURL;
    }


}
