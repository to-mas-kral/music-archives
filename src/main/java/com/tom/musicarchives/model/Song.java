package com.tom.musicarchives.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "length")
    private LocalDateTime length;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
