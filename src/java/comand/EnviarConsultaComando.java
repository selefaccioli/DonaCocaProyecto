/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.SendMail;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class EnviarConsultaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
                String nombreUsu = request.getParameter("nombre");
                String destinatario = request.getParameter("email");
                String asunto = request.getParameter("asunto");
                String message = request.getParameter("message");
                String mensaje;

try{
           
            
           
            final String pass = "f37453481";
            String user = "msolnieto@donacocafinaljava.com";

            

            if(SendMail.send(destinatario,asunto,message,user,pass)){
                 mensaje = "Se ha enviado el mail correctamente";
                 SendMail.send(user,asunto,message,user,pass);
            }else{
                 mensaje = "Ha ocurrido un error y no se ha podido enviar el mail.";
            }
            }catch(DonaCocaException ex){
            request.setAttribute("ex", ex.getMessage());
                    return "/pedidoDetalle.jsp";
            }

             request.getSession().setAttribute("exitoConsulta", true);
             return "/pedidoDetalle.jsp";

    }
    
}
