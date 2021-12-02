package com.anshu.springboot.musicDemo.db;

import java.util.List;

import com.anshu.springboot.musicDemo.model.entity.Film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
//  @RepositoryRestResource(excerptProjection = FilmData.class)
public interface FilmRespo extends JpaRepository<Film, Integer>{
    @RestResource(path = "desc", rel = "desc")
    public List<Film> findByDesc(@Param("desc") String desc);
}
