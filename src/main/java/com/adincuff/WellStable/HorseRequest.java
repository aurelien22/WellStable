package com.adincuff.WellStable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class HorseRequest {

    private String ueln;

    @Size(min = 9, max = 9)
    private String sire;

    @NotEmpty
    @Size(max = 20)
    private String name;

    private String race;

    private String genre;

    private String color;

    public HorseRequest(String ueln, String sire, String name, String race, String genre, String color) {
        this.ueln = ueln;
        this.sire = sire;
        this.name = name;
        this.race = race;
        this.genre = genre;
        this.color = color;
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
}
