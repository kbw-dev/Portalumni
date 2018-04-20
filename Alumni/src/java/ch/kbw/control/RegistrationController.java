/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jophil George
 */
@ManagedBean(name = "registrierungController")
@SessionScoped

public class RegistrationController {

    private String vorname;
    private String nachname;
    private String email;
    private String passwort1;
    private String passwort2;
    private String passwortVerschlüsselt;
    private static int offset;
    private String error;
    private String retVal;

    public RegistrationController() {
        vorname = "";
        nachname = "";
        email = "";
        passwort1 = "";
        passwort2 = "";
        passwortVerschlüsselt = "";
        offset = 5;
        error = "";
    }

    public String anfrageSenden() throws InterruptedException {

        if (vorname.isEmpty() || nachname.isEmpty() || email.isEmpty() || passwort1.isEmpty() || passwort2.isEmpty()) {
            error = "Füllen Sie das Formular ganz aus.";
        } else {
            if (checkPasswort() == true && checkEmail() == true) {

                final String benutzername = "info@portalumni.ch";         //VON DIESER ADRESSE WIRD EMAIL GESENDET
                final String passwort = "Ulgada66";                      //PASSWORT VON DIESER ADRESSE
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.setProperty("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "server36.hostfactory.ch");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getInstance(properties,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(benutzername, passwort);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(email));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jophil@gmail.com"));           //AN DIESE ADRESSE WIRD EMAIL GESENDET
                    message.setSubject("Alumni Registrierung");
                    message.setText(" Vorname: " + vorname + " \n Nachname: " + nachname + " \n Email: " + email + " \n Passwort: " + passwortVerschlüsseln());
                    Transport.send(message);
                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
                retVal = "index.xhtml";
                Thread.sleep(5000);
                resetForm();
            } else {
                retVal = "registrierung.html";
            }
        }

        return retVal;
    }

    public boolean checkPasswort() {

        if (passwort1.equals(passwort2)) {
            if (passwort1.length() < 8) {

                error = "Die Passwortanforderungen wurden nicht erfüllt.";
                return false;
            }

            String grossbuchstaben = "[A-Z]";
            Pattern uppCasePattern = Pattern.compile(grossbuchstaben);
            Matcher m = uppCasePattern.matcher(passwort1);
            if (!m.find()) {

                error = "Die Passwortanforderungen wurden nicht erfüllt.";
                return false;
            }

            String kleinbuchstaben = "[a-z]";
            Pattern lowerCasePattern = Pattern.compile(kleinbuchstaben);
            Matcher m2 = lowerCasePattern.matcher(passwort1);
            if (!m2.find()) {

                error = "Die Passwortanforderungen wurden nicht erfüllt.";
                return false;
            }

            String zahlen = "[0-9]";
            Pattern numberCasePattern = Pattern.compile(zahlen);
            Matcher m3 = numberCasePattern.matcher(passwort1);
            if (!m3.find()) {

                error = "Die Passwortanforderungen wurden nicht erfüllt.";
                return false;
            }

            String symbole = "[+\"*ç%&/()=?^$£<>¦@#°§¬|¢´~]";
            Pattern symbolCasePattern = Pattern.compile(symbole);
            Matcher m4 = symbolCasePattern.matcher(passwort1);
            if (!m4.find()) {

                error = "Die Passwortanforderungen wurden nicht erfüllt.";
                return false;
            }
        } else {
            error = "Die Passwörter stimmen nicht überein.";
            return false;
        }

        return true;
    }

    public boolean checkEmail() {
        boolean result = true;
        try {
            InternetAddress emailAdr = new InternetAddress(email);
            emailAdr.validate();
        } catch (AddressException ex) {
            error = "Die angegebene E-Mail Adresse ist ungültig.";
            result = false;
        }
        return result;
    }

    public String passwortVerschlüsseln() {

        char[] meinArray = passwort1.toCharArray();

        char[] cryptArray = new char[meinArray.length];
        for (int i = 0; i < meinArray.length; i++) {

            int verschiebung = (meinArray[i] + offset) % 128;
            // ursprüngliches Zeichen plus Offset modulo 128

            cryptArray[i] = (char) (verschiebung);
        }
        for (int i = 0; i < cryptArray.length; i++) {

            passwortVerschlüsselt += cryptArray[i];
        }

        return passwortVerschlüsselt;
    }

    public void resetForm() {
        vorname = "";
        nachname = "";
        email = "";
        passwort1 = "";
        passwort2 = "";
        error = "";
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort1() {
        return passwort1;
    }

    public void setPasswort1(String passwort1) {
        this.passwort1 = passwort1;
    }

    public String getPasswort2() {
        return passwort2;
    }

    public void setPasswort2(String passwort2) {
        this.passwort2 = passwort2;
    }

    public String getPasswortVerschlüsselt() {
        return passwortVerschlüsselt;
    }

    public void setPasswortVerschlüsselt(String passwortVerschlüsselt) {
        this.passwortVerschlüsselt = passwortVerschlüsselt;
    }

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        RegistrationController.offset = offset;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRetVal() {
        return retVal;
    }

    public void setRetVal(String retVal) {
        this.retVal = retVal;
    }

}
