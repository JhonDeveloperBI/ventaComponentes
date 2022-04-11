package com.ceiba.articulo.adaptador.repositorio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoArticulo implements RowMapper<Articulo>, MapperResult {

    @Override
    public Articulo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long idArticulo = resultSet.getLong("id_Articulo");
        String nombreArticulo = resultSet.getString("nombre");
        Long unidades = resultSet.getLong("unidades");
        Float precio = resultSet.getFloat( "precio");

        return new Articulo(idArticulo,nombreArticulo,unidades,precio);
    }
}
