package com.adincuff.WellStable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HorseInitializer implements CommandLineRunner {

    @Autowired
    private HorseRepository horseRepository;

    @Override
    public void run(String... args) throws Exception {

        Horse horse1 = new Horse();
        horse1.setUeln("2500FR19350378E");
        horse1.setSire("19350378E");
        horse1.setName("jacasse camara");
        horse1.setRace("pur-sang");
        horse1.setGenre("hongre");
        horse1.setColor("gris");

        Horse horse2 = new Horse();
        horse2.setUeln("2500FR19350377F");
        horse2.setSire("19350377F");
        horse2.setName("jazzman camara");
        horse2.setRace("pur-sang");
        horse2.setGenre("hongre");
        horse2.setColor("gris");

        horseRepository.save(horse1);
        horseRepository.save(horse2);

    }
}
