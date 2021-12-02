package com.anshu.springboot.musicDemo.model.projection;

import com.anshu.springboot.musicDemo.model.Film;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "filmData", types = {Film.class})
public interface FilmData {
    String getTitle();
    String getDesc();
}
