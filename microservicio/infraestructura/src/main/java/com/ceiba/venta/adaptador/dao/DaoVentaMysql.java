package com.ceiba.venta.adaptador.dao;

import com.ceiba.articulo.adaptador.dao.MapeoArticulo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.venta.modelo.dto.DtoVenta;
import com.ceiba.venta.puerto.dao.DaoVenta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoVentaMysql implements DaoVenta {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoVentaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="venta", value="listar")
    private static String sqlListarVenta;


    @Override
    public List<DtoVenta> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarVenta, new MapeoVenta());
    }
}
