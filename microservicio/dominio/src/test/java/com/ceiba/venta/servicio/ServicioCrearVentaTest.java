package com.ceiba.venta.servicio;


import com.ceiba.BasePrueba;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.articulo.servicio.testdatabuilder.ArticuloTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import com.ceiba.venta.servicio.testdatabuilder.VentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;


public class ServicioCrearVentaTest {


    private RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);;
    private ServicioActualizarArticulo servicioActualizarArticulo = Mockito.mock(ServicioActualizarArticulo.class);
    private DaoArticulo daoArticulo = Mockito.mock(DaoArticulo.class);
    private DaoUsuario daoUsuario = Mockito.mock(DaoUsuario.class);


    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del articulo")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelArticulo() {
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta, servicioActualizarArticulo, daoArticulo, daoUsuario));
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVenta.validarExistenciaPreviaArticulo(1L), ExcepcionValorInvalido.class,"El articulo no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del usuario")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelUsuario() {
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta, servicioActualizarArticulo, daoArticulo, daoUsuario));
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVenta.validarExistenciaPreviaUsuario(1L), ExcepcionValorInvalido.class,"El usuario no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear la venta de manera correcta dia entre semana")
    void deberiaCrearLaVentaDeManeraCorrectaDiaEntreSemana(){
        // arrange
        Venta venta = new VentaTestDataBuilder().build();
        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(true);
        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(true);
        Articulo articulo = new ArticuloTestDataBuilder().build();
        LocalDateTime fechaVenta = LocalDateTime.now();

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);
      //  Mockito.when(servicioCrearVenta.validarDiasEntreSemana( fechaVenta )).thenReturn(true);
        // act
        Long idVenta = servicioCrearVenta.ejecutar(venta);
        //- assert
        assertEquals(1L,idVenta);
        Mockito.verify(repositorioVenta, Mockito.times(1)).crear(venta);
    }

    @Test
    @DisplayName("Deberia Crear la venta de manera correcta con descuento fin de semana")
    void deberiaCrearLaVentaDeManeraCorrectaConDescuentoFinDeSemana(){
        // arrange
        Venta venta = new VentaTestDataBuilder().build();
        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(true);
        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(true);
        Articulo articulo = new ArticuloTestDataBuilder().conUnidades(10L).build();
        LocalDateTime fechaSabado =LocalDateTime.parse("2022-02-19T11:25");

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);
        Mockito.when(servicioCrearVenta.obtenerFecha(  )).thenReturn(fechaSabado);
        // act
        Long idVenta = servicioCrearVenta.ejecutar(venta);
        //- assert
        assertEquals(1L,idVenta);
        Mockito.verify(repositorioVenta, Mockito.times(1)).crear(venta);
    }
}
