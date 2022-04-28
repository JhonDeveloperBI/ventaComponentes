package com.ceiba.venta.validaciones;

import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.puerto.dao.DaoUsuario;

public class VentaValidaciones {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";
    private static final String EL_ARTICULO_NO_EXISTE_EN_EL_SISTEMA = "El articulo no existe en el sistema";

    private final DaoArticulo daoArticulo;
    private final DaoUsuario daoUsuario;

    public VentaValidaciones(DaoArticulo daoArticulo, DaoUsuario daoUsuario){
        this.daoArticulo = daoArticulo;
        this.daoUsuario = daoUsuario;
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

}
