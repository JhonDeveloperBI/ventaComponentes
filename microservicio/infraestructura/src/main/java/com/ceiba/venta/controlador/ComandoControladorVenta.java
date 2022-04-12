package com.ceiba.venta.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.venta.ComandoVenta;
import com.ceiba.venta.comando.manejador.ManejadorCrearVenta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
@Api(tags = { "Controlador comando ventas"})
public class ComandoControladorVenta {

    private final ManejadorCrearVenta manejadorCrearVenta;

    @Autowired
    public ComandoControladorVenta(ManejadorCrearVenta manejadorCrearVenta) {
        this.manejadorCrearVenta = manejadorCrearVenta;
    }

    @PostMapping
    @ApiOperation("Crear venta")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoVenta comandoVenta) {
        return manejadorCrearVenta.ejecutar(comandoVenta);
    }


}
