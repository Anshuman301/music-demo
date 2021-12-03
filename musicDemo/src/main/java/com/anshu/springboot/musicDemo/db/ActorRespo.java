package com.anshu.springboot.musicDemo.db;

import java.util.List;

import com.anshu.springboot.musicDemo.model.entity.Actor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActorRespo extends CrudRepository<Actor, Integer>{
    public List<Actor> findByFirstName(String fName);

    public List<Actor> findByFirstNameAndLastName(@Param("fName") String fName, @Param("lName") String lName);
}
