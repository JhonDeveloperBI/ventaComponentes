package com.ceiba.articulo.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearArticulo {

    private static final String EL_ARTICULO_YA_EXISTE_EN_EL_SISTEMA = "El artículo ya existe en el sistema";

    private final RepositorioArticulo repositorioArticulo;

    public ServicioCrearArticulo(RepositorioArticulo repositorioArticulo) {
        this.repositorioArticulo = repositorioArticulo;
    }

    public Long ejecutar(Articulo articulo) {
        validarExistenciaPrevia(articulo);
        return this.repositorioArticulo.crear(articulo);
    }

    private void validarExistenciaPrevia(Articulo articulo) {
        boolean existe = this.repositorioArticulo.existe(articulo.getNombreArticulo());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_ARTICULO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
