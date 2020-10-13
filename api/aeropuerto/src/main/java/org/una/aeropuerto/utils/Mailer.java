/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public class Mailer {
    
    private static final String CORREO = "sistemaaeropuerto@gmail.com"; //correo de donde se envia
    private static final String PASSWORD = "ZU1F!J1mFtCN4Z5h%14z^#guQ0N1Rf"; 
    
    
    public static void sendCorreoSolicitud(String link, String destino){
        sendMail(destino, getContent(link));
    }
    
    public static void sendCorreoRespuesta(String nombre, String temp, String mensaje, String destino){
        sendMail(destino, getRespuesta(nombre, temp, mensaje));
    }
    
    public static void sendMail(String destino, String contenido) {
        try {
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.host", "smtp.gmail.com");
            propiedades.put("mail.smtp.auth", "true");
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "587");
            Session session = Session.getDefaultInstance(propiedades);
            MimeMessage mensage = new MimeMessage(session);
            
            InternetAddress from = new InternetAddress(CORREO);
            InternetAddress to = new InternetAddress(destino);
            mensage.addRecipient(Message.RecipientType.TO, to);
            mensage.setSubject("Enlace de recuperación de contraseña");
            
            mensage.setContent(contenido,"text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(CORREO, PASSWORD);
            transport.sendMessage(mensage, mensage.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException ex) {
            System.out.println("Error al enviar el correo: "+ex);
        }

    }
    
    public static String getContent(String link){
        String contenido = 
        "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <style>\n" +
        "        .contenedor{\n" +
        "            background-image: url(https://i2.cnnturk.com/i/cnnturk/75/0x0/5e2be80070380e0c60f58c56.jpg);\n" +
        "            max-width: 400px;\n" +
        "            background-size: 100% 100%;\n" +
        "            border-radius: 15px;\n" +
        "            display: flex;\n" +
        "            justify-content: center;\n" +
        "            margin: auto;\n"+
        "        }\n" +
        "        .secundario{\n" +
        "            width: 100%;\n" +
        "            background-color: black;\n" +
        "            opacity: 0.7;\n" +
        "            border-radius: 15px;\n" +
        "            align-items: center;\n"+
        "        }\n" +
        "        .texto{\n" +
        "            text-align: CENTER;\n" +
        "            color: aliceblue;\n" +
        "        }\n" +
        "        .parrafo{\n" +
        "            color: aliceblue;\n" +
        "            font-size: 15pt;\n" +
        "            text-align: justify;\n" +
        "        }\n" +
        "        .link{\n" +
        "            text-align: CENTER;\n" +
        "            color: aliceblue;\n" +
        "            font-size: 15pt;\n" +
        "        }\n" +
        "        #imgCandado{\n" +
        "            max-width: 300px;\n" +
        "            margin-left: 50px;\n" +
        "            margin-right: 50px;\n" +
        "        }\n"+
        "    </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "   <div class='contenedor'>\n" +
        "       <div class='secundario'>\n" +
        "            <div>\n" +
        "                <h1 class=\"texto\">Sistema de Activación</h1>\n" +
        "                <div></div>\n" +
        "                <blockquote class=\"parrafo\">\n" +
        "                    Buen día, el siguiente es un correo del sistema de recuperacion \n" +
        "                    de contraseñas de Sistema Aeropuerto\n" +
        "                    Usted ha solicitado el cambio de contraseña,\n" +
        "                    para ello ingrese al siguiente enlace donde\n" +
        "                    se procederá a atender su solicitud.\n" +
        "                    <br><br><img src=\"https://mobile.servientrega.com/WebSitePortal/assets/img/icons/icon_cambiocontrasena.png\" id=\"imgCandado\">\n" +
        "                </blockquote>\n" +
        "                <blockquote class=\"link\">\n" +
        "                    <br>Ingrese al enlace: <a href="+link+">Link para cambiar contraseña</a>\n" +
        "                </blockquote>\n" +
        "            </div>\n" +
        "       </div>\n" +
        "   </div> \n" +
        "</body>\n" +
        "</html>";
        return contenido;
    }
    
    public static String getRespuesta(String nombre, String temp, String mensaje){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Respuesta</title>\n" +
                "    <style>\n" +
                "        .cont{\n" +
                "            background-image: url(https://i2.cnnturk.com/i/cnnturk/75/0x0/5e2be80070380e0c60f58c56.jpg);\n" +
                "            max-width: 400px;\n" +
                "            background-size: 100% 100%;\n" +
                "            border-radius: 15px;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            margin: auto;\n" +
                "        }\n" +
                "        .text{\n" +
                "            text-align: CENTER;\n" +
                "            color: black;\n" +
                "            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\n" +
                "        }\n" +
                "        .cont2{\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            background-color: white;\n" +
                "            text-align: CENTER;\n" +
                "            border-radius: 15px;\n" +
                "            height: 150px;\n" +
                "            margin-left: 2em;\n" +
                "            margin-right: 2em;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"cont\">\n" +
                "        <div>\n" +
                "            <h1 class=\"text\" style=\"font-size: 26pt;\">Sistema Aeropuerto</h1>\n" +
                "            <blockquote class=\"text\" style=\"font-size: 16pt;\">\n" +
                "                "+nombre+", el cambio de contraseña ha sido exitoso\n" +
                "                "+mensaje+"\n" +
                "            </blockquote>\n" +
                "            <div class=\"cont2\">\n" +
                "                <blockquote style=\"font-size: 16pt;\">\n" +
                "                    "+temp+"\n" +
                "                </blockquote>\n" +
                "            </div>\n" +
                "            <blockquote class=\"text\" style=\"font-size: 16pt;\">\n" +
                "                Atención: La proxima vez que ingrese a la aplicacion debera cambiar su contraseña.\n" +
                "            </blockquote>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
