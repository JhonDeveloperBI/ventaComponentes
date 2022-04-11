package com.ceiba.configuracion;

import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.articulo.servicio.ServicioCrearArticulo;
import com.ceiba.articulo.servicio.ServicioEliminarArticulo;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import com.ceiba.venta.servicio.ServicioCrearVenta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    //artículos
    @Bean
    public ServicioCrearArticulo servicioCrearArticulo(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo){
        return new ServicioCrearArticulo(repositorioArticulo,daoArticulo);
    }

    @Bean
    public ServicioEliminarArticulo servicioEliminarArticulo(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo){
        return new ServicioEliminarArticulo(repositorioArticulo,daoArticulo);
    }

    @Bean
    public ServicioActualizarArticulo servicioActualizarArticulo(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo){
        return new ServicioActualizarArticulo(repositorioArticulo,daoArticulo);
    }

    //ventas
    @Bean
    public ServicioCrearVenta servicioCrearVenta(RepositorioArticulo repositorioArticulo, DaoArticulo daoArticulo){
        return new ServicioCrearVenta(repositorioArticulo,daoArticulo);
    }


}
