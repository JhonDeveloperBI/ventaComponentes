package com.ceiba.venta.consulta;

import com.ceiba.venta.modelo.dto.DtoVenta;
import com.ceiba.venta.puerto.dao.DaoVenta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarVentas {

    private final DaoVenta daoVenta;

    public ManejadorListarVentas(DaoVenta daoVenta) {
        this.daoVenta = daoVenta;
    }

    public List<DtoVenta> ejecutar(){
        return this.daoVenta.listar();
    }
}
