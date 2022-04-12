package com.ceiba.articulo.adaptador.repositorio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
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

    @SqlStatement(namespace="articulo", value="existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace="articulo", value="existe")
    private static String sqlExistePorNombre;


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
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarArticulo, paramSource);
    }

    @Override
    public boolean existe(String nombreArticulo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nombreArticulo);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorNombre,paramSource, Boolean.class);
    }

    @Override
    public boolean existePorId(Long idArticulo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idArticulo);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }
}
