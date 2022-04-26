package com.ceiba.usuario.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarUsuario implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {

    private final ServicioEliminarUsuario servicioEliminarUsuario;

    public ManejadorEliminarUsuario(ServicioEliminarUsuario servicioEliminarUsuario) {
        this.servicioEliminarUsuario = servicioEliminarUsuario;
    }

    public ComandoRespuesta<Long> ejecutar(Long idUsuario) {
       return new ComandoRespuesta<>(this.servicioEliminarUsuario.ejecutar(idUsuario).longValue());
    }
}
