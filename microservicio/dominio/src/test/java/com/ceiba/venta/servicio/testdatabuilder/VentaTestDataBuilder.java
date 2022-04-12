package com.ceiba.venta.servicio.testdatabuilder;

import com.ceiba.venta.modelo.entidad.Venta;

import java.time.LocalDateTime;

public class VentaTestDataBuilder {

    private Long id;
    private Long idArticulo;
    private Long idUsuario;
    private Long unidadVenta;
    private Float precioUnidad;
    private Float totalVenta;
    private String detalleVentaArticulo;
    private LocalDateTime fechaVentaArticulo;

    public VentaTestDataBuilder(){
        idArticulo= 1L;
        idUsuario=1L;
        unidadVenta=5L;
        precioUnidad=500F;
        totalVenta=2500F;
        detalleVentaArticulo="Sin descuento";
        fechaVentaArticulo= LocalDateTime.now();
    }

    public VentaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public VentaTestDataBuilder conIdArticulo(Long idArticulo){
        this.idArticulo = idArticulo;
        return this;
    }

    public VentaTestDataBuilder conIdUsuario(Long idUsuario){
        this.idUsuario = idUsuario;
        return this;
    }

    public VentaTestDataBuilder conUnidadVenta(Long unidadVenta){
        this.unidadVenta = unidadVenta;
        return this;
    }

    public VentaTestDataBuilder conPrecioUnidad(Float precioUnidad ){
        this.precioUnidad = precioUnidad;
        return this;
    }

    public VentaTestDataBuilder conTotalVenta(Float totalVenta ){
        this.totalVenta = totalVenta;
        return this;
    }

    public VentaTestDataBuilder conDetalleVenta(String detalleVentaArticulo){
        this.detalleVentaArticulo = detalleVentaArticulo;
        return this;
    }

    public VentaTestDataBuilder conFechaVentaArticulo(LocalDateTime fechaVentaArticulo){
        this.fechaVentaArticulo = fechaVentaArticulo;
        return this;
    }


    public Venta build(){
        return new Venta(id,idArticulo,idUsuario,unidadVenta,precioUnidad,totalVenta,detalleVentaArticulo,fechaVentaArticulo);
    }
}
