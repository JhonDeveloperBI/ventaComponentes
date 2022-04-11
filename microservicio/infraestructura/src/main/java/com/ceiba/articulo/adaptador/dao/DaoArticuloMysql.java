package com.ceiba.articulo.adaptador.dao;

import com.ceiba.articulo.modelo.dto.DtoArticulo;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoArticuloMysql implements DaoArticulo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoArticuloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace="articulo", value="listar")
    private static String sqlListarArticulo;

    @SqlStatement(namespace="articulo", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="articulo", value="existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace="articulo", value="obtenerPrecioArticuloPorId")
    private static String sqlObtenerPrecioArticuloPorId;

    @SqlStatement(namespace="articulo", value="obtenerArticuloPorId")
    private static String sqlObtenerArticuloPorId;

    @Override
    public List<DtoArticulo> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarArticulo, new MapeoArticulo());
    }

    @Override
    public boolean existe(String nombreArticulo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nombreArticulo);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_articulo", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }

    @Override
    public float obtenerPrecioArticuloPorId(Long idArticulo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_articulo", idArticulo);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPrecioArticuloPorId,paramSource, Float.class);
    }

    @Override
    public Articulo obtenerArticuloPorId(Long idArticulo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_articulo", idArticulo);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerArticuloPorId,paramSource, new com.ceiba.articulo.adaptador.repositorio.MapeoArticulo());
    }
}
