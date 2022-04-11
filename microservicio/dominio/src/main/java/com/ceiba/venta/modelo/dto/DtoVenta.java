package com.ceiba.venta.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoVenta {
    private Long idVenta;
    private Long idArticulo;
    private Long idUsuario;
    private Long unidadVenta;
    private Float precioUnidad;
    private Float totalVenta;
    private String detalleVentaArticulo;
    private LocalDateTime fechaVentaArticulo;
}
