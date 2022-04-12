package com.ceiba.venta.servicio;

import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import com.ceiba.venta.servicio.testdatabuilder.VentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearVentaTest {

    @Test
    @DisplayName("Deberia Crear la venta de manera correcta")
    void deberiaCrearLaVentaDeManeraCorrecta(){
        // arrange
        Venta venta = new VentaTestDataBuilder().build();
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        Mockito.when(repositorioVenta.crear(venta)).thenReturn(10L);
        ServicioCrearVenta servicioCrearVenta = new ServicioCrearVenta(repositorioVenta);

        //act
        Long idVenta = servicioCrearVenta.ejecutar(venta);

        //asert
        assertEquals(10L,idVenta);
        Mockito.verify(repositorioVenta, Mockito.times(1)).crear(venta);
    }
}
