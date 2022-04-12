package com.ceiba.venta.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.venta.modelo.dto.DtoVenta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoVenta implements RowMapper<DtoVenta>, MapperResult {


    public DtoVenta mapRow(ResultSet resultSet, int rowNum) throws SQLException {
       Long idVenta = resultSet.getLong("id");
       Long idArticulo =resultSet.getLong("id_articulo");
       Long idUsuario = resultSet.getLong("id_usuario");
       Long unidadVenta = resultSet.getLong("unidad_venta");
       Float precioUnidad =resultSet.getFloat("precio_unidad");
       Float totalVenta =resultSet.getFloat("total_venta");
       String detalleVenta =resultSet.getString("detalle_venta_articulo");
       LocalDateTime fechaVenta =extraerLocalDateTime(resultSet, "fecha_venta");

       return new DtoVenta(idVenta,idArticulo,idUsuario,unidadVenta,precioUnidad,totalVenta,detalleVenta,fechaVenta);

    }
}
