/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author selef
 */
public class LogOutComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        
        for(Cookie c:cookies)
        {
            if(c.getName().equals("nomUsuarioDonaCoca"))
            {
                c.setValue(null);
                c.setMaxAge(-1);
                c.setPath("/");
                response.addCookie(c);
            }
            if(c.getName().equals("contraDonaCoca"))
            {
                c.setValue(null);
                c.setMaxAge(-1);
                c.setPath("/");
                response.addCookie(c);
            }
        }
        
        return "/index.jsp";
    }
    
}
