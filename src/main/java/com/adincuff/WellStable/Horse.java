package com.adincuff.WellStable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Horse {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String ueln;

    @Column(nullable = false)
    private String sire;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String race;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String color;

    /*
    public Horse(String ueln, String sire, String name, String race, String genre, String color) {
        this.ueln = ueln;
        this.sire = sire;
        this.name = name;
        this.race = race;
        this.genre = genre;
        this.color = color;
    }

    public Horse(Long id, String ueln, String sire, String name, String race, String genre, String color) {
        this.id = id;
        this.ueln = ueln;
        this.sire = sire;
        this.name = name;
        this.race = race;
        this.genre = genre;
        this.color = color;
    }

     */
}
