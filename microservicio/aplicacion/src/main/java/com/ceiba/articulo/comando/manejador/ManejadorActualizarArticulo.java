package com.ceiba.articulo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.ComandoArticulo;
import com.ceiba.articulo.comando.fabrica.FabricaArticulo;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarArticulo implements ManejadorComandoRespuesta<ComandoArticulo, ComandoRespuesta<Long>> {

    private final FabricaArticulo fabricaArticulo;
    private final ServicioActualizarArticulo servicioActualizarArticulo;

    public ManejadorActualizarArticulo(FabricaArticulo fabricaArticulo, ServicioActualizarArticulo servicioActualizarArticulo) {
        this.fabricaArticulo = fabricaArticulo;
        this.servicioActualizarArticulo = servicioActualizarArticulo;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoArticulo comandoArticulo) {
        Articulo articulo = this.fabricaArticulo.crear(comandoArticulo);
       return new ComandoRespuesta<>(this.servicioActualizarArticulo.ejecutar( articulo ).longValue());
    }

}
