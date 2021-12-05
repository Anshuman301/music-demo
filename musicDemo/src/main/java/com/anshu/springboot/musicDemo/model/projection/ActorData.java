package com.anshu.springboot.musicDemo.model.projection;

import java.util.List;

public interface ActorData {
    int getId();
    String getFirstName();
    String getLastName();
    List<FilmData> getFilms();
}
