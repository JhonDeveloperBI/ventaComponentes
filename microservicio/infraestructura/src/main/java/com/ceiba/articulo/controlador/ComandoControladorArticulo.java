package com.ceiba.articulo.controlador;

import com.ceiba.articulo.comando.manejador.ManejadorActualizarArticulo;
import com.ceiba.articulo.comando.manejador.ManejadorCrearArticulo;
import com.ceiba.articulo.comando.manejador.ManejadorEliminarArticulo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulos")
@Api(tags = { "Controlador comando articulo"})
public class ComandoControladorArticulo {

    private final ManejadorCrearArticulo manejadorCrearLibro;
    private final ManejadorEliminarArticulo manejadorEliminarLibro;
    private final ManejadorActualizarArticulo manejadorActualizarLibro;

    public ComandoControladorArticulo(ManejadorCrearArticulo manejadorCrearLibro, ManejadorEliminarArticulo manejadorEliminarLibro, ManejadorActualizarArticulo manejadorActualizarLibro) {
        this.manejadorCrearLibro = manejadorCrearLibro;
        this.manejadorEliminarLibro = manejadorEliminarLibro;
        this.manejadorActualizarLibro = manejadorActualizarLibro;
    }

}
