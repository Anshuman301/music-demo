package com.anshu.springboot.musicDemo.db;

import com.anshu.springboot.musicDemo.model.entity.Film;

import org.springframework.data.jpa.repository.JpaRepository;
//  @RepositoryRestResource(excerptProjection = FilmData.class)
public interface FilmRespo extends JpaRepository<Film, Integer>{
    
}
