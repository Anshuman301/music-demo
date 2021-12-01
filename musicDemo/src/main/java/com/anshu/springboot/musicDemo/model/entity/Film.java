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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String desc;
    @Column(name = "language_id")
    private int langId;

    @ManyToMany
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name="film_id"), inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actors;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "inventory", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "store_id"))
    private List<Store> stores;
    public Film() {}
    
    public Film(String title, String desc, int langId) {
        this.title = title;
        this.desc = desc;
        this.langId = langId;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + langId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Film other = (Film) obj;
        if (desc == null) {
            if (other.desc != null)
                return false;
        } else if (!desc.equals(other.desc))
            return false;
        if (langId != other.langId)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    public int getLangId() {
        return langId;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Actor> getActors() {
        return actors;
    }
    public void updateActors(List<Actor> newActor) {
        List<Actor> prevActor = getActors();
        if(prevActor == null)
            prevActor = new ArrayList<Actor>();
        for(Actor actor : newActor)
            prevActor.add(actor);
        setActors(prevActor);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Store> getStores() {
        return stores;
    }
    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
    @Override
    public String toString() {
        return "Film [desc=" + desc + ", id=" + id + ", title=" + title + "]";
    }
    
}
