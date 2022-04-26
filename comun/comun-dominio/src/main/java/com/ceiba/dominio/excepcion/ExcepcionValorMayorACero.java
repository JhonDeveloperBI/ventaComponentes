package com.ceiba.dominio.excepcion;

public class ExcepcionValorMayorACero extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionValorMayorACero(String mensaje) {
        super(mensaje);
    }
}
