package com.ceiba.articulo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.articulo.ComandoArticulo;
import com.ceiba.articulo.servicio.testdatabuilder.ComandoArticuloTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorArticulo.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorArticuloTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear un articulo")
    void deberiaCrearUnArticulo() throws Exception{
        // arrange
        ComandoArticulo articulo = new ComandoArticuloTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/articulos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articulo)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("Deberia actualizar un articulo")
    void deberiaActualizarUnArticulo() throws Exception{
        // arrange
        Long id = 1L;
        ComandoArticulo articulo = new ComandoArticuloTestDataBuilder().build();
        // act - assert
        mocMvc.perform(put("/articulos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articulo)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia actualizar un articulo devolviendo el id del articulo")
    void deberiaActualizarUnArticuloDevolviendoElIdDelArticulo() throws Exception{
        // arrange
        Long id = 1L;
        ComandoArticulo articulo = new ComandoArticuloTestDataBuilder().build();
        // act - assert
        mocMvc.perform(put("/articulos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articulo)))
                .andExpect(content().json("{'valor': 1}"))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Deberia eliminar un articulo")
    void deberiaEliminarUnArticulo() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/articulos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/articulos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }


    @Test
    @DisplayName("Deberia eliminar un articulo retornando el id del articulo")
    void deberiaEliminarUnArticuloRetornandoElIdDelArticulo() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/articulos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'valor': 1}"))
                .andExpect(status().isOk());

        mocMvc.perform(get("/articulos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
