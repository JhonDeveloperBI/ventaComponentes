package com.ceiba.articulo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.servicio.testdatabuilder.ArticuloTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorMayorACero;
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
        assertEquals(1, articulo.getId());
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

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando la cantidad es igual a cero")
    void deberiaLanzarUnaExcepcionCuandoLaCantidadEsIgualACero() {

        //Arrange
        ArticuloTestDataBuilder articuloTestDataBuilder = new ArticuloTestDataBuilder().conNombreArticulo("led").conPrecio(100F).conUnidades(0L).conId(1L);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    articuloTestDataBuilder.build();
                },
                ExcepcionValorMayorACero.class, "La cantidad de articulos debe ser mayor a 0");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando el precio es igual a cero")
    void deberiaLanzarUnaExcepcionCuandoElPrecioEsIgualACero() {

        //Arrange
        ArticuloTestDataBuilder articuloTestDataBuilder = new ArticuloTestDataBuilder().conNombreArticulo("led").conPrecio(0F).conUnidades(10L).conId(1L);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    articuloTestDataBuilder.build();
                },
                ExcepcionValorMayorACero.class, "El precio debe ser mayor a 0");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando el precio es igual a cero y la cantidad igual a 0")
    void deberiaLanzarUnaExcepcionCuandoElPrecioEsIgualACeroYLaCantidadIgualACero() {

        //Arrange
        ArticuloTestDataBuilder articuloTestDataBuilder = new ArticuloTestDataBuilder().conNombreArticulo("led").conPrecio(0F).conUnidades(0L).conId(1L);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    articuloTestDataBuilder.build();
                },
                ExcepcionValorMayorACero.class, "La cantidad de articulos debe ser mayor a 0 y el precio deber ser mayor a 0");
    }


    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide inventario del articulo")
    void deberiaLanzarUnaExepcionCuandoSeValideInventarioDelArticulo() {
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("diodo led").conUnidades(10L).conPrecio(150F).build();
        // act - assert
        BasePrueba.assertThrows(() -> articulo.validarInventarioArticulo(10L, 15L), ExcepcionValorInvalido.class,"No hay inventario del articulo");
    }


    @Test
    @DisplayName("Deberia existir inventario articulo")
    void deberiaExistirInventarioArticulo() {
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("diodo led").conUnidades(10L).conPrecio(150F).build();
        // act - assert
       articulo.validarInventarioArticulo(10L,5L);

       assertEquals(5L, articulo.getUnidades());
    }

    @Test
    @DisplayName("Deberia retornar el valor total del articulo")
    void deberiaRetornarElValorTotalDelArticulo() {
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("diodo led").conUnidades(10L).conPrecio(150F).build();
        // act - assert
        articulo.validarInventarioArticulo(10L, 8L);
        assertEquals(2L, articulo.getUnidades());
    }

    @Test
    @DisplayName("Deberia el articulo quedar con 0 unidades")
    void deberiaElArticuloQuedarConCeroUnidades() {
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("diodo led").conUnidades(10L).conPrecio(150F).build();
        // act - assert
        articulo.validarInventarioArticulo(10L, 10L);
        assertEquals(0L, articulo.getUnidades());
    }


}
