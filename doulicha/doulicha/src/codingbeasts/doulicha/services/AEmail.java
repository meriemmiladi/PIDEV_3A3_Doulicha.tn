///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package codingbeasts.doulicha.services;
//    
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import com.sun.mail.util.MessageRemovedIOException;
//
//public class AEmail {
//
// 
//private String username =("mohamedazyz.belaidi@esprit.tn");
//private String password =("ucjenrtgssrqxkxy");
//public void envoyer() {
//// Etape 1 : Création de la session
//Properties props = new Properties();
//props.put("mail.smtp.auth", "true");
//props.put("mail.smtp.starttls.enable","true");
//props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//props.put("mail.smtp.host","smtp.gmail.com");
//props.put("mail.smtp.port","587");
//Session session = Session.getInstance(props,
//new javax.mail.Authenticator() {
//protected PasswordAuthentication getPasswordAuthentication() {
//return new PasswordAuthentication(username, password);
//}
//});
//try {
//// Etape 2 : Création de l'objet Message
//Message message = new MimeMessage(session);
//message.setFrom(new InternetAddress("mohamedazyz.belaidi@esprit.tn"));
//message.setRecipients(Message.RecipientType.TO,
//InternetAddress.parse("azyzbel@gmail.com"));
//message.setSubject("Test email");
//message.setText("Bonjour, ce message est un test ...");
//// Etape 3 : Envoyer le message
//Transport.send(message);
//System.out.println("Message_envoye");
//} catch (MessagingException e) {
//throw new RuntimeException(e);
//} }
//////Etape 4 : Tester la méthode
////public static void main(String[] args) {
////AEmail test = new AEmail();
////test.envoyer();
////} 
//}
//                
//    
//

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AEmail{

    private String fromEmail;
    private String password;
    private String toEmail;
    private String subject;
    private String message;

    public AEmail(String fromEmail, String password, String toEmail, String subject, String message) {
        this.fromEmail = fromEmail;
        this.password = password;
        this.toEmail = toEmail;
        this.subject = subject;
        this.message = message;
    }

    public void sendEmail() throws MessagingException {
        // Configuration des propriétés SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Configuration de l'authentification
        Session session = Session.getInstance(props,
                new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Création du message
        Message e_message = new MimeMessage(session);
        e_message.setFrom(new InternetAddress(fromEmail));
        e_message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        e_message.setSubject(subject);
        e_message.setText(this.message);

        // Envoi du message
        Transport.send(e_message);
    }
}