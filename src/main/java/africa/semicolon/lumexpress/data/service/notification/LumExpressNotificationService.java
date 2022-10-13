package africa.semicolon.lumexpress.data.service.notification;

import africa.semicolon.lumexpress.data.dto.request.NotificationRequest;

public interface LumExpressNotificationService {
    String send(NotificationRequest notificationRequest);
}
