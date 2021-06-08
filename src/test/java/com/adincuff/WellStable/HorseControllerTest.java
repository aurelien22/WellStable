package com.adincuff.WellStable;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HorseController.class)
public class HorseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HorseService horseService;

    @Captor
    private ArgumentCaptor<HorseRequest> horseRequestArgumentCaptor;

    @Test
    public void allHorsesEndpointShouldReturnTwoHorses() throws Exception {

        when(horseService.getAllHorses()).thenReturn(List.of(
                createHorse(1L, "2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris"),
                createHorse(2L, "2500FR19350377F", "19350377F", "jazzman camara", "pur-sang", "hongre", "gris"))
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

        when(horseService.getHorseById(1L)).thenReturn(createHorse(1L, "2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris"));

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

    @Test
    public void updateHorseWithKnownIdShouldUpdateTheHorse() throws Exception {
        HorseRequest horseRequest = new HorseRequest("2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris");

        when(horseService.updateHorse(eq(1L), horseRequestArgumentCaptor.capture())).thenReturn(createHorse(1L, "2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris"));

        this.mockMvc.perform(put("/api/horses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horseRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.ueln", is("2500FR19350378E")))
                .andExpect(jsonPath("$.sire", is("19350378E")))
                .andExpect(jsonPath("$.name", is("jacasse camara")))
                .andExpect(jsonPath("$.race", is("pur-sang")))
                .andExpect(jsonPath("$.genre", is("hongre")))
                .andExpect(jsonPath("$.color", is("gris")));

        assertThat(horseRequestArgumentCaptor.getValue().getUeln(), is("2500FR19350378E"));
        assertThat(horseRequestArgumentCaptor.getValue().getSire(), is("19350378E"));
        assertThat(horseRequestArgumentCaptor.getValue().getName(), is("jacasse camara"));
        assertThat(horseRequestArgumentCaptor.getValue().getRace(), is("pur-sang"));
        assertThat(horseRequestArgumentCaptor.getValue().getGenre(), is("hongre"));
        assertThat(horseRequestArgumentCaptor.getValue().getColor(), is("gris"));

    }

    @Test
    public void updateHorseWithUnknownIdShouldReturn404() throws Exception {

        HorseRequest horseRequest = new HorseRequest("2500FR19350378E", "19350378E", "jacasse camara", "pur-sang", "hongre", "gris");

        when(horseService.updateHorse(eq(42L), horseRequestArgumentCaptor.capture()))
                .thenThrow(new HorseNotFoundException("The horse with the id '42' was not found"));

        this.mockMvc.perform(put("/api/horse/42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horseRequest)))
                .andExpect(status().isNotFound());

    }

    private Horse createHorse(Long id, String ueln, String sire, String name, String race, String genre, String color) {
        Horse horse = new Horse();
        horse.setId(id);
        horse.setUeln(ueln);
        horse.setSire(sire);
        horse.setName(name);
        horse.setRace(race);
        horse.setGenre(genre);
        horse.setColor(color);

        return horse;
    }
}