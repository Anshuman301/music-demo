package com.anshu.springboot.musicDemo.model.projection;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface ActorData {
    int getId();
    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();
    List<FilmData> getFilms();
}
