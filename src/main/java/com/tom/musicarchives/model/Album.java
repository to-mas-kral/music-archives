package com.tom.musicarchives.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Band getBand() {
        return band;
    }

    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    public List<Song> getSongs() {
        return songs;
    }

    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OneToMany(mappedBy = "album")
    @OrderBy("orderInAlbum")
    private List<Song> songs;

    @NotBlank
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

    public void setBand(Band band) {
        this.band = band;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
