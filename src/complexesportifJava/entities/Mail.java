/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.entities;
import javax.mail.internet.*; 
import java.util.Properties;  
import javax.mail.*;   

/**
 *
 * @author oussa
 */
public class Mail {
    public static void send(String from,String pwd,String to,String sub,String msg){
    //Propriétés
    Properties properies = new Properties();
    properies.put("mail.smtp.host", "smtp.gmail.com");
    properies.put("mail.smtp.port", "465");
    properies.put("mail.smtp.auth", "true");
    properies.put("mail.smtp.ssl.protocols", "TLSv1.2");
    properies.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    //Session
    Session s = Session.getDefaultInstance(properies,
      new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(from, pwd);
      }
    });
    //composer le message
    try {
      MimeMessage m = new MimeMessage(s);
      m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
      m.setSubject(sub);
      m.setText(msg);
      //envoyer le message
      Transport.send(m);
      System.out.println("Message envoyé avec succès");
    } catch (MessagingException e) {
        System.out.println(e);;
    }
  }
}