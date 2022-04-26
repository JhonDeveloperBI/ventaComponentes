package com.ceiba.venta.servicio;


import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServicioCrearVenta {


    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";
    private static final String EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA = "El articulo no existe en el sistema";


    private static final String HORA_INICIO_OFERTA = "17:00:00.000";
    private static final String HORA_FINAL_OFERTA = "22:00:00.000";

    private static final String DESCRIPCION_CON_OFERTA = "venta con descuento";
    private static final String DESCRIPCION_SIN_OFERTA = "venta sin descuento";

    private static final Long UNIDAD_MAXIMO_ARTICULO_OFERTA_FIN_DE_SEMANA = 10L;


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

        validarExistenciaPreviaUsuario(venta.getIdUsuario());
        validarExistenciaPreviaArticulo(idArticulo);
        Articulo articulo = obtenerArticuloPorId(idArticulo);

        Long unidadVenta =  venta.getUnidadVenta();
        Long unidadArticulo = articulo.getUnidades();
        Long totalArticulo = unidadArticulo - unidadVenta;
        articulo.validarInventarioArticulo(totalArticulo);

        LocalDateTime fechaVenta = obtenerFecha();

        LocalTime time = fechaVenta.toLocalTime();
        LocalTime horaInicialOferta = LocalTime.parse(HORA_INICIO_OFERTA);
        LocalTime horaFinalOferta = LocalTime.parse(HORA_FINAL_OFERTA);

        Float precioArticulo = articulo.getPrecio();
        Boolean cumplePrecio = venta.precioArticuloOferta(precioArticulo);
        Boolean esDiaEntreSemana = venta.validarDiasEntreSemana(fechaVenta);

        if( esDiaEntreSemana ) {
            venta.setDetalleVentaArticulo(venta.aplicarOferta(unidadVenta, time, horaInicialOferta,
                    horaFinalOferta) && cumplePrecio ? DESCRIPCION_CON_OFERTA : DESCRIPCION_SIN_OFERTA);

            venta.setTotalVenta(venta.reglaEstaEnRangoOferta(unidadVenta, time, horaInicialOferta,
                    horaFinalOferta, precioArticulo, cumplePrecio, true));
        }else {
            venta.setTotalVenta(venta.aplicarOfertaFinDeSemana(unidadVenta, UNIDAD_MAXIMO_ARTICULO_OFERTA_FIN_DE_SEMANA, precioArticulo, false));
        }
        venta.setFechaVentaArticulo(fechaVenta);
        venta.setPrecioUnidad(precioArticulo+1);
        articulo.setUnidades(totalArticulo);
        servicioActualizarArticulo.ejecutar(articulo);
        return this.repositorioVenta.crear(venta);
    }


    public void validarExistenciaPreviaArticulo(Long idArticulo) {
        boolean existe = this.daoArticulo.existePorId(idArticulo);
        if(!existe) {
            throw new ExcepcionValorInvalido(EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    public void validarExistenciaPreviaUsuario(Long idUsuario) {
        boolean existe = this.daoUsuario.existePorId(idUsuario);
        if(!existe) {
            throw new ExcepcionValorInvalido(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    public Articulo obtenerArticuloPorId(Long idArticulo) {
        return this.daoArticulo.obtenerArticuloPorId(idArticulo);
    }

    public LocalDateTime obtenerFecha(){
        return LocalDateTime.now();
    }


}
