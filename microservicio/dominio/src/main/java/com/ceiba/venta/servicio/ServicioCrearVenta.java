package com.ceiba.venta.servicio;

import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

public class ServicioCrearVenta {

    private final RepositorioVenta repositorioVenta;

    public ServicioCrearVenta(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public Long ejecutar(Venta venta){
        return this.repositorioVenta.crear(venta);
    }

}
