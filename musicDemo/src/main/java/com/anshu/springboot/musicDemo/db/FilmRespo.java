package com.anshu.springboot.musicDemo.db;

import java.util.List;

import com.anshu.springboot.musicDemo.model.entity.Film;
import com.anshu.springboot.musicDemo.model.projection.FilmData;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface FilmRespo extends Repository<Film, Integer>{
    
    public List<Film> findByDesc(@Param("desc") String desc);
    public List<FilmData> findAllProjectedBy();
    public List<Film> saveAll(Iterable<Film> films);
}
