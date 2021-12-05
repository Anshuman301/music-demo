package com.anshu.springboot.musicDemo.model.dto;

import javax.persistence.Id;

import lombok.Setter;
@Setter
public class ActorUpdationDTO {
    @Id
    private int id;
    private String firstName;
    private String lastName;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
