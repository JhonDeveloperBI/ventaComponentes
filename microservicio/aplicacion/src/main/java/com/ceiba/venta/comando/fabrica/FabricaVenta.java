package com.ceiba.venta.comando.fabrica;

import com.ceiba.venta.ComandoVenta;
import com.ceiba.venta.modelo.entidad.Venta;
import org.springframework.stereotype.Component;

@Component
public class FabricaVenta {

    public Venta crear(ComandoVenta comandoVenta){
        return new Venta(
                 comandoVenta.getId(),
                comandoVenta.getIdArticulo(),
                comandoVenta.getIdUsuario(),
                comandoVenta.getUnidadVenta(),
                comandoVenta.getPrecioUnidad(),
                comandoVenta.getTotalVenta(),
                comandoVenta.getDetalleVentaArticulo(),
                comandoVenta.getFechaVentaArticulo()
        );
    }
}
