package com.ceiba.venta.validaciones;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class VentaValidacionesTest {

    private DaoArticulo daoArticulo = Mockito.mock(DaoArticulo.class);
    private DaoUsuario daoUsuario = Mockito.mock(DaoUsuario.class);

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del articulo")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelArticulo() {
        VentaValidaciones ventaValidaciones =  spy(new VentaValidaciones( daoArticulo, daoUsuario));
        // act - assert
        BasePrueba.assertThrows(() -> ventaValidaciones.validarExistenciaPreviaArticulo(1L), ExcepcionValorInvalido.class,"El articulo no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia existir un articulo")
    void deberiaExistirUnArticulo() {
        VentaValidaciones ventaValidaciones =  spy(new VentaValidaciones( daoArticulo, daoUsuario));

        Mockito.when(daoArticulo.existePorId(1L)).thenReturn(true);
        // act - assert
        ventaValidaciones.validarExistenciaPreviaArticulo(1L);

        verify(ventaValidaciones,Mockito.times(1)).validarExistenciaPreviaArticulo(1L);
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del usuario")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelUsuario() {
        VentaValidaciones ventaValidaciones =  spy(new VentaValidaciones( daoArticulo, daoUsuario));
        // act - assert
        BasePrueba.assertThrows(() -> ventaValidaciones.validarExistenciaPreviaUsuario(1L), ExcepcionValorInvalido.class,"El usuario no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia existir un usuario")
    void deberiaExistUnUsuario() {
        VentaValidaciones ventaValidaciones =  spy(new VentaValidaciones( daoArticulo, daoUsuario));
        Mockito.when(daoUsuario.existePorId(1L)).thenReturn(true);
        ventaValidaciones.validarExistenciaPreviaUsuario(1L);

        // act - assert
        verify(ventaValidaciones,Mockito.times(1)).validarExistenciaPreviaUsuario(1L);
    }

}
