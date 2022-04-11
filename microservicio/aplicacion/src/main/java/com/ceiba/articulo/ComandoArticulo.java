package com.ceiba.articulo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoArticulo {

    private Long idArticulo;
    private String nombreArticulo;
    private Long unidades;
    private Float precio;
}
