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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


public class ServicioCrearVentaTest {


    private RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);;
    private ServicioActualizarArticulo servicioActualizarArticulo = Mockito.mock(ServicioActualizarArticulo.class);
    private DaoArticulo daoArticulo = Mockito.mock(DaoArticulo.class);
    private DaoUsuario daoUsuario = Mockito.mock(DaoUsuario.class);


    @Captor
    ArgumentCaptor<Venta> ventaArgumentCaptor;


    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del articulo")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelArticulo() {
        // arrange
        Venta venta = new VentaTestDataBuilder().conIdArticulo(1L).conIdUsuario(1L).conUnidadVenta(6L).build();
        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(true);
        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(false);
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("led Amarillo").conUnidades(5L).conPrecio(1000F).build();

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVenta.ejecutar(venta), ExcepcionValorInvalido.class,"El articulo no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del usuario")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelUsuario() {

        // arrange
        Venta venta = new VentaTestDataBuilder().conIdArticulo(1L).conIdUsuario(1L).conUnidadVenta(6L).build();
        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(false);
        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(true);
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("led Amarillo").conUnidades(5L).conPrecio(1000F).build();

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVenta.ejecutar(venta), ExcepcionValorInvalido.class,"El usuario no existe en el sistema");
    }





    @Test
    @DisplayName("Deberia lanzar excepcion de no hay inventario del articulo")
    void deberiaLanzarExcepcionNoExisteInventariDelArticulo(){
        // arrange
        Venta venta = new VentaTestDataBuilder().conIdArticulo(1L).conIdUsuario(1L).conUnidadVenta(6L).build();
        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(true);
        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(true);
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("led Amarillo").conUnidades(5L).conPrecio(1000F).build();

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVenta.ejecutar(venta), ExcepcionValorInvalido.class,"No hay inventario del articulo");
    }


    @Test
    @DisplayName("Deberia Crear la venta de manera correcta dia entre semana")
    void deberiaCrearLaVentaDeManeraCorrectaDiaEntreSemana(){
        // arrange
        Venta venta =  spy(new Venta());
        venta.setIdArticulo(1L);
        venta.setIdUsuario(1L);
        venta.setUnidadVenta(5L);

        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(true);
        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(true);
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("led Amarillo").conUnidades(10L).conPrecio(1000F).build();
        LocalDateTime fechaViernes =LocalDateTime.parse("2022-02-18T11:25");

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);
        Mockito.when(venta.obtenerFecha(  )).thenReturn(fechaViernes);

        // act
        Long idVenta = servicioCrearVenta.ejecutar(venta);
        //- assert
        assertEquals(1L,idVenta);
        verify(repositorioVenta, Mockito.times(1)).crear(venta);
        ventaArgumentCaptor =ArgumentCaptor.forClass(Venta.class);
        verify(repositorioVenta).crear(ventaArgumentCaptor.capture());

        assertEquals(5000, ventaArgumentCaptor.getValue().getTotalVenta());
        assertEquals("venta sin descuento", ventaArgumentCaptor.getValue().getDetalleVentaArticulo());
        assertEquals(articulo.getPrecio(), ventaArgumentCaptor.getValue().getPrecioUnidad());
    }



    @Test
    @DisplayName("Deberia Crear la venta de manera correcta con descuento fin de semana")
    void deberiaCrearLaVentaDeManeraCorrectaConDescuentoFinDeSemana(){
        // arrange
        Venta venta =  spy(new Venta());
        venta.setIdArticulo(1L);
        venta.setIdUsuario(1L);
        venta.setUnidadVenta(15L);

        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(true);
        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(true);

        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).conNombreArticulo("led Amarillo").conPrecio(1000F).conUnidades(19L).build();
        LocalDateTime fechaSabado =LocalDateTime.parse("2022-02-19T11:25");

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);
        Mockito.when(venta.obtenerFecha(  )).thenReturn(fechaSabado);

        // act
        Long idVenta = servicioCrearVenta.ejecutar(venta);
        //- assert
        assertEquals(1L,idVenta);
        verify(repositorioVenta, Mockito.times(1)).crear(venta);

        ventaArgumentCaptor =ArgumentCaptor.forClass(Venta.class);
        verify(repositorioVenta).crear(ventaArgumentCaptor.capture());

        assertEquals(13500, ventaArgumentCaptor.getValue().getTotalVenta());
        assertEquals("venta con descuento", ventaArgumentCaptor.getValue().getDetalleVentaArticulo());
        assertEquals(articulo.getPrecio(), ventaArgumentCaptor.getValue().getPrecioUnidad());

    }
}
