package com.ceiba.venta.controlador;

import com.ceiba.venta.consulta.ManejadorListarVentas;
import com.ceiba.venta.modelo.dto.DtoVenta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@Api(tags={"Controlador consulta ventas"})
public class ConsultaControladorVenta {

    private final ManejadorListarVentas manejadorListarVentas;

    @Autowired
    public ConsultaControladorVenta(ManejadorListarVentas manejadorListarVentas) {
        this.manejadorListarVentas = manejadorListarVentas;
    }

    @GetMapping
    @ApiOperation("Listar Ventas")
    public List<DtoVenta> listar() {
        return this.manejadorListarVentas.ejecutar();
    }

}
