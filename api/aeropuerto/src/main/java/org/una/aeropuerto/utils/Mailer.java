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
    
    public static void sendMail(String link, String destino) { //link de activacion
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
            
            mensage.setContent(getContent(link),"text/html");
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
        "            display: flex;\n" +
        "            justify-content: center;\n" +
        "            background-image: url(https://i2.cnnturk.com/i/cnnturk/75/0x0/5e2be80070380e0c60f58c56.jpg);\n" +
        "            background-size: 400px 600px;\n" +
        "            width: 400px;\n" +
        "            height: 600px;\n" +
        "            align-items: center;\n" +
        "            border-radius: 15px;\n" +
        "        }\n" +
        "        .secundario{\n" +
        "            background-color: black;\n" +
        "            opacity: 0.7;\n" +
        "            border-radius: 15px;\n" +
        "            align-items: center;\n" +
        "            height: 600px;\n" +
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
        "                    <br><br><img src=\"https://mobile.servientrega.com/WebSitePortal/assets/img/icons/icon_cambiocontrasena.png\" width=\"300\">\n" +
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
    
    public static String getRespuesta(String nombre, String temp){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Respuesta</title>\n" +
                "    <style>\n" +
                "        .cont{\n" +
                "            background-color: rgb(42, 45, 77);\n" +
                "            border-radius: 15px;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "        .text{\n" +
                "            text-align: CENTER;\n" +
                "            color: white;\n" +
                "            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\n" +
                "        }\n" +
                "        .cont2{\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            background-color: white;\n" +
                "            text-align: CENTER;\n" +
                "            border-radius: 15px;\n" +
                "            height: 100px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"cont\">\n" +
                "        <div>\n" +
                "            <h1 class=\"text\" style=\"font-size: 26pt;\">Sistema Aeropuerto</h1>\n" +
                "            <blockquote class=\"text\" style=\"font-size: 16pt;\">\n" +
                "                "+nombre+", el cambio de contraseña ha sido exitoso\n" +
                "                Aquí tiene su nueva contraseña:\n" +
                "            </blockquote>\n" +
                "            <div class=\"cont2\">\n" +
                "                <blockquote style=\"font-size: 16pt;\">\n" +
                "                    Contraseña Temporal: "+temp+"\n" +
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
