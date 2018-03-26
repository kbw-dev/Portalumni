package ch.kbw.control;

import ch.kbw.dao.UserDAO;
import ch.kbw.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Adel
 */
@Named
@RequestScoped
public class MailService {

    @Inject
    private UserDAO userDAO;
    private String mailContent;
    private String mailHeader;
    private List<User> newsletterReceivers;

    public MailService() {

        newsletterReceivers = new ArrayList<>();

    }

    public void sendCircularMail() {

        System.err.println("TEST: " + userDAO.getCurrentUser().getFirstName());
        for (User c : userDAO.getAllUsers()) {
            if (c.wantsNewsletter()) {
                newsletterReceivers.add(c);
            }
        }
        
        //MAIL SERVER SETTINGS
        Properties props = new Properties();
        props.put("mail.smtp.host", "server36.hostfactory.ch");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("info@portalumni.ch", "Ulgada66");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@portalumni.ch"));
            for (User c : newsletterReceivers) {
                message.addRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(c.getEmail()));

            }

            message.setSubject(mailHeader);
            message.setText(mailContent);

            Transport.send(message);
            System.out.println("Mail wurde gesendet.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void mailToAdmin(String subject, String content){
        Properties props = new Properties();
        props.put("mail.smtp.host", "server36.hostfactory.ch");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("info@portalumni.ch", "Ulgada66");
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@portalumni.ch"));
            
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("erwin.eugster@kbw.ch"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("lena.hinnen@stud.kbw.ch"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("fabian.ulrich@stud.kbw.ch"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("lucian.nicca@stud.kbw.ch"));

            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Mail wurde gesendet.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getMailHeader() {
        return mailHeader;
    }

    public void setMailHeader(String mailHeader) {
        this.mailHeader = mailHeader;
    }

    public List<User> getNewsletterReceivers() {
        return newsletterReceivers;
    }

    public void setNewsletterReceivers(ArrayList<User> newsletterReceivers) {
        this.newsletterReceivers = newsletterReceivers;
    }

}
