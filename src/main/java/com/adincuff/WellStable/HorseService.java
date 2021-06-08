package com.adincuff.WellStable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Horse updateHorse(long id, HorseRequest horseToUpdateRequest) {

        Optional<Horse> horseFromDatabase = horseRepository.findById(id);

        if (horseFromDatabase.isEmpty()) {
            throw new HorseNotFoundException(String.format("Horse with id: '%s' not found", id));
        }

        Horse horseToUpdate = horseFromDatabase.get();

        horseToUpdate.setUeln(horseToUpdateRequest.getUeln());
        horseToUpdate.setSire(horseToUpdateRequest.getSire());
        horseToUpdate.setName(horseToUpdateRequest.getName());
        horseToUpdate.setRace(horseToUpdateRequest.getRace());
        horseToUpdate.setGenre(horseToUpdateRequest.getGenre());
        horseToUpdate.setColor(horseToUpdateRequest.getColor());

        return horseToUpdate;

    }

    public void deleteHorseById(Long id) {
        horseRepository.deleteById(id);
    }
}
