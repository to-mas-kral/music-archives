package com.tom.musicarchives.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "released")
    private LocalDate released;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
