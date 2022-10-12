package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@AllArgsConstructor
@Service
@Slf4j
public class GmailNotificationServiceImpl implements EmailNotificationService {
    private final JavaMailSender javaMailSender;

    @Override
    public String sendHtmlMail(EmailNotificationRequest emailDetails) {
        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setFrom("no-reply@email.lumExpress.com.ng");
            messageHelper.setTo(emailDetails.getUserEmail());
            messageHelper.setText(emailDetails.getMailContent(), true);
            javaMailSender.send(mimeMessage);
            return String.format("email sent to %s successfully",
                    emailDetails.getUserEmail());

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return String.format("email not sent to %s successfully",
                emailDetails.getUserEmail());
    }
}
