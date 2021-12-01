package com.anshu.springboot.musicDemo.model;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @ManyToMany
    @JoinTable(name = "inventory", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Film> films;
    public Store() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Film> getFilms() {
        return films;
    }
    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
