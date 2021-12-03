package com.anshu.springboot.musicDemo.db;

import java.util.List;

import com.anshu.springboot.musicDemo.model.entity.Film;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FilmRespo extends CrudRepository<Film, Integer>{
    
    public List<Film> findByDesc(@Param("desc") String desc);
}
