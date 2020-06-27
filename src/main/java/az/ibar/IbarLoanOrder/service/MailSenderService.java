package az.ibar.IbarLoanOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String message, String subject, String emails) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("IBAR <www.boost.az@gmail.com>");
            msg.setTo(emails);
            msg.setSubject(subject);
            msg.setText(message);
            javaMailSender.send(msg);
            System.out.println("Mail sent!");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
