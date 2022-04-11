package com.ceiba.articulo.servicio.testdatabuilder;

import com.ceiba.articulo.modelo.entidad.Articulo;

public class ArticuloTestDataBuilder {

    private Long idArticulo;
    private String nombreArticulo;
    private Long unidades;
    private Float precio;

    public ArticuloTestDataBuilder(){
        nombreArticulo="Arduino";
        unidades=10L;
        precio = 1000F;
    }

    public  ArticuloTestDataBuilder conId(Long idArticulo){
        this.idArticulo = idArticulo;
        return this;
    }


    public ArticuloTestDataBuilder conNombreArticulo( String nombreArticulo){
        this.nombreArticulo = nombreArticulo;
        return this;
    }

    public ArticuloTestDataBuilder conUnidades( Long unidades){
        this.unidades = unidades;
        return this;
    }

    public ArticuloTestDataBuilder conPrecio( Float precio){
        this.precio = precio;
        return this;
    }

    public Articulo build(){
        return  new Articulo(idArticulo,nombreArticulo,unidades,precio);
    }
}
