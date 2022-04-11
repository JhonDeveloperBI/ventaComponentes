package com.ceiba.articulo.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioActualizarArticulo {

    private static final String EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA = "El artículo no existe en el sistema";

    private final RepositorioArticulo repositorioArticulo;
    private final DaoArticulo daoArticulo;

    public ServicioActualizarArticulo(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo) {
        this.repositorioArticulo = repositorioArticulo;
        this.daoArticulo = daoArticulo;
    }

    public void ejecutar(Articulo articulo) {
        validarExistenciaPrevia(articulo);
        this.repositorioArticulo.actualizar(articulo);
    }

    private void validarExistenciaPrevia(Articulo articulo) {
        boolean existe = this.daoArticulo.existePorId(articulo.getIdArticulo());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
