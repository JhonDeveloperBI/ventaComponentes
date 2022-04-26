package com.ceiba.articulo.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;
import lombok.Setter;

import static com.ceiba.dominio.ValidadorArgumento.*;

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
    private static final String LA_CANTIDAD_DEBE_SER_UN_VALOR_NUMERICO = "La cantidad debe ser un valor numerico";
    private static final String EL_PRECIO_DEBE_SER_UN_VALOR_NUMERICO = "El precio debe ser un valor numerico";

    private static final String LA_CANTIDAD_DEBE_SER_MAYOR_A_CERO = "La cantidad de articulos deber ser mayor a 0";
    private static final String EL_PRECIO_DEBE_SER_MAYOR_A_CERO = "El precio debe ser mayor a 0";
    private static final String LA_CANTIDAD_DEBE_SER_MAYOR_A_CERO_Y_EL_PRECIO_DEBE_SER_MAYOR_A_CERO ="La cantidad de articulos debe ser mayor a 0 y el precio deber ser mayor a 0";

    private static final String NO_HAY_INVENTARIO_ARTICULO = "No hay inventario del articulo";


    public Articulo(Long id,String nombreArticulo, Long unidades,Float precio) {
        validarObligatorio(nombreArticulo, SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ARTICULO);
        validarObligatorio(unidades, SE_DEBE_INGRESAR_LA_CANTIDAD);
        validarObligatorio(precio, SE_DEBE_INGRESAR_EL_PRECIO);
        validarNumerico(String.valueOf(unidades), LA_CANTIDAD_DEBE_SER_UN_VALOR_NUMERICO);
        validarNumerico(String.valueOf(precio), EL_PRECIO_DEBE_SER_UN_VALOR_NUMERICO);
        validarMayorACero(unidades, precio, LA_CANTIDAD_DEBE_SER_MAYOR_A_CERO, EL_PRECIO_DEBE_SER_MAYOR_A_CERO,
                LA_CANTIDAD_DEBE_SER_MAYOR_A_CERO_Y_EL_PRECIO_DEBE_SER_MAYOR_A_CERO);

        this.id = id;
        this.nombreArticulo = nombreArticulo;
        this.unidades = unidades;
        this.precio = precio;
    }

    public void validarInventarioArticulo(Long totalArticulo) {
        if(totalArticulo<=0) {
            throw new ExcepcionValorInvalido(NO_HAY_INVENTARIO_ARTICULO);
        }
    }

}
