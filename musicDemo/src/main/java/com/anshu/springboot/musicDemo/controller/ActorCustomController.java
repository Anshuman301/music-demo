package com.anshu.springboot.musicDemo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.anshu.springboot.musicDemo.db.ActorRespo;
import com.anshu.springboot.musicDemo.db.FilmRespo;
import com.anshu.springboot.musicDemo.model.Actor;
import com.anshu.springboot.musicDemo.model.Film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class ActorCustomController {
    
    @Autowired
    private ActorRespo actorRespo;

    @Autowired
    private FilmRespo filmRespo;

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(path = "actors/actorsForFilm/{id}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> saveFilmsOnActor(@PathVariable int id, @RequestBody List<Film> films, Principal principal) {
        System.out.println("Principal:: " + principal.getName());
        Optional<Actor> optActor = actorRespo.findById(id);
        List<Actor> actors = new ArrayList<Actor>();
        if(optActor.isPresent())
            actors.add(optActor.get());
        if(actors.size() != 0) {
            List<Film> dbFilms = filmRespo.saveAll(films);
            for(Film film: dbFilms)
                film.updateActors(actors);
            filmRespo.saveAll(dbFilms);
        }
        CollectionModel<Actor> model = CollectionModel.of(actors);
        return ResponseEntity.ok(model);
    }
}
