package com.ceiba.articulo.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Articulo {
    private Long id;
    private String nombreArticulo;
    private Long unidades;
    private Float precio;

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ARTICULO = "Se debe ingresar el nombre del articulo";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD = "Se debe ingresar la cantidad de articulos";
    private static final String SE_DEBE_INGRESAR_EL_PRECIO = "Se debe ingresar el precio del articulo";


    public Articulo(Long id,String nombreArticulo, Long unidades,Float precio) {
        validarObligatorio(nombreArticulo, SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ARTICULO);
        validarObligatorio(unidades, SE_DEBE_INGRESAR_LA_CANTIDAD);
        validarObligatorio(precio, SE_DEBE_INGRESAR_EL_PRECIO);

        this.id = id;
        this.nombreArticulo = nombreArticulo;
        this.unidades = unidades;
        this.precio = precio;
    }


}
