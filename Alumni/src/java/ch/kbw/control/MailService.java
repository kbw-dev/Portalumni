package ch.kbw.control;

import ch.kbw.dao.UserDAO;
import ch.kbw.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    private String sender;
    private String password;
    private List<User> newsletterReceivers;

    private Properties mailServerSettings;

    public MailService() {
        this.sender = "info@portalumni.ch";
        this.password = "Ulgada66";
        this.mailServerSettings = new Properties();
        this.mailServerSettings.put("mail.smtp.host", "server36.hostfactory.ch");
        this.mailServerSettings.put("mail.smtp.socketFactory.port", "587");
        this.mailServerSettings.put("mail.smtp.starttls.enable", true);
        this.mailServerSettings.put("mail.smtp.auth", true);
        this.mailServerSettings.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.mailServerSettings.put("mail.smtp.auth", "true");
        this.mailServerSettings.put("mail.smtp.port", "587");

        this.newsletterReceivers = new ArrayList<>();

    }

    public void addUsersIntoNewsletterReceivers() {
        System.err.println("TEST: " + userDAO.getCurrentUser().getFirstName());
        for (User c : this.userDAO.getAllUsers()) {
            if (c.wantsNewsletter()) {
                newsletterReceivers.add(c);
            }
        }
    }

    public void sendCircularMail() {

        addUsersIntoNewsletterReceivers();

        Session session = Session.getInstance(mailServerSettings,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            for (User c : newsletterReceivers) {
                message.addRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(c.getEmail()));

            }

            message.setSubject(mailHeader);
            message.setText(mailContent);

            Transport.send(message);
            System.out.println("Mail wurde gesendet.");
            mailHeader = "";
            mailContent = "";

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void mailToAdmin(String subject, String content) {

        Session session = Session.getInstance(mailServerSettings,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));

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
