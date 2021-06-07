package com.adincuff.WellStable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HorseController.class)
public class HorseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HorseService horseService;

    @Test
    public void allHorsesEndpointShouldReturnTwoHorses() throws Exception {

        when(horseService.getAllHorses()).thenReturn(List.of(
                createHorse(1L, "2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris", new Date(2019/02/26)),
                createHorse(2L, "2500FR19350377F", "19350377F", "jazzman camara", "pur-sang", "hongre", "gris", new Date(2019/02/23)))
        );

        this.mockMvc.perform(get("/api/horses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("jacasse camara")))
                .andExpect(jsonPath("$[1].name", is("jazzman camara")));

    }

    @Test
    public void getHorseWithIdShouldReturnAHorse() throws Exception {

        when(horseService.getHorseById(1L)).thenReturn(createHorse(1L, "2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris", new Date(2019/02/26)));

        this.mockMvc.perform(get("/api/horses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is("jacasse camara")));

    }

    @Test
    public void getHorseWithUnknownIdShouldReturn404() throws Exception {

        when(horseService.getHorseById(42L)).thenThrow(new HorseNotFoundException("Horse with id '42' not found"));

        this.mockMvc.perform(get("/api/horses/42"))
                .andExpect(status().isNotFound());

    }

    private Horse createHorse(Long id, String ueln, String sire, String name, String race, String genre, String color, Date dateOfBirth) {
        Horse horse = new Horse(ueln, sire, name, race, genre, color, dateOfBirth);
        horse.setId(id);

        return horse;
    }

}
