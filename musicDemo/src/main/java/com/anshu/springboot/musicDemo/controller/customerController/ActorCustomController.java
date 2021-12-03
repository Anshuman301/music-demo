package com.anshu.springboot.musicDemo.controller.customerController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.anshu.springboot.musicDemo.db.ActorRespo;
import com.anshu.springboot.musicDemo.db.FilmRespo;
import com.anshu.springboot.musicDemo.model.entity.Actor;
import com.anshu.springboot.musicDemo.model.entity.Film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController("/celeb")
public class ActorCustomController {
    
    @Autowired
    private ActorRespo actorRespo;

    @Autowired
    private FilmRespo filmRespo;

    @PostMapping(path = "/updateFilms/{id}")
    public ResponseEntity<?> saveFilmsOnActor(@PathVariable int id, @RequestBody List<Film> films, Principal principal) {
        System.out.println("Principal:: " + principal.getName());
        try {
            Optional<Actor> optActor = actorRespo.findById(id);
            List<Actor> actors = new ArrayList<Actor>();
            if(optActor.isPresent())
                actors.add(optActor.get());
            if(actors.size() != 0) {
                List<Film> dbFilms = new ArrayList<>();
            for(Film film : films)
                dbFilms.add(filmRespo.save(film));
            for(Film film: dbFilms)
                film.updateActors(actors);
            filmRespo.saveAll(dbFilms);
            return ResponseEntity.ok(new HashMap<>(Map.of("success", true)));
        } else 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<>(Map.of("success", false)));
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entity Not Found", ex);
        }
    }
}
