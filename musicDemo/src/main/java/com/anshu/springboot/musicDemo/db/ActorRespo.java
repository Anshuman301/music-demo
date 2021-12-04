package com.anshu.springboot.musicDemo.db;

import java.util.List;
import java.util.Optional;

import com.anshu.springboot.musicDemo.model.entity.Actor;
import com.anshu.springboot.musicDemo.model.projection.ActorData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface ActorRespo extends Repository<Actor, Integer>{
    public List<Actor> findByFirstName(String fName);

    public List<Actor> findByFirstNameAndLastName(@Param("fName") String fName, @Param("lName") String lName);
    public Page<ActorData> findAllProjectedBy(Pageable p);
    public Optional<Actor> findActorById(int id);
    public ActorData findActorProjectedById(int id);

    public List<Actor> saveAll(Iterable<Actor> actors);
}
