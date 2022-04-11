package com.ceiba.articulo.puerto.repositorio;

import com.ceiba.articulo.modelo.entidad.Articulo;

public interface RepositorioArticulo {

    /**
     * Permite crear un Articulo
     * @param Articulo
     * @return el id generado
     */
    Long crear(Articulo articulo);

    /**
     * Permite actualizar un articulo
     * @param articulo
     */
    void actualizar(Articulo articulo);

    /**
     * Permite eliminar un articulo
     * @param id
     */
    Integer eliminar(Long id);
}
