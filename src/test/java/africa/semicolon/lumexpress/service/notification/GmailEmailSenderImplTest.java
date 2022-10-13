package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;
import africa.semicolon.lumexpress.data.service.notification.EmailNotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GmailEmailSenderImplTest {
    @Autowired
    private EmailNotificationService emailSender;

    @Test
    void sendHtmlMail() {
        EmailNotificationRequest emailDetails = new EmailNotificationRequest();
        emailDetails.setUserEmail("roseadeyinka01@gmail.com");
        emailDetails.setMailContent("Hi elderly whimp!");
        String response = emailSender.sendHtmlMail(emailDetails);
        assertThat(response.contains("successfully")).isTrue();
    }
}