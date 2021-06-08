package com.adincuff.WellStable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping("/{id}")
    public ResponseEntity<Horse> updateHorse(@PathVariable("id") Long id, @Valid @RequestBody HorseRequest horseRequest) {
        return ResponseEntity.ok(horseService.updateHorse(id, horseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable("id") Long id) {
        horseService.deleteHorseById(id);
        return ResponseEntity.ok().build();
    }

}
