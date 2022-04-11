package com.ceiba.articulo.adaptador.repositorio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class RepositorioArticuloMysql implements RepositorioArticulo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioArticuloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="articulo", value="crear")
    private static String sqlCrearArticulo;

    @SqlStatement(namespace="articulo", value="actualizar")
    private static String sqlActualizarArticulo;

    @SqlStatement(namespace="articulo", value="eliminar")
    private static String sqlEliminarArticulo;

    @Override
    public Long crear(Articulo articulo) {
        return this.customNamedParameterJdbcTemplate.crear(articulo, sqlCrearArticulo);
    }

    @Override
    public void actualizar(Articulo articulo) {
        this.customNamedParameterJdbcTemplate.actualizar(articulo, sqlActualizarArticulo);
    }

    @Override
    public Integer eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_articulo", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarArticulo, paramSource);
    }
}
