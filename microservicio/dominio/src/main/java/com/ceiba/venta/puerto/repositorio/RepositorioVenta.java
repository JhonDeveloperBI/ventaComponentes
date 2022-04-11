package com.ceiba.venta.puerto.repositorio;

import com.ceiba.venta.modelo.entidad.Venta;

public interface RepositorioVenta {

    /**
     * Permite crear una venta
     * @param venta
     * @return el id generado
     */
    Long crear(Venta venta);
}
