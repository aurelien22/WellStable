package com.adincuff.WellStable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/horses")
public class HorseController {

    @Autowired
    private HorseService horseService;

    @GetMapping
    public ResponseEntity<List<Horse>> getAllHorses() {
        return ResponseEntity.ok(horseService.getAllHorses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horse> getHorseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(horseService.getHorseById(id));
    }

}
