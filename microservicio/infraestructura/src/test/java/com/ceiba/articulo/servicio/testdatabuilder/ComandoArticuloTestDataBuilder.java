package com.ceiba.articulo.servicio.testdatabuilder;

import com.ceiba.articulo.ComandoArticulo;

import java.util.UUID;

public class ComandoArticuloTestDataBuilder {

    private Long idArticulo;
    private String nombreArticulo;
    private Long unidades;
    private Float precio;

    public ComandoArticuloTestDataBuilder(){
        nombreArticulo = UUID.randomUUID().toString();
        unidades = 5L;
        precio = 1000F;
    }

    public ComandoArticuloTestDataBuilder conNombre(String nombreArticulo){
        this.nombreArticulo = nombreArticulo;
        return this;
    }

    public ComandoArticulo build(){
        return new ComandoArticulo(idArticulo,nombreArticulo,unidades,precio);
    }
}
