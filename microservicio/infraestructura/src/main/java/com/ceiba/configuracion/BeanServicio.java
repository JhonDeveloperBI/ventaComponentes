package com.ceiba.configuracion;

import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.articulo.servicio.ServicioCrearArticulo;
import com.ceiba.articulo.servicio.ServicioEliminarArticulo;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
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
    public ServicioCrearArticulo servicioCrearArticulo(RepositorioArticulo repositorioArticulo){
        return new ServicioCrearArticulo(repositorioArticulo);
    }

    @Bean
    public ServicioEliminarArticulo servicioEliminarArticulo(RepositorioArticulo repositorioArticulo){
        return new ServicioEliminarArticulo(repositorioArticulo);
    }

    @Bean
    public ServicioActualizarArticulo servicioActualizarArticulo(RepositorioArticulo repositorioArticulo){
        return new ServicioActualizarArticulo(repositorioArticulo);
    }

    //ventas
    @Bean
    public ServicioCrearVenta servicioCrearVenta(RepositorioVenta repositorioVenta){
        return new ServicioCrearVenta(repositorioVenta);
    }


}
