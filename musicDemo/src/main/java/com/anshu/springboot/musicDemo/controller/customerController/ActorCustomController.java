package com.anshu.springboot.musicDemo.controller.customerController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.anshu.springboot.musicDemo.annotation.MapperDto;
import com.anshu.springboot.musicDemo.db.ActorRespo;
import com.anshu.springboot.musicDemo.db.FilmRespo;
import com.anshu.springboot.musicDemo.model.dto.ActorUpdationDTO;
import com.anshu.springboot.musicDemo.model.entity.Actor;
import com.anshu.springboot.musicDemo.model.entity.Film;
import com.anshu.springboot.musicDemo.model.projection.ActorData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/celeb")
public class ActorCustomController {
    
    @Autowired
    private ActorRespo actorRespo;

    @Autowired
    private FilmRespo filmRespo;

    @PostMapping(path = "/updateFilms/{id}")
    public ResponseEntity<?> saveFilmsOnActor(@PathVariable int id, @RequestBody List<Film> films, Principal principal) {
        System.out.println("Principal:: " + principal.getName());
        try {
            Optional<Actor> optActor = actorRespo.findActorById(id);
            Actor actor = null;
            if(optActor.isPresent())
                actor = optActor.get();
            if(actor != null) {
            List<Film> dbFilms = filmRespo.saveAll(films);
            for(Film film: dbFilms)
                film.updateActors(new ArrayList<>(List.of(actor)));
            filmRespo.saveAll(dbFilms);
            return ResponseEntity.ok(new HashMap<>(Map.of("success", true)));
        } else 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<>(Map.of("success", false)));
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entity Not Found", ex);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCelebList(@RequestParam int page, @RequestParam int size) {
        try {
        Pageable pageable = PageRequest.of(page, size);
          Page<ActorData> actorsItr =  actorRespo.findAllProjectedBy(pageable);
          return ResponseEntity.ok(new HashMap<>(Map.of("list", actorsItr.getContent())));
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entity Not Found", ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCelebListById(@PathVariable int id) {
        try {
            ActorData actorData = actorRespo.findActorProjectedById(id);
            return ResponseEntity.ok(new HashMap<>(Map.of("actor", actorData)));
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entity Not Found", ex);
        }
    }

    @PostMapping("/saveOrAll")
    public ResponseEntity<?> saveCelebEntityOrAll(@RequestBody List<Actor> actors) {
        try {
            List<Actor> dbActor = actorRespo.saveAll(actors);
            if(dbActor.size() !=0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<>(Map.of("success", true)));
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<>(Map.of("success", false)));
        }catch(Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Saved", e);
        }
    }

    @PatchMapping("/updateOrAll")
    public ResponseEntity<?> updateCelebOrAll(@MapperDto(type = ActorUpdationDTO.class) Actor actor) {
        try {
            System.out.println(actor.toString());
            Actor updatedDbActor = actorRespo.save(actor);
            if(updatedDbActor != null) 
                return ResponseEntity.ok(new HashMap<>(Map.of("message", "Updated")));
            else
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new HashMap<>(Map.of("message", "Cannot Update")));
        }catch(Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Saved", e);
        }
    }
}
