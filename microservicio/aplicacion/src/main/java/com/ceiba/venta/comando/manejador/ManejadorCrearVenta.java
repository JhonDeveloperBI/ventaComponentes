package com.ceiba.venta.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.venta.ComandoVenta;
import com.ceiba.venta.comando.fabrica.FabricaVenta;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.servicio.ServicioCrearVenta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearVenta implements ManejadorComandoRespuesta<ComandoVenta, ComandoRespuesta<Long>> {

    private final FabricaVenta fabricaVenta;
    private final ServicioCrearVenta servicioCrearVenta;

    public ManejadorCrearVenta(FabricaVenta fabricaVenta, ServicioCrearVenta servicioCrearVenta) {
        this.fabricaVenta = fabricaVenta;
        this.servicioCrearVenta = servicioCrearVenta;
    }


    public ComandoRespuesta<Long> ejecutar(ComandoVenta comandoVenta) {
        Venta venta = this.fabricaVenta.crear(comandoVenta);
        return new ComandoRespuesta<>(this.servicioCrearVenta.ejecutar(venta));
    }
}
