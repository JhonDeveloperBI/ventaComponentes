package com.ceiba.articulo.servicio;

import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
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
}
