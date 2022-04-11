package com.ceiba.articulo.servicio.testdatabuilder;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarArticuloTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del articulo")
    void deberiaValidarLaExistenciaPreviaDelArticulo() {
        // arrange
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).build();
        RepositorioArticulo repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarArticulo servicioActualizarArticulo = new ServicioActualizarArticulo(repositorioArticulo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarArticulo.ejecutar(articulo), ExcepcionDuplicidad.class,"El artículo no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Articulo articulo = new ArticuloTestDataBuilder().conId(1L).build();
        RepositorioArticulo repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarArticulo servicioActualizarArticulo = new ServicioActualizarArticulo(repositorioArticulo);
        // act
        servicioActualizarArticulo.ejecutar(articulo);
        //assert
        Mockito.verify(repositorioArticulo,Mockito.times(1)).actualizar(articulo);
    }
}
