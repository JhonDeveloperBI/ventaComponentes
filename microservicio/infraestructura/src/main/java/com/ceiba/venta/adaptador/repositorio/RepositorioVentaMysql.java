package com.ceiba.venta.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioVentaMysql implements RepositorioVenta {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioVentaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="venta", value="crear")
    private static String sqlCrearVenta;


    @Override
    public Long crear(Venta venta) {
        return this.customNamedParameterJdbcTemplate.crear(venta, sqlCrearVenta);
    }
}
