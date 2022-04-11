package com.ceiba.articulo.adaptador.dao;

import com.ceiba.articulo.modelo.dto.DtoArticulo;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoArticulo implements RowMapper<DtoArticulo>, MapperResult {

    @Override
    public DtoArticulo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long idArticulo = resultSet.getLong("id_Articulo");
        String nombreArticulo = resultSet.getString("nombre");
        Long unidades = resultSet.getLong("unidades");
        Float precio = resultSet.getFloat( "precio");

        return new DtoArticulo(idArticulo,nombreArticulo,unidades,precio);
    }
}
