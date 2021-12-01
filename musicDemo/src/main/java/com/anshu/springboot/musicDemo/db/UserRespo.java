package com.anshu.springboot.musicDemo.db;

import com.anshu.springboot.musicDemo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespo extends JpaRepository<User, Integer>{
    public User findByUserName(String userName);
    public User findByEmailAddress(String emailAddress);
}
