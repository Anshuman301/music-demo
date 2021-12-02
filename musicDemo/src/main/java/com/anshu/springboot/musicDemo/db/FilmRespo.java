package com.anshu.springboot.musicDemo.db;

import java.util.List;

import com.anshu.springboot.musicDemo.model.entity.Film;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
 @RepositoryRestResource(path = "cinema", itemResourceRel = "cinema", collectionResourceRel = "cinemaList")
public interface FilmRespo extends CrudRepository<Film, Integer>{
    @RestResource(path = "desc", rel = "desc")
    public List<Film> findByDesc(@Param("desc") String desc);
}
