package com.ceiba.venta.servicio;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.venta.validaciones.VentaValidaciones;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

public class ServicioCrearVenta {

    private final RepositorioVenta repositorioVenta;
    private final ServicioActualizarArticulo servicioActualizarArticulo;
    private final DaoArticulo daoArticulo;
    private final DaoUsuario daoUsuario;

    public ServicioCrearVenta(RepositorioVenta repositorioVenta, ServicioActualizarArticulo servicioActualizarArticulo, DaoArticulo daoArticulo, DaoUsuario daoUsuario) {
        this.repositorioVenta = repositorioVenta;
        this.servicioActualizarArticulo = servicioActualizarArticulo;
        this.daoArticulo = daoArticulo;
        this.daoUsuario = daoUsuario;
    }

    public Long ejecutar(Venta venta){
        Long idArticulo = venta.getIdArticulo();
        VentaValidaciones ventaValidaciones = new VentaValidaciones(daoArticulo,daoUsuario);
        ventaValidaciones.validarExistenciaPreviaUsuario(venta.getIdUsuario());
        ventaValidaciones.validarExistenciaPreviaArticulo(idArticulo);

        Articulo articulo = obtenerArticuloPorId(idArticulo);
        Long unidadVenta =  venta.getUnidadVenta();
        Long unidadArticulo = articulo.getUnidades();

        articulo.validarInventarioArticulo(unidadArticulo, unidadVenta);
        Float precioArticulo = articulo.getPrecio();
        Boolean cumplePrecio = venta.precioArticuloOferta(precioArticulo);

        venta.calcularOferta( precioArticulo, cumplePrecio);

        this.servicioActualizarArticulo.ejecutar(articulo);
        return this.repositorioVenta.crear(venta);
    }

    public Articulo obtenerArticuloPorId(Long idArticulo) {
        return this.daoArticulo.obtenerArticuloPorId(idArticulo);
    }




}
