package com.adincuff.WellStable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorseService {

    @Autowired
    private HorseRepository horseRepository;

    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

    public Horse getHorseById(long id) {

        Optional<Horse> requestHorse = horseRepository.findById(id);

        if (requestHorse.isEmpty()) {
            throw new HorseNotFoundException(String.format("Horse with id: '%s' not found", id));
        }

        return requestHorse.get();

    }
}
