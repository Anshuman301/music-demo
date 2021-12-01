package com.anshu.springboot.musicDemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;
    @Column(name="user_name")
    private String userName;
    @Column(name="email_address")
    private String emailAddress;
    @Column(name="picture_url")
    private String pictureUrl;
    @Column(name="provider_id")
    private String providerId;

    public User() {}
    
    public User(String userName, String emailAddress, String pictureUrl, String providerId) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.pictureUrl = pictureUrl;
        this.providerId = providerId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    public String getProviderId() {
        return providerId;
    }

    @Override
    public String toString() {
        return "User [emailAddress=" + emailAddress + ", pictureUrl=" + pictureUrl + ", providerId=" + providerId
                + ", userName=" + userName + "]";
    }

    
    
}
