package com.ceiba.articulo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarArticuloTest {

    @Test
    @DisplayName("Deberia eliminar el articulo llamando al repositorio")
    void deberiaEliminarElArticuloLlamandoAlRepositorio() {
        RepositorioArticulo repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioEliminarArticulo servicioEliminarArticulo = new ServicioEliminarArticulo(repositorioArticulo);

        servicioEliminarArticulo.ejecutar(1l);

        Mockito.verify(repositorioArticulo, Mockito.times(1)).eliminar(1l);

    }

    @Test
    @DisplayName("Deberia fallar al eliminar el articulo")
    void deberiaFallarAlEliminarElArticulo() {
        RepositorioArticulo repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioEliminarArticulo servicioEliminarArticulo = new ServicioEliminarArticulo(repositorioArticulo);


        BasePrueba.assertThrows(() -> {
                    servicioEliminarArticulo.ejecutar(1l);
                },
                ExcepcionSinDatos.class, "El artículo no existe en el sistema");

    }


}
