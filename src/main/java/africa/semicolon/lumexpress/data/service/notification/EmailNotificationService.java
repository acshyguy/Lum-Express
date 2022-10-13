package africa.semicolon.lumexpress.data.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;

public interface EmailNotificationService {
    String sendHtmlMail(EmailNotificationRequest emailDetails);
}
