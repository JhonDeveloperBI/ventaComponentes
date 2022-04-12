package com.ceiba.venta.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Venta {

    private static final String SE_DEBE_INGRESAR_EL_ID_ARTICULO = "Se debe ingresar el id del artículo a comprar";
    private static final String SE_DEBE_INGRESAR_EL_ID_DEL_USUARIO = "Se debe ingresar el id del usuario";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ARTICULOS = "Se debe ingresar la cantidad de artículos a comprar";
    private static final String SE_DEBE_INGRESAR_EL_PRECIO_DE_UNIDAD_DEL_ARTICULO = "Se debe ingresar el precio unidad del artículo";
    private static final String SE_DEBE_INGRESAR_EL_TOTAL_DE_VENTA = "Se debe ingresar el total de venta del artículo";
    private static final String SE_DEBE_INGRESAR_EL_DETALLE_DE_VENTA  = "Se debe ingresar el detalle de venta del artículo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_VENTA  = "Se debe ingresar la fecha de venta del artículo";

    private Long id;
    private Long idArticulo;
    private Long idUsuario;
    private Long unidadVenta;
    private Float precioUnidad;
    private Float totalVenta;
    private String detalleVentaArticulo;
    private LocalDateTime fechaVentaArticulo;

    public Venta(Long id, Long idArticulo, Long idUsuario, Long unidadVenta, Float precioUnidad, Float totalVenta, String detalleVentaArticulo, LocalDateTime fechaVentaArticulo) {
        validarObligatorio(idArticulo, SE_DEBE_INGRESAR_EL_ID_ARTICULO);
        validarObligatorio(idUsuario, SE_DEBE_INGRESAR_EL_ID_DEL_USUARIO);
        validarObligatorio(unidadVenta, SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ARTICULOS);
        validarObligatorio(precioUnidad, SE_DEBE_INGRESAR_EL_PRECIO_DE_UNIDAD_DEL_ARTICULO);
        validarObligatorio(totalVenta, SE_DEBE_INGRESAR_EL_TOTAL_DE_VENTA);
        validarObligatorio(detalleVentaArticulo, SE_DEBE_INGRESAR_EL_DETALLE_DE_VENTA);
        validarObligatorio(fechaVentaArticulo, SE_DEBE_INGRESAR_LA_FECHA_DE_VENTA);





        this.id = id;
        this.idArticulo = idArticulo;
        this.idUsuario = idUsuario;
        this.unidadVenta = unidadVenta;
        this.precioUnidad = precioUnidad;
        this.totalVenta = totalVenta;
        this.detalleVentaArticulo = detalleVentaArticulo;
        this.fechaVentaArticulo = fechaVentaArticulo;
    }
}
