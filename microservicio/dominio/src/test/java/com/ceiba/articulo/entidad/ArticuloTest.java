package com.ceiba.articulo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.servicio.testdatabuilder.ArticuloTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticuloTest {

    @Test
    @DisplayName("Deberia crear correctamente el articulo")
    void deberiaCrearCorrectamenteElUsusuario() {
        //act
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("diodo led").conUnidades(10L).conPrecio(150F).build();
        //assert
        assertEquals(1, articulo.getIdArticulo());
        assertEquals("diodo led", articulo.getNombreArticulo());
        assertEquals(10L, articulo.getUnidades());
        assertEquals(150F, articulo.getPrecio());
    }

    @Test
    void deberiaFallarSinNombreDeArticulo(){

        //Arrange
        ArticuloTestDataBuilder articuloTestDataBuilder = new ArticuloTestDataBuilder().conNombreArticulo(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    articuloTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre del articulo");
    }

    @Test
    void deberiaFallarSinCantidad(){

        //Arrange
        ArticuloTestDataBuilder articuloTestDataBuilder = new ArticuloTestDataBuilder().conUnidades(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    articuloTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la cantidad de articulos");
    }

    @Test
    void deberiaFallarSinPrecio(){

        //Arrange
        ArticuloTestDataBuilder articuloTestDataBuilder = new ArticuloTestDataBuilder().conPrecio(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    articuloTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el precio del articulo");
    }

}
