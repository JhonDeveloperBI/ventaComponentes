package com.ceiba.articulo.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearArticulo {

    private static final String EL_ARTICULO_YA_EXISTE_EN_EL_SISTEMA = "El artículo ya existe en el sistema";

    private final RepositorioArticulo repositorioArticulo;
    private final DaoArticulo daoArticulo;

    public ServicioCrearArticulo(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo) {
        this.repositorioArticulo = repositorioArticulo;
        this.daoArticulo = daoArticulo;
    }

    public Long ejecutar(Articulo articulo) {
        validarExistenciaPrevia(articulo);
        return this.repositorioArticulo.crear(articulo);
    }

    private void validarExistenciaPrevia(Articulo articulo) {
        boolean existe = this.daoArticulo.existe(articulo.getNombreArticulo());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_ARTICULO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
