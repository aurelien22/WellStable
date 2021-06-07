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

        Horse horse1 = new Horse("2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris", new Date(2019/02/26));
        Horse horse2 = new Horse("2500FR19350377F", "19350377F", "jazzman camara", "pur-sang", "hongre", "gris", new Date(2019/02/23));

        horseRepository.save(horse1);
        horseRepository.save(horse2);

    }
}
