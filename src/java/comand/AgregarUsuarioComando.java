/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class AgregarUsuarioComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        CtrlUsuario ctrlU = new CtrlUsuario();
        
        boolean existeUsuario = true;
        
        try {
            existeUsuario = ctrlU.existeUsuario(request.getParameter("usuUsu"));
        } catch (DonaCocaException ex) {
            request.setAttribute("ex", ex.getMessage());
            return "/ABMUsuarios.jsp";
        }
        
        Usuario usNuevo = new Usuario();

        usNuevo.setNombre(request.getParameter("nomUsu"));
        usNuevo.setApellido(request.getParameter("apeUsu"));
        usNuevo.setDni(Integer.parseInt(request.getParameter("dniUsu")));
        usNuevo.setUsuario(request.getParameter("usuUsu"));
        Boolean esAdmin = (request.getParameter("admin")!=null);
        Boolean esActivo = (request.getParameter("activo")!=null);
        usNuevo.setEsAdmin(esAdmin);
        usNuevo.setActivo(esActivo);
        usNuevo.setContrasenia(request.getParameter("Contra1"));
        usNuevo.setMail(request.getParameter("mailUsu"));
        usNuevo.setTelefono(request.getParameter("telUsu"));
        usNuevo.setDireccion(request.getParameter("dicUsu"));
         SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = formato.parse(request.getParameter("fecNac"));
            usNuevo.setFechaNacimiento(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        usNuevo.setConocimiento(request.getParameter("conocimiento"));
        ArrayList<Usuario> usuarios;
        
        if(!existeUsuario){
            
            try {
                ctrlU.registrarUsuario(usNuevo);
                usuarios = ctrlU.obtenerUsuarios();
            } catch (DonaCocaException ex) {
                request.setAttribute("ex", ex.getMessage());
                return"/ABMUsuarios.jsp";
            }
            
            
            request.getSession().setAttribute("listaUsuarios", usuarios);
            request.setAttribute("ExitoUsuario", true);
        }
        else{
            request.setAttribute("usuarioPorAgregar", usNuevo);
            request.setAttribute("ExitoUsuario", false);    
        }
         request.getSession().setAttribute("Scroll",true);
        return "/ABMUsuarios.jsp";



    }
    
}
