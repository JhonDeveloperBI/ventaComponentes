package com.ceiba.venta.servicio;

import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;

public class ServicioCrearVenta {

    private final RepositorioArticulo repositorioArticulo;
    private final DaoArticulo daoArticulo;

    public ServicioCrearVenta(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo) {
        this.repositorioArticulo = repositorioArticulo;
        this.daoArticulo = daoArticulo;
    }
}
