/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlUsuario;

/**
 *
 * @author selef
 */
public class SeleccionarUsuarioComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlUsuario ctrlU = new CtrlUsuario();
        int idUsuEdit = Integer.parseInt(request.getParameter("idUsuarioEdit"));
        
        if(idUsuEdit != 0){
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getSession().getAttribute("listaUsuarios");
            Usuario usuarioEdit = null;
            
            for(Usuario u: usuarios){
                
                if(u.getId() == idUsuEdit){
                    
                    usuarioEdit = u;
                }
            }
            
            request.getSession().setAttribute("usuarioEdit", usuarioEdit);
            
        }
        else{
            request.getSession().setAttribute("usuarioEdit", null);
        }
        request.getSession().setAttribute("Scroll",true);
        
        return "/ABMUsuarios.jsp";


    }
    
}
