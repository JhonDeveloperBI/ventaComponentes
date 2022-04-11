package com.ceiba.articulo.controlador;

import com.ceiba.articulo.consulta.ManejadorListarArticulos;
import com.ceiba.articulo.modelo.dto.DtoArticulo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articulos")
@Api(tags={"Controlador consulta artículos"})
public class ConsultaControladorArticulo {

    private final ManejadorListarArticulos manejadorListarArticulos;

    public ConsultaControladorArticulo(ManejadorListarArticulos manejadorListarArticulos) {
        this.manejadorListarArticulos = manejadorListarArticulos;
    }

    @GetMapping
    @ApiOperation("Listar Artículos")
    public List<DtoArticulo> listar() {
        return this.manejadorListarArticulos.ejecutar();
    }

}
