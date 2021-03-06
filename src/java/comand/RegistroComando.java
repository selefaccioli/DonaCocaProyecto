/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RegistroComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlUsuario ctrlU = new CtrlUsuario();
        Usuario usu = new Usuario();
        
        boolean existeUsuario = true;
        
        try {
            existeUsuario = ctrlU.existeUsuario(request.getParameter("usuUsu"));
        } catch (DonaCocaException ex) {
            request.setAttribute("ex", ex.getMessage() );
            return "/signup.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(RegistroComando.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        usu.setNombre(request.getParameter("nomUsu"));
        usu.setApellido(request.getParameter("apeUsu"));
        usu.setDni(Integer.parseInt(request.getParameter("dniUsu")));
        usu.setMail(request.getParameter("mailUsu"));
        usu.setUsuario(request.getParameter("usuUsu"));
        usu.setContrasenia(request.getParameter("Contra1"));
        usu.setDireccion(request.getParameter("direcUsu"));
        usu.setTelefono(request.getParameter("telUsu"));
        usu.setActivo(true);
        usu.setEsAdmin(false);
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = formato.parse(request.getParameter("fecNac"));
            usu.setFechaNacimiento(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        usu.setConocimiento(request.getParameter("conocimiento"));
        
        if(!existeUsuario){
            try {
                ctrlU.registrarUsuario(usu);
            } catch (DonaCocaException ex) {
                request.setAttribute("ex", ex.getMessage());
                return "/signup.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(RegistroComando.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getSession().setAttribute("usuario", usu);
            request.getSession().setAttribute("exitoRegistro", true);
        }
        else{
            request.setAttribute("usuarioPorRegistrar", usu);
            request.setAttribute("exitoRegistro", "El usuario ya existe");
            return "/signup.jsp";
            
        }
        
        return "/home.jsp";

    }
    
}
