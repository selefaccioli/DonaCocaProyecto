/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author selef
 */
public abstract class Comando {
        public abstract String ejecutar(HttpServletRequest request, HttpServletResponse response);    

}
