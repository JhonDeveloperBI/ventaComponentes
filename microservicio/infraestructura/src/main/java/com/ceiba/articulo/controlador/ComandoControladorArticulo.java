package com.ceiba.articulo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.ComandoArticulo;
import com.ceiba.articulo.comando.manejador.ManejadorActualizarArticulo;
import com.ceiba.articulo.comando.manejador.ManejadorCrearArticulo;
import com.ceiba.articulo.comando.manejador.ManejadorEliminarArticulo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulos")
@Api(tags = { "Controlador comando artículo"})
public class ComandoControladorArticulo {

    private final ManejadorCrearArticulo manejadorCrearArticulo;
    private final ManejadorEliminarArticulo manejadorEliminarArticulo;
    private final ManejadorActualizarArticulo manejadorActualizarArticulo;

    @Autowired
    public ComandoControladorArticulo(ManejadorCrearArticulo manejadorCrearArticulo, ManejadorEliminarArticulo manejadorEliminarArticulo, ManejadorActualizarArticulo manejadorActualizarArticulo) {
        this.manejadorCrearArticulo = manejadorCrearArticulo;
        this.manejadorEliminarArticulo = manejadorEliminarArticulo;
        this.manejadorActualizarArticulo = manejadorActualizarArticulo;
    }

    @PostMapping
    @ApiOperation("Crear artículo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoArticulo comandoArticulo) {
        return manejadorCrearArticulo.ejecutar(comandoArticulo);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar artículo")
    public void actualizar(@RequestBody ComandoArticulo comandoArticulo,@PathVariable Long idArticulo) {
        comandoArticulo.setIdArticulo(idArticulo);
        manejadorActualizarArticulo.ejecutar(comandoArticulo);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar artículo")
    public  ComandoRespuesta<Long> eliminar(@PathVariable Long id) {
        return manejadorEliminarArticulo.ejecutar(id);
    }

}
