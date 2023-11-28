package com.tom.musicarchives.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.List;

import java.time.LocalDate;

@Entity
@Table(name = "band")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "founded")
    private LocalDate founded;

    @Column(name = "dissolved")
    private LocalDate dissolved;

    @OneToMany(mappedBy = "band")
    private Set<Album> albums;

    @ManyToMany
    @JoinTable(
            name = "band_members",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> member;

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
