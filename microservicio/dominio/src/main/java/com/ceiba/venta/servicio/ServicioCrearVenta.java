package com.ceiba.venta.servicio;


import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.servicio.ServicioActualizarArticulo;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServicioCrearVenta {


    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";
    private static final String EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA = "El articulo no existe en el sistema";
    private static final String NO_HAY_INVENTARIO_ARTICULO = "No hay inventario del articulo";

    private static final String HORA_INICIO_OFERTA = "17:00:00.000";
    private static final String HORA_FINAL_OFERTA = "22:00:00.000";

    private static final String DESCRIPCION_CON_OFERTA = "venta con descuento";
    private static final String DESCRIPCION_SIN_OFERTA = "venta sin descuento";

    private static final Float PRECIO_MAXIMA_OFERTA_ARTICULO = 50000F;
    private static final Long UNIDAD_MAXIMO_ARTICULO_OFERTA = 2L;
    private static final Long UNIDAD_MAXIMO_ARTICULO_OFERTA_FIN_DE_SEMANA = 10L;


    private static final Long PORCENTAJE_OFERTA_ARTICULO_SEMANA = 30L;
    private static final Long PORCENTAJE_OFERTA_ARTICULO_FIN_SEMANA = 10L;


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
        validarInventarioArticulo(totalArticulo);

        LocalDateTime fechaVenta = LocalDateTime.now();

        LocalTime time = fechaVenta.toLocalTime();
        LocalTime horaInicialOferta = LocalTime.parse(HORA_INICIO_OFERTA);
        LocalTime horaFinalOferta = LocalTime.parse(HORA_FINAL_OFERTA);

        Float precioArticulo = articulo.getPrecio();
        Boolean cumplePrecio = precioArticuloOferta(precioArticulo);
        Boolean esDiaEntreSemana = validarDiasEntreSemana(fechaVenta);

        if( esDiaEntreSemana ) {
            venta.setDetalleVentaArticulo(aplicarOferta(unidadVenta, time, horaInicialOferta,
                    horaFinalOferta) && cumplePrecio && esDiaEntreSemana ? DESCRIPCION_CON_OFERTA : DESCRIPCION_SIN_OFERTA);

            venta.setTotalVenta(reglaEstaEnRangoOferta(unidadVenta, time, horaInicialOferta,
                    horaFinalOferta, precioArticulo, cumplePrecio, true));
        }else {
            venta.setDetalleVentaArticulo(DESCRIPCION_CON_OFERTA);
            venta.setTotalVenta(aplicarOfertaFinDeSemana(unidadVenta, precioArticulo, false));
        }

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

    public void validarInventarioArticulo(Long totalArticulo) {
        if(totalArticulo<=0) {
            throw new ExcepcionValorInvalido(NO_HAY_INVENTARIO_ARTICULO);
        }
    }

    public boolean validarDiasEntreSemana(LocalDateTime fechaVenta) {
        return (!(fechaVenta.getDayOfWeek() == DayOfWeek.SATURDAY || fechaVenta.getDayOfWeek() == DayOfWeek.SUNDAY));
    }

    public boolean precioArticuloOferta(Float precio) {
        return precio>PRECIO_MAXIMA_OFERTA_ARTICULO;
    }

    public boolean aplicarOferta(Long unidadVenta, LocalTime time, LocalTime horaInicialOferta, LocalTime horaFinalOferta) {
        return ((unidadVenta>UNIDAD_MAXIMO_ARTICULO_OFERTA) && (horaInicialOferta.isBefore(time)) && (time.isAfter(horaFinalOferta)));
    }

    public float aplicarOfertaFinDeSemana(Long unidadVenta, Float precioArticulo, Boolean esDiaEntreSemana){
        Float totalVenta = unidadVenta * precioArticulo;

        if(  unidadVenta >= UNIDAD_MAXIMO_ARTICULO_OFERTA_FIN_DE_SEMANA && !esDiaEntreSemana ) {
            return totalVenta - (totalVenta * PORCENTAJE_OFERTA_ARTICULO_FIN_SEMANA) / 100;
        }else{
            return totalVenta;
        }
    }

    public float reglaEstaEnRangoOferta(Long unidadVenta, LocalTime time, LocalTime horaInicialOferta,
                                         LocalTime horaFinalOferta, Float precio, boolean cumplePrecio, boolean diaOferta){

        if(aplicarOferta(unidadVenta, time, horaInicialOferta, horaFinalOferta) && cumplePrecio && diaOferta) {
            return ((precio*PORCENTAJE_OFERTA_ARTICULO_SEMANA)/100)*unidadVenta;
        }
        return  precio * unidadVenta;
    }

    public Articulo obtenerArticuloPorId(Long idArticulo) {
        return this.daoArticulo.obtenerArticuloPorId(idArticulo);
    }


}
