package com.anshu.springboot.musicDemo.db;

import java.util.List;

import com.anshu.springboot.musicDemo.model.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "celeb", collectionResourceRel = "celebList", itemResourceRel = "celeb")
public interface ActorRespo extends CrudRepository<Actor, Integer>{
    public List<Actor> findByFirstName(String fName);

    @RestResource(path = "firstNameAndLastName", rel = "firstNameAndLastName")
    public List<Actor> findByFirstNameAndLastName(@Param("fName") String fName, @Param("lName") String lName);
    @RestResource(exported = false)
    @Override
    default void deleteById(Integer id){}
}
