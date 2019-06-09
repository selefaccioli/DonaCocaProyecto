/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class SendMail {
     public static boolean send(String to, String sub, String msg, final String user,final String pass) throws DonaCocaException
    {  
     String mje;
     Properties props = new Properties();
     
     props.put("mail.smtp.host", "webmail.donacocafinaljava.com");
     //below mentioned mail.smtp.port is optional
     //props.put("mail.smtp.port", "26");		
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     
    Session session = Session.getInstance(props,new javax.mail.Authenticator()
    {
  	  protected PasswordAuthentication getPasswordAuthentication() 
  	  {
  	 	 return new PasswordAuthentication(user,pass);
  	  }
   });
    
   try
   {
     MimeMessage message = new MimeMessage(session);
       message.setFrom(new InternetAddress(user));
       message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
       message.setSubject(sub);
       message.setText(msg);
       message.setContent(msg,"text/html; charset=utf-8");
       
       Transport.send(message);
       return true;
 
    }
    catch(Exception e)
    {
        throw new DonaCocaException(e.getMessage(),e);
    }
  }  
}
