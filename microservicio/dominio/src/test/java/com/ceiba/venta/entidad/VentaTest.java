package com.ceiba.venta.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.servicio.ServicioCrearVenta;
import com.ceiba.venta.servicio.testdatabuilder.VentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

public class VentaTest {




    @Test
    @DisplayName("Deberia fallar sin id de articulo")
    void deberiaFallarSinIdDeArticulo(){

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conIdArticulo(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el id del artículo a comprar");
    }

    @Test
    @DisplayName("Deberia fallar sin id de usuario")
    void deberiaFallarSinIdDeUsuario(){

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conIdUsuario(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el id del usuario");
    }

    @Test
    @DisplayName("Deberia fallar sin cantidad articulos")
    void deberiaFallarSinCantidadArticulos(){

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conUnidadVenta(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la cantidad de artículos a comprar");
    }

    @Test
    @DisplayName("Deberia retornar true si el precio del articulo es mayor o False si el precio del articulo es menor")
    void deberiaRetornarTrueSIPrecioLibroMenorOrFalseSiPrecioLibroMayor() {
        // arrange
        LocalDateTime fecha = LocalDateTime.now();
        String detalleVenta = "sin descuento";

        Venta venta = new VentaTestDataBuilder().conId(1L).build();

        // act - assert
        assertEquals( true,venta.precioArticuloOferta(100000F));
        assertEquals( false,venta.precioArticuloOferta(30000F));
    }

    @Test
    @DisplayName("Deberia retornar true si aplica fecha oferta o False si no aplica la fecha oferta")
    void deberiaRetornarTrueFechaOfertaAplicaOrFalseSiNoAplicaFechaOferta() {
        //arrange
        LocalDateTime fecha=LocalDateTime.parse("2022-02-16T11:25");
        LocalDateTime fechaSabado=LocalDateTime.parse("2022-02-19T11:25");
        LocalDateTime fechaDomingo=LocalDateTime.parse("2022-02-20T11:25");

        Venta venta = new VentaTestDataBuilder().conId(1L).build();

        // act - assert

        assertEquals( true,venta.validarDiasEntreSemana(fecha));
        assertEquals( false,venta.validarDiasEntreSemana(fechaSabado));
        assertEquals( false,venta.validarDiasEntreSemana(fechaDomingo));
    }

    @Test
    @DisplayName("Deberia retornar true si aplica la condicion cantidad, hora 5PM a 10PM o False si no esta en el rango de la hora")
    void deberiaRetornarTrueSiCantidadRangoHoraOrFalseSiNotieneCantidadMinRangoHora() {
        //arrange
        LocalTime horaActualNoAplica=LocalTime.parse("14:00:00.000");
        LocalTime horaActualAplica=LocalTime.parse("19:00:00.000");

        LocalTime horaInicial=LocalTime.parse("17:00:00.000");
        LocalTime horaFinal=LocalTime.parse("10:00:00.000");
        Venta venta = new VentaTestDataBuilder().conId(1L).build();
        Boolean cumplePrecio = true;
        Boolean cumplePrecioNoAplica = false;
        // act - assert
        venta.setUnidadVenta(3L);
        assertEquals( false,venta.aplicarOferta(horaActualNoAplica, horaInicial, horaFinal,cumplePrecioNoAplica));

        venta.setUnidadVenta(4L);
        assertEquals( true,venta.aplicarOferta(horaActualAplica, horaInicial, horaFinal,cumplePrecio));

        venta.setUnidadVenta(3L);
        assertEquals(false,venta.aplicarOferta(horaActualNoAplica, horaInicial, horaFinal,cumplePrecioNoAplica));

        venta.setUnidadVenta(3L);
        assertEquals( true,venta.aplicarOferta(horaActualAplica, horaInicial, horaFinal,cumplePrecio));

        venta.setUnidadVenta(1L);
        assertEquals( false,venta.aplicarOferta(horaActualNoAplica, horaInicial, horaFinal,cumplePrecioNoAplica));

        venta.setUnidadVenta(1L);
        assertEquals(false,venta.aplicarOferta(horaActualAplica, horaInicial, horaFinal, cumplePrecioNoAplica));
    }


    @Test
    @DisplayName("Deberia retornar un valor sin descuento ")
    void deberiaRetornarUnValorSinDescuento() {
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

        Venta venta = new VentaTestDataBuilder().conId(1L).build();
        // act - assert
        venta.setUnidadVenta(3L);
        venta.reglaEstaEnRangoOferta( horaActualNoAplica, horaInicial, horaFinal, precio, noCumplePrecio, cumpleDiaOferta);
        assertEquals( 30000F, venta.getTotalVenta());

        venta.setUnidadVenta(4L);
        venta.reglaEstaEnRangoOferta( horaActualAplica, horaInicial, horaFinal, precio, cumplePrecio, cumpleDiaOferta);
        assertEquals( 28000F, venta.getTotalVenta());

        venta.setUnidadVenta(3L);
        venta.reglaEstaEnRangoOferta(horaActualNoAplica, horaInicial, horaFinal,precio,noCumplePrecio,cumpleDiaOferta);
        assertEquals( 30000F, venta.getTotalVenta());

        venta.setUnidadVenta(3L);
        venta.reglaEstaEnRangoOferta(horaActualAplica, horaInicial, horaFinal,precio,cumplePrecio,cumpleDiaOferta);
        assertEquals( 21000F, venta.getTotalVenta());

        venta.setUnidadVenta(1L);
        venta.reglaEstaEnRangoOferta(horaActualNoAplica, horaInicial, horaFinal,precio,noCumplePrecio,noCumpleDiaOferta);
        assertEquals( 10000F, venta.getTotalVenta());

        venta.setUnidadVenta(1L);
        venta.reglaEstaEnRangoOferta(horaActualAplica, horaInicial, horaFinal,precio,cumplePrecio,noCumpleDiaOferta);
        assertEquals(10000F, venta.getTotalVenta());

    }

    @Test
    @DisplayName("Deberia retornar un valor con descuento si esta dentro del rango de la oferta")
    void deberiaRetornarUnValorConDescuentoSiEstaDentroDelRangoDeLaOferta() {
        //arrange
        LocalTime horaActualNoAplica=LocalTime.parse("14:00:00.000");
        LocalTime horaActualAplica=LocalTime.parse("19:00:00.000");

        LocalTime horaInicial=LocalTime.parse("17:00:00.000");
        LocalTime horaFinal=LocalTime.parse("10:00:00.000");

        Float precio = 50000F;
        Boolean cumpleDiaOferta = true;
        Boolean noCumpleDiaOferta = false;
        Boolean noCumplePrecio = false;
        Boolean cumplePrecio = true;

        Venta venta = new VentaTestDataBuilder().conId(1L).build();
        // act - assert

        venta.setUnidadVenta(4L);
        venta.reglaEstaEnRangoOferta( horaActualAplica, horaInicial, horaFinal, precio, cumplePrecio, cumpleDiaOferta);
        assertEquals( 140000F, venta.getTotalVenta());
        assertEquals( "venta con descuento", venta.getDetalleVentaArticulo());

        venta.setUnidadVenta(3L);
        venta.reglaEstaEnRangoOferta(horaActualAplica, horaInicial, horaFinal,precio,cumplePrecio,cumpleDiaOferta);
        assertEquals( 105000, venta.getTotalVenta());
        assertEquals( "venta con descuento", venta.getDetalleVentaArticulo());

    }

    @Test
    @DisplayName("Deberia retornar un valor con el descuento del fin de semana")
    void deberiaRetornarUnValorConElDescuentoDelFinDeSemana() {
        //arrange
        Venta venta = new VentaTestDataBuilder().conUnidadVenta(10L).conId(1L).build();
        Float precioArticulo = 1000F;
        Boolean DiaEntreSemanaInvalido = false;
        Float ventaConDescuento = 9000F;
        // act - assert
        venta.aplicarOfertaFinDeSemana(10L,precioArticulo,DiaEntreSemanaInvalido);
        assertEquals(ventaConDescuento,venta.getTotalVenta());
        assertEquals("venta con descuento", venta.getDetalleVentaArticulo());

    }

    @Test
    @DisplayName("Deberia retornar un valor sin el descuento del fin de semana")
    void deberiaRetornarUnValorSinElDescuentoDelFinDeSemana() {
        //arrange
        Venta venta = new VentaTestDataBuilder().conUnidadVenta(9L).conId(1L).build();
        Float precioArticulo = 1000F;
        Boolean DiaEntreSemanaValido = true;
        Float ventaSinDescuento = 9000F;

        // act - assert
        venta.aplicarOfertaFinDeSemana(2L,precioArticulo,DiaEntreSemanaValido);

        assertEquals(ventaSinDescuento, venta.getTotalVenta());
        assertEquals("venta sin descuento", venta.getDetalleVentaArticulo());

    }

    @Test
    @DisplayName("Deberia calcular oferta entre semana")
    void deberiaCalcularOfertaEntreSemana() {
        // arrange
        Venta venta =  spy(new Venta());
        venta.setIdArticulo(1L);
        venta.setIdUsuario(1L);
        venta.setUnidadVenta(5L);
        Float precioArticulo = 50000F;

        LocalDateTime fechaViernes =LocalDateTime.parse("2022-02-18T11:25");
        Mockito.when(venta.obtenerFecha(  )).thenReturn(fechaViernes);

        // act - assert
        boolean cumplePrecio = venta.precioArticuloOferta(precioArticulo);

        venta.calcularOferta(precioArticulo,cumplePrecio);

        assertEquals(5,venta.getUnidadVenta());
        assertEquals(50000, venta.getPrecioUnidad());
        assertEquals(250000, venta.getTotalVenta());
        assertEquals("venta sin descuento", venta.getDetalleVentaArticulo());
        assertEquals(fechaViernes, venta.getFechaVentaArticulo());
    }

    @Test
    @DisplayName("Deberia calcular oferta fin de semana")
    void deberiaCalcularOfertaFinDeSemana() {
        // arrange
        Venta venta =  spy(new Venta());
        venta.setIdArticulo(1L);
        venta.setIdUsuario(1L);
        venta.setUnidadVenta(5L);
        Float precioArticulo = 1000F;

        LocalDateTime fechaViernes =LocalDateTime.parse("2022-02-19T11:25");
        Mockito.when(venta.obtenerFecha(  )).thenReturn(fechaViernes);

        // act - assert
        boolean cumplePrecio = venta.precioArticuloOferta(precioArticulo);

        venta.calcularOferta(precioArticulo,cumplePrecio);

        assertEquals(5,venta.getUnidadVenta());
        assertEquals(1000, venta.getPrecioUnidad());
        assertEquals(5000, venta.getTotalVenta());
        assertEquals("venta sin descuento", venta.getDetalleVentaArticulo());
        assertEquals(fechaViernes, venta.getFechaVentaArticulo());
    }



    @Test
    @DisplayName("Deberia crear correctamente la venta")
    void deberiaCrearCorrectamenteLaVenta() {
        // arrange
        LocalDateTime fecha = LocalDateTime.now();
        String detalleVenta = "sin descuento";
        //act
        Venta venta = new VentaTestDataBuilder().conId(1L).conIdArticulo(1L)
                .conIdUsuario(1L).conUnidadVenta(5L).conPrecioUnidad(500F).conTotalVenta(2500F)
                .conDetalleVenta(detalleVenta).conFechaVentaArticulo(fecha).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(1, venta.getIdArticulo());
        assertEquals(1, venta.getIdUsuario());
        assertEquals(5L, venta.getUnidadVenta());
        assertEquals(500F, venta.getPrecioUnidad());
        assertEquals(2500F, venta.getTotalVenta());
        assertEquals(detalleVenta, venta.getDetalleVentaArticulo());
        assertEquals(fecha, venta.getFechaVentaArticulo());
    }
}
