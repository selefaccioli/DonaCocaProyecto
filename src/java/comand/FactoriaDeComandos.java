/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import java.util.HashMap;

/**
 *
 * @author selef
 */
public class FactoriaDeComandos {
    public static FactoriaDeComandos instancia; 
    private final HashMap<String, Class<?>> mapa;
    
    //constructor singleton
    private FactoriaDeComandos()
    { 
        mapa=new HashMap<>();
        
        mapa.put("InicioComando", InicioComando.class);
        mapa.put("RedireccionarComando", RedireccionarComando.class);
        mapa.put("AgregarLineaPedidoComando", AgregarLineaPedidoComando.class);
        mapa.put("LogInComando",LogInComando.class);
        mapa.put("FinalizarPedidoComando", FinalizarPedidoComando.class);
        mapa.put("LogOutComando", LogOutComando.class);
        mapa.put("SeleccionarTortaComando", SeleccionarTortaComando.class);
        mapa.put("EditarTortaComando", EditarTortaComando.class);
        mapa.put("AgregarTortaComando", AgregarTortaComando.class);
        mapa.put("CuentaComando", CuentaComando.class);
        mapa.put("RegistroComando", RegistroComando.class);
        mapa.put("AgregarUsuarioComando", AgregarUsuarioComando.class);
        mapa.put("SeleccionarUsuarioComando", SeleccionarUsuarioComando.class);
        mapa.put("EditarUsuarioComando", EditarUsuarioComando.class);
        mapa.put("ActualizarLineaComando", ActualizarLineaComando.class);
        mapa.put("EliminarLineaComando", EliminarLineaComando.class);
        mapa.put("RegistrarEnvioComando", RegistrarEnvioComando.class);
        mapa.put("AplicarCuponComando", AplicarCuponComando.class);
        mapa.put("SeleccionarCuponComando", SeleccionarCuponComando.class);
        mapa.put("AgregarCuponComando", AgregarCuponComando.class);
        mapa.put("EditarCuponComando", EditarCuponComando.class);
        mapa.put("EditarDetalleComando", EditarDetalleComando.class);
        mapa.put("SeleccionarDetalleComando", SeleccionarDetalleComando.class);
        mapa.put("AgregarDetalleComando", AgregarDetalleComando.class);
        mapa.put("EditarVarianteComando", EditarVarianteComando.class);
        mapa.put("SeleccionarVarianteComando", SeleccionarVarianteComando.class);
        mapa.put("AgregarVarianteComando", AgregarVarianteComando.class);
        mapa.put("RedireccionarTortaComando", RedireccionarTortaComando.class);
        mapa.put("CalcularPrecioComprarComando", CalcularPrecioComprarComando.class);
        mapa.put("EliminarTortaComando", EliminarTortaComando.class);
       
    
    }
    /**
     * Metodo de clase devuelve la instancia de FactoriaDeComandos
     * @return Devuelve la instancia de FactoriaDeComandos
     */
    public static FactoriaDeComandos getInstancia()
    {
        if( instancia ==null)
            instancia=new FactoriaDeComandos();     
        return instancia;
    }
    
    /**
     * Llega el nombre del comando por parametro, busca el correspondiente en
     * el mapa e instancia la clase que corresponde, devuelve una instancia
     * de comando
     * @param nom {@code String} nombre del comando
     * @return Si la clase es instanciada devuelve un {@code Comando} si ocurre 
     * una excepcion devuelve {@code "null"}
     */
    public Comando buscarComando(String nom) throws IllegalAccessException, InstantiationException
    { 
        Comando c=null;
        try
        {
            c =(Comando)mapa.get(nom).newInstance();
        }
        catch(InstantiationException | IllegalAccessException ex)
        {
            throw ex;
        }     
        return c;       
    }   
    
    
    
}
