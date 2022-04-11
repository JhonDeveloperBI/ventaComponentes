package com.ceiba.articulo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.ComandoArticulo;
import com.ceiba.articulo.comando.fabrica.FabricaArticulo;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.servicio.ServicioCrearArticulo;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearArticulo implements ManejadorComandoRespuesta<ComandoArticulo, ComandoRespuesta<Long>> {

    private final FabricaArticulo fabricaArticulo;
    private final ServicioCrearArticulo servicioCrearArticulo;

    public ManejadorCrearArticulo(FabricaArticulo fabricaArticulo, ServicioCrearArticulo servicioCrearArticulo) {
        this.fabricaArticulo = fabricaArticulo;
        this.servicioCrearArticulo = servicioCrearArticulo;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoArticulo comandoArticulo) {
        Articulo articulo = this.fabricaArticulo.crear(comandoArticulo);
        return new ComandoRespuesta<>(this.servicioCrearArticulo.ejecutar(articulo));
    }
}
