package com.ceiba.venta.servicio.testdatabuilder;

import com.ceiba.venta.ComandoVenta;
import com.ceiba.venta.modelo.entidad.Venta;

import java.time.LocalDateTime;

public class ComandoVentaTestDataBuilder {

    private Long id;
    private Long idArticulo;
    private Long idUsuario;
    private Long unidadVenta;
    private Float precioUnidad;
    private Float totalVenta;
    private String detalleVentaArticulo;
    private LocalDateTime fechaVentaArticulo;

    public ComandoVentaTestDataBuilder(){
        idArticulo= 1L;
        idUsuario=1L;
        unidadVenta=5L;
        precioUnidad=500F;
        totalVenta=2500F;
        detalleVentaArticulo="Sin descuento";
        fechaVentaArticulo= LocalDateTime.now();
    }

    public ComandoVentaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public ComandoVentaTestDataBuilder conIdArticulo(Long idArticulo){
        this.idArticulo = idArticulo;
        return this;
    }

    public ComandoVentaTestDataBuilder conIdUsuario(Long idUsuario){
        this.idUsuario = idUsuario;
        return this;
    }

    public ComandoVentaTestDataBuilder conUnidadVenta(Long unidadVenta){
        this.unidadVenta = unidadVenta;
        return this;
    }

    public ComandoVentaTestDataBuilder conPrecioUnidad(Float precioUnidad ){
        this.precioUnidad = precioUnidad;
        return this;
    }

    public ComandoVentaTestDataBuilder conTotalVenta(Float totalVenta ){
        this.totalVenta = totalVenta;
        return this;
    }

    public ComandoVentaTestDataBuilder conDetalleVenta(String detalleVentaArticulo){
        this.detalleVentaArticulo = detalleVentaArticulo;
        return this;
    }

    public ComandoVentaTestDataBuilder conFechaVentaArticulo(LocalDateTime fechaVentaArticulo){
        this.fechaVentaArticulo = fechaVentaArticulo;
        return this;
    }


    public ComandoVenta build(){
        return new ComandoVenta(id,idArticulo,idUsuario,unidadVenta,precioUnidad,totalVenta,detalleVentaArticulo,fechaVentaArticulo);
    }
}
