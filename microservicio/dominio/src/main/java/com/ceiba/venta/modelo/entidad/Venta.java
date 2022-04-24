package com.ceiba.venta.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Venta {

    private static final String SE_DEBE_INGRESAR_EL_ID_ARTICULO = "Se debe ingresar el id del artículo a comprar";
    private static final String SE_DEBE_INGRESAR_EL_ID_DEL_USUARIO = "Se debe ingresar el id del usuario";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ARTICULOS = "Se debe ingresar la cantidad de artículos a comprar";
    private static final Float PRECIO_MAXIMA_OFERTA_ARTICULO = 50000F;
    private static final Long UNIDAD_MAXIMO_ARTICULO_OFERTA = 2L;
    private static final Long PORCENTAJE_OFERTA_ARTICULO_SEMANA = 30L;
    private static final Long PORCENTAJE_OFERTA_ARTICULO_FIN_SEMANA = 10L;
    private static final String DESCRIPCION_CON_OFERTA = "venta con descuento";
    private static final String DESCRIPCION_SIN_OFERTA = "venta sin descuento";

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


        this.id = id;
        this.idArticulo = idArticulo;
        this.idUsuario = idUsuario;
        this.unidadVenta = unidadVenta;
        this.precioUnidad = precioUnidad;
        this.totalVenta = totalVenta;
        this.detalleVentaArticulo = detalleVentaArticulo;
        this.fechaVentaArticulo = fechaVentaArticulo;
    }

    public boolean precioArticuloOferta(Float precio) {
        return precio>PRECIO_MAXIMA_OFERTA_ARTICULO;
    }

    public boolean validarDiasEntreSemana(LocalDateTime fechaVenta) {
        return (!(fechaVenta.getDayOfWeek() == DayOfWeek.SATURDAY || fechaVenta.getDayOfWeek() == DayOfWeek.SUNDAY));
    }

    public boolean aplicarOferta(Long unidadVenta, LocalTime time, LocalTime horaInicialOferta, LocalTime horaFinalOferta) {
        return ((unidadVenta>UNIDAD_MAXIMO_ARTICULO_OFERTA) && (horaInicialOferta.isBefore(time)) && (time.isAfter(horaFinalOferta)));
    }

    public float reglaEstaEnRangoOferta(Long unidadVenta, LocalTime time, LocalTime horaInicialOferta,
                                        LocalTime horaFinalOferta, Float precio, boolean cumplePrecio, boolean diaOferta){

        if(aplicarOferta(unidadVenta, time, horaInicialOferta, horaFinalOferta) && cumplePrecio && diaOferta) {
            return ((precio*PORCENTAJE_OFERTA_ARTICULO_SEMANA)/100)*unidadVenta;
        }
        return  precio * unidadVenta;
    }

    public float aplicarOfertaFinDeSemana(Long unidadVenta,Long unidadMaximaOferta, Float precioArticulo, Boolean esDiaEntreSemana){
        Float totalVenta = unidadVenta * precioArticulo;

        if(  unidadVenta >= unidadMaximaOferta && !esDiaEntreSemana ) {
            this.setDetalleVentaArticulo(DESCRIPCION_CON_OFERTA);
            return totalVenta - (totalVenta * PORCENTAJE_OFERTA_ARTICULO_FIN_SEMANA) / 100;
        }else{
            this.setDetalleVentaArticulo(DESCRIPCION_SIN_OFERTA);
            return totalVenta;
        }
    }
}
