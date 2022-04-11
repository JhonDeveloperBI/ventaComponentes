package com.ceiba.venta.modelo.entidad;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class Venta {

    private static final String SE_DEBE_INGRESAR_EL_ID_ARTICULO = "Se debe ingresar el id del artículo a comprar";
    private static final String SE_DEBE_INGRESAR_EL_ID_DEL_USUARIO = "Se debe ingresar el id del usuario";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ARTICULOS = "Se debe ingresar la cantidad de artículos a comprar";

    private Long idVenta;
    private Long idArticulo;
    private Long idUsuario;
    private Long unidadVenta;
    private Float precioUnidad;
    private Float totalVenta;
    private String detalleVentaArticulo;
    private LocalDateTime fechaVentaArticulo;

    public Venta(Long idVenta, Long idArticulo, Long idUsuario, Long unidadVenta, Float precioUnidad, Float totalVenta, String detalleVentaArticulo, LocalDateTime fechaVentaArticulo) {
        validarObligatorio(idArticulo, SE_DEBE_INGRESAR_EL_ID_ARTICULO);
        validarObligatorio(idUsuario, SE_DEBE_INGRESAR_EL_ID_DEL_USUARIO);
        validarObligatorio(unidadVenta, SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ARTICULOS);


        this.idVenta = idVenta;
        this.idArticulo = idArticulo;
        this.idUsuario = idUsuario;
        this.unidadVenta = unidadVenta;
        this.precioUnidad = precioUnidad;
        this.totalVenta = totalVenta;
        this.detalleVentaArticulo = detalleVentaArticulo;
        this.fechaVentaArticulo = fechaVentaArticulo;
    }
}
