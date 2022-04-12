package com.ceiba.venta.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.servicio.testdatabuilder.VentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VentaTest {

    @Test
    @DisplayName("Deberia crear correctamente la venta")
    void deberiaCrearCorrectamenteElUsusuario() {
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
    @DisplayName("Deberia fallar sin precio unidad articulos")
    void deberiaFallarSinPrecioUnidadArticulos(){

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conPrecioUnidad(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el precio unidad del artículo");
    }

    @Test
    @DisplayName("Deberia fallar sin total venta")
    void deberiaFallarSinTotalVenta(){

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conTotalVenta(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el total de venta del artículo");
    }

    @Test
    @DisplayName("Deberia fallar sin detalle venta")
    void deberiaFallarSinDetalleVenta(){

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conDetalleVenta(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el detalle de venta del artículo");
    }

    @Test
    @DisplayName("Deberia fallar sin fecha venta")
    void deberiaFallarSinFechaVenta(){

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conFechaVentaArticulo(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de venta del artículo");
    }

}
