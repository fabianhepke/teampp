package com.example.team.help;

import android.os.AsyncTask;

import com.example.team.components.User;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender extends AsyncTask<User, Void, Void> {

    @Override
    protected Void doInBackground(User... users) {
        final String username = "teampp.noreply@gmail.com";
        final String password = "memevzh10";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("teampp.noreply@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(users[0].geteMail()));
            message.setSubject("Team++ Konto verifizieren!");
            message.setText("Herzlich Willkommen bei Team++!\nKlicken sie auf folgenden Link um ihr Konto zu verifizieren:\nhttps://www.memevz.h10.de/verify.php?token=" + users[0].getVerifyToken().getToken() + "\nDanke, dass sie sich bei uns registriert haben!\n\nIhr Team++ Team;)");

            Transport.send(message);

            System.out.println("Mail Sent Successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
