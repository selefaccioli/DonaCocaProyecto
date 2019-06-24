/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlUsuario;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CuentaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        
        CtrlUsuario cu = new CtrlUsuario();
        
        Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
        
        if(request.getParameter("actDatos") != null){
            
            usu.setNombre(request.getParameter("nombre"));
            usu.setApellido(request.getParameter("apellido"));
            usu.setMail(request.getParameter("email"));
            usu.setTelefono(request.getParameter("telefono"));
            usu.setDireccion(request.getParameter("direccion"));
            usu.setDni(Integer.parseInt(request.getParameter("dni")));
        }
        
        try {
            cu.editarUsuario(usu);
            usu= cu.obtenerUsuario(usu.getUsuario(), usu.getContrasenia());
        } catch (DonaCocaException ex) {
            request.setAttribute("ex", ex.getMessage());
            return "/cuenta.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(CuentaComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("exitoEditado", true);
        request.getSession().setAttribute("usuario", usu);
        
        return "/cuenta.jsp";
    }
    
}
