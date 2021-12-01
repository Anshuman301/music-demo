package com.anshu.springboot.musicDemo.db;

import java.util.List;

import com.anshu.springboot.musicDemo.model.entity.Actor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

// @RepositoryRestResource(excerptProjection = ActorData.class)
public interface ActorRespo extends JpaRepository<Actor, Integer>{
    @RestResource(path = "firstName", rel = "firstName")
    public Page<Actor> findByFirstName(@Param("firstName") String fName, Pageable p);

    @RestResource(path = "firstNameAndLastName", rel = "firstNameAndLastName")
    public List<Actor> findByFirstNameAndLastName(@Param("fName") String fName, @Param("lName") String lName);
    
}
