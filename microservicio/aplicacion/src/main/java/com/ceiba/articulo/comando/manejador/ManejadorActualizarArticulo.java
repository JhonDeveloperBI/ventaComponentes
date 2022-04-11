package com.ceiba.articulo.comando.manejador;

import com.ceiba.articulo.ComandoArticulo;
import com.ceiba.articulo.comando.fabrica.FabricaArticulo;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarArticulo implements ManejadorComando<ComandoArticulo> {

    private final FabricaArticulo fabricaArticulo;
    private final ServicioActualizarArticulo servicioActualizarArticulo;

    public ManejadorActualizarArticulo(FabricaArticulo fabricaArticulo, ServicioActualizarArticulo servicioActualizarArticulo) {
        this.fabricaArticulo = fabricaArticulo;
        this.servicioActualizarArticulo = servicioActualizarArticulo;
    }

    public void ejecutar(ComandoArticulo comandoArticulo) {
        Articulo articulo = this.fabricaArticulo.crear(comandoArticulo);
        this.servicioActualizarArticulo.ejecutar( articulo );
    }
}
