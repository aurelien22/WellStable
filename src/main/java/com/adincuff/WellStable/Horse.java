package com.adincuff.WellStable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Horse {

    @Id
    @GeneratedValue
    private Long id;

    private String ueln;

    @Column(nullable = false)
    private String sire;

    @Column(nullable = false)
    private String name;

    private String race;

    private String genre;

    private String color;

    private Date dateOfBirth;

    public Horse() {
    }

    public Horse(String ueln, String sire, String name, String race, String genre, String color, Date dateOfBirth) {
        this.ueln = ueln;
        this.sire = sire;
        this.name = name;
        this.race = race;
        this.genre = genre;
        this.color = color;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUeln() {
        return ueln;
    }

    public void setUeln(String ueln) {
        this.ueln = ueln;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
