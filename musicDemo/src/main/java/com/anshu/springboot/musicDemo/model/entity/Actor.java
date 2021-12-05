package com.anshu.springboot.musicDemo.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name = "actor") @NoArgsConstructor @RequiredArgsConstructor  @ToString @Getter @Setter(value = AccessLevel.PUBLIC)
public class Actor {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "actor_id")
    private int id;
    @Column(name = "first_name") @NonNull private String firstName;
    @Column(name="last_name") @NonNull private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name="actor_id"), inverseJoinColumns = @JoinColumn(name="film_id"))
    @ToString.Exclude @Setter @Getter
    private List<Film> films;

    public void updateFilms(List<Film> newFilms) {
        List<Film> prevFilms = this.films;
        for(Film film : newFilms)
            prevFilms.add(film);
        this.films = prevFilms;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
