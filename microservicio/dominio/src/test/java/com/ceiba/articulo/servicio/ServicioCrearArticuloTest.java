package com.ceiba.articulo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.testdatabuilder.ArticuloTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearArticuloTest {


    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del articulo")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelArticulo() {
        // arrange
        Articulo articulo = new ArticuloTestDataBuilder().build();
        RepositorioArticulo repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearArticulo servicioCrearArticulo = new ServicioCrearArticulo(repositorioArticulo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearArticulo.ejecutar(articulo), ExcepcionDuplicidad.class,"El artículo ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear el articulo de manera correcta")
    void deberiaCrearElArticuloDeManeraCorrecta() {
        // arrange
        Articulo articulo = new ArticuloTestDataBuilder().build();
        RepositorioArticulo repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioArticulo.crear(articulo)).thenReturn(10L);
        ServicioCrearArticulo servicioCrearArticulo = new ServicioCrearArticulo(repositorioArticulo);
        // act
        Long idArticulo = servicioCrearArticulo.ejecutar(articulo);
        //- assert
        assertEquals(10L,idArticulo);
        Mockito.verify(repositorioArticulo, Mockito.times(1)).crear(articulo);
    }
}
