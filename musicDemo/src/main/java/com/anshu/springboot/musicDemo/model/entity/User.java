package com.anshu.springboot.musicDemo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @NoArgsConstructor @RequiredArgsConstructor @Getter @Setter @ToString
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;
    @Column(name="user_name") @NonNull String userName;
    @Getter(value = AccessLevel.NONE)
    @Column(name="email_address")@NonNull private String emailAddress;
    @Column(name="picture_url") @NonNull private String pictureUrl;
    @Column(name="provider_id") @NonNull private String providerId;    
    
    public String getEmailAddress() {
        return emailAddress;
    }
}
