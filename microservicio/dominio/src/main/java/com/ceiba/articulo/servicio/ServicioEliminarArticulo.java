package com.ceiba.articulo.servicio;

import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioEliminarArticulo {

    private static final String EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA = "El artículo no existe en el sistema";

    private final RepositorioArticulo repositorioArticulo;
    private final DaoArticulo daoArticulo;


    public ServicioEliminarArticulo(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo) {
        this.repositorioArticulo = repositorioArticulo;
        this.daoArticulo = daoArticulo;
    }

    public Integer ejecutar(Long id) {
        validarExistenciaPrevia(id);
        return this.repositorioArticulo.eliminar(id);
    }


    public void validarExistenciaPrevia(Long id) {
        boolean existe = this.daoArticulo.existePorId(id);
        if(!existe) {
            throw new ExcepcionSinDatos(EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
