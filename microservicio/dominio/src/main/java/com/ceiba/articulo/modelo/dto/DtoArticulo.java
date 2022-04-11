package com.ceiba.articulo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoArticulo {
    private Long idArticulo;
    private String nombreArticulo;
    private Long unidades;
    private Float precio;
}
