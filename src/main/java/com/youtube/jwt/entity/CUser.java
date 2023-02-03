package com.youtube.jwt.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CUser {


    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String usermail;

    public CUser() {

    }
    public CUser(String userName, String userFirstName, String userLastName, String userPassword, String usermail) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.usermail = usermail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", usermail='" + usermail + '\'' +
                '}';
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
