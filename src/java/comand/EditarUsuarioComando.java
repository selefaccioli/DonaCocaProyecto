/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class EditarUsuarioComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        CtrlUsuario ctrlU = new CtrlUsuario();
        Usuario usEditado = (Usuario) request.getSession().getAttribute("usuarioEdit");
        
        usEditado.setNombre(request.getParameter("nomUsu"));
        usEditado.setApellido(request.getParameter("apeUsu"));
        usEditado.setDni(Integer.parseInt(request.getParameter("dniUsu")));
        usEditado.setUsuario(request.getParameter("usuUsu"));
        Boolean esAdmin = (request.getParameter("admin")!=null);
        Boolean esActivo = (request.getParameter("activo")!=null);
        usEditado.setEsAdmin(esAdmin);
        usEditado.setActivo(esActivo);
        usEditado.setMail(request.getParameter("mailUsu"));
        usEditado.setTelefono(request.getParameter("telUsu"));
        usEditado.setDireccion(request.getParameter("dicUsu"));
        
        ArrayList<Usuario> usuarios=null;
        
        try {
            ctrlU.editarUsuario(usEditado);
            usuarios = ctrlU.obtenerUsuarios();
        } catch (DonaCocaException ex) {
           request.setAttribute("ex", ex.getMessage());
           return "/ABMUsuarios.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(EditarUsuarioComando.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getSession().setAttribute("listaUsuarios", usuarios);
        request.getSession().setAttribute("usuarioEdit", usEditado);
        request.getSession().setAttribute("Scroll",true);
        request.setAttribute("ExitoUsuario", true);
        
        return"/ABMUsuarios.jsp";

    }
    
}
