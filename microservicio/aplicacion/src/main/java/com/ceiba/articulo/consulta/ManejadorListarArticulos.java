package com.ceiba.articulo.consulta;

import com.ceiba.articulo.modelo.dto.DtoArticulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarArticulos {

    private final DaoArticulo daoArticulo;

    public ManejadorListarArticulos(DaoArticulo daoArticulo) {
        this.daoArticulo = daoArticulo;
    }

    public List<DtoArticulo> ejecutar(){
        return this.daoArticulo.listar();
    }
}
