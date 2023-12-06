package com.tom.musicarchives.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.Set;

import java.time.LocalDate;

@Entity
@Table(name = "band")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100)
    @Column(name = "name")
    @NotBlank
    private String name;

    @NotNull
    @Column(name = "founded")
    private LocalDate founded;

    @Column(name = "dissolved")
    private LocalDate dissolved;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "band")
    private Set<Album> albums;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "band_members",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> members;

    @NotBlank
    @Column(name = "genre")
    private String genre;

    @NotBlank
    @Column(name = "country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public LocalDate getDissolved() {
        return dissolved;
    }

    public void setDissolved(LocalDate dissolved) {
        this.dissolved = dissolved;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", founded=" + founded +
                ", dissolved=" + dissolved +
                '}';
    }
}
