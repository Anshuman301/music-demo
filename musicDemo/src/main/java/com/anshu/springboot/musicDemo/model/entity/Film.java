package com.anshu.springboot.musicDemo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity @Table(name = "film") @NoArgsConstructor @RequiredArgsConstructor @Getter @Setter @ToString
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int id;
    @Column(name = "title") @NonNull private String title;
    @Column(name = "description") @NonNull private String desc;
    @Column(name = "language_id") @NonNull private int langId;

    @ManyToMany @ToString.Exclude
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name="film_id"), inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actors;

    @ManyToMany @ToString.Exclude
    @JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToMany @ToString.Exclude
    @JoinTable(name = "inventory", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "store_id"))
    private List<Store> stores;

    public void updateActors(List<Actor> newActor) {
        List<Actor> prevActor = this.actors;
        if(prevActor == null)
            prevActor = new ArrayList<Actor>();
        for(Actor actor : newActor)
            prevActor.add(actor);
        this.actors = prevActor;
    }
    
}
