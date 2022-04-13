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
import java.time.LocalTime;

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
    @DisplayName("Deberia lanzar una exepcion cuando se valide inventario del articulo")
    void deberiaLanzarUnaExepcionCuandoSeValideInventarioDelArticulo() {
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta, servicioActualizarArticulo, daoArticulo, daoUsuario));
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVenta.validarInventarioArticulo(-1L), ExcepcionValorInvalido.class,"No hay inventario del articulo");
    }


    @Test
    @DisplayName("Deberia retornar true si el precio del articulo es mayor o False si el precio del articulo es menor")
    void deberiaRetornarTrueSIPrecioLibroMenorOrFalseSiPrecioLibroMayor() {
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta, servicioActualizarArticulo, daoArticulo, daoUsuario));
        // act - assert
        assertEquals( true,servicioCrearVenta.precioArticuloOferta(100000F));
        assertEquals( false,servicioCrearVenta.precioArticuloOferta(30000F));
    }

    @Test
    @DisplayName("Deberia retornar true si aplica la condicion cantidad, hora 5PM a 10PM o False si no esta en el rango de la hora")
    void deberiaRetornarTrueSiCantidadRangoHoraOrFalseSiNotieneCantidadMinRangoHora() {
        //arrange
        LocalTime horaActualNoAplica=LocalTime.parse("14:00:00.000");
        LocalTime horaActualAplica=LocalTime.parse("19:00:00.000");

        LocalTime horaInicial=LocalTime.parse("17:00:00.000");
        LocalTime horaFinal=LocalTime.parse("10:00:00.000");

        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta, servicioActualizarArticulo, daoArticulo, daoUsuario));
        // act - assert

        assertEquals( false,servicioCrearVenta.aplicarOferta(3L,horaActualNoAplica, horaInicial, horaFinal));
        assertEquals( true,servicioCrearVenta.aplicarOferta(4L,horaActualAplica, horaInicial, horaFinal));
        assertEquals(false,servicioCrearVenta.aplicarOferta(3L,horaActualNoAplica, horaInicial, horaFinal));
        assertEquals( true,servicioCrearVenta.aplicarOferta(3L,horaActualAplica, horaInicial, horaFinal));
        assertEquals( false,servicioCrearVenta.aplicarOferta(1L,horaActualNoAplica, horaInicial, horaFinal));
        assertEquals(false,servicioCrearVenta.aplicarOferta(1L,horaActualAplica, horaInicial, horaFinal));
    }

    @Test
    @DisplayName("Deberia retornar un valor con descuento si esta dentro del rango de la oferta")
    void deberiaRetornarUnValorConDescuentoSiEstaDentroDelRangoDeLaOferta() {
        //arrange
        LocalTime horaActualNoAplica=LocalTime.parse("14:00:00.000");
        LocalTime horaActualAplica=LocalTime.parse("19:00:00.000");

        LocalTime horaInicial=LocalTime.parse("17:00:00.000");
        LocalTime horaFinal=LocalTime.parse("10:00:00.000");
        Float precio = 10000F;
        Boolean cumpleDiaOferta = true;
        Boolean noCumpleDiaOferta = false;
        Boolean noCumplePrecio = false;
        Boolean cumplePrecio = true;

        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta, servicioActualizarArticulo, daoArticulo, daoUsuario));
        // act - assert

        assertEquals( 30000F,servicioCrearVenta.reglaEstaEnRangoOferta(3L,horaActualNoAplica, horaInicial, horaFinal,precio,noCumplePrecio, cumpleDiaOferta));
        assertEquals( 12000F,servicioCrearVenta.reglaEstaEnRangoOferta(4L,horaActualAplica, horaInicial, horaFinal,precio,cumplePrecio,cumpleDiaOferta));
        assertEquals( 30000F, servicioCrearVenta.reglaEstaEnRangoOferta(3L,horaActualNoAplica, horaInicial, horaFinal,precio,noCumplePrecio,cumpleDiaOferta));
        assertEquals( 9000F,servicioCrearVenta.reglaEstaEnRangoOferta(3L,horaActualAplica, horaInicial, horaFinal,precio,cumplePrecio,cumpleDiaOferta));
        assertEquals( 10000F,servicioCrearVenta.reglaEstaEnRangoOferta(1L,horaActualNoAplica, horaInicial, horaFinal,precio,noCumplePrecio,noCumpleDiaOferta));
        assertEquals(10000F,servicioCrearVenta.reglaEstaEnRangoOferta(1L,horaActualAplica, horaInicial, horaFinal,precio,cumplePrecio,noCumpleDiaOferta));
    }


    @Test
    @DisplayName("Deberia retornar true si aplica fecha oferta o False si no aplica la fecha oferta")
    void deberiaRetornarTrueFechaOfertaAplicaOrFalseSiNoAplicaFechaOferta() {
        //arrange
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        LocalDateTime fecha=LocalDateTime.parse("2022-02-16T11:25");
        LocalDateTime fechaSabado=LocalDateTime.parse("2022-02-19T11:25");
        LocalDateTime fechaDomingo=LocalDateTime.parse("2022-02-20T11:25");

        // act - assert

        assertEquals( true,servicioCrearVenta.validarDiasEntreSemana(fecha));
        assertEquals( false,servicioCrearVenta.validarDiasEntreSemana(fechaSabado));
        assertEquals( false,servicioCrearVenta.validarDiasEntreSemana(fechaDomingo));
    }

    @Test
    @DisplayName("Deberia retornar un valor con el descuento del fin de semana")
    void deberiaRetornarUnValorConElDescuentoDelFinDeSemana() {
        //arrange
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Long unidadVenta = 10L;
        Float precioArticulo = 1000F;
        Boolean DiaEntreSemanaInvalido = false;
        Float ventaConDescuento = 9000F;
        // act - assert
        assertEquals(ventaConDescuento,servicioCrearVenta.aplicarOfertaFinDeSemana(unidadVenta,precioArticulo,DiaEntreSemanaInvalido));

    }

    @Test
    @DisplayName("Deberia retornar un valor sin el descuento del fin de semana")
    void deberiaRetornarUnValorSinElDescuentoDelFinDeSemana() {
        //arrange
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Long unidadVentaInvalida = 9L;
        Float precioArticulo = 1000F;
        Boolean DiaEntreSemanaValido = true;
        Float ventaSinDescuento = 10000F;
        // act - assert

        assertEquals(10000F,ventaSinDescuento,servicioCrearVenta.aplicarOfertaFinDeSemana(unidadVentaInvalida,precioArticulo,DiaEntreSemanaValido));

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
        Mockito.when(servicioCrearVenta.validarDiasEntreSemana( fechaVenta )).thenReturn(true);
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
        LocalDateTime fechaVenta = LocalDateTime.now();

        Mockito.when(repositorioVenta.crear(venta)).thenReturn(1L);
        ServicioCrearVenta servicioCrearVenta =  spy(new ServicioCrearVenta(repositorioVenta,  servicioActualizarArticulo, daoArticulo, daoUsuario));
        Mockito.when(daoArticulo.obtenerArticuloPorId(1L)).thenReturn(articulo);
        Mockito.when(servicioCrearVenta.validarDiasEntreSemana( fechaVenta )).thenReturn(false);
        // act
        Long idVenta = servicioCrearVenta.ejecutar(venta);
        //- assert
        assertEquals(1L,idVenta);
        Mockito.verify(repositorioVenta, Mockito.times(1)).crear(venta);
    }
}
