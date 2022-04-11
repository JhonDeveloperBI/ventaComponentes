package com.ceiba.articulo.comando.fabrica;

import com.ceiba.articulo.ComandoArticulo;
import com.ceiba.articulo.modelo.entidad.Articulo;
import org.springframework.stereotype.Component;

@Component
public class FabricaArticulo {

    public Articulo crear(ComandoArticulo comandoArticulo) {
        return new Articulo(
                comandoArticulo.getIdArticulo(),
                comandoArticulo.getNombreArticulo(),
                comandoArticulo.getUnidades(),
                comandoArticulo.getPrecio()
        );
    }
}
