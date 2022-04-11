package com.ceiba.articulo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.servicio.ServicioEliminarArticulo;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarArticulo implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {

   private final ServicioEliminarArticulo servicioEliminarArticulo;

    public ManejadorEliminarArticulo(ServicioEliminarArticulo servicioEliminarArticulo) {
        this.servicioEliminarArticulo = servicioEliminarArticulo;
    }

    public ComandoRespuesta<Long> ejecutar(Long idArticulo) {
        return new ComandoRespuesta<Long>(this.servicioEliminarArticulo.ejecutar(idArticulo).longValue());
    }

}
