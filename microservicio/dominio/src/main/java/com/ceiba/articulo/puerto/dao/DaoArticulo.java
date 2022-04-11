package com.ceiba.articulo.puerto.dao;

import com.ceiba.articulo.modelo.dto.DtoArticulo;
import com.ceiba.articulo.modelo.entidad.Articulo;

import java.util.List;

public interface DaoArticulo {

    /**
     * Permite listar Articulos
     * @return los articulos
     */
    List<DtoArticulo> listar();

    /**
     * Permite validar si existe un articulo con un nombre
     * @param nombreArticulo
     * @return si existe o no
     */
    boolean existe(String nombreArticulo);

    /**
     * Permite validar si existe un articulo con un nombre excluyendo un id
     * @return si existe o no
     */
    boolean existePorId(Long id);


    /**
     * Obtener precio por idArticulo
     * @return precio
     */
    float obtenerPrecioArticuloPorId(Long idArticulo);


    /**
     * Obtener precio por idArticulo
     * @return Articulo
     */
    Articulo obtenerArticuloPorId(Long idArticulo);
}
