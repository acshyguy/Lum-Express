package africa.semicolon.lumexpress.data.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;

public interface VerificationTokenService {
    VerificationToken createToken(String userEmail);
    boolean isValidVerificationToken (String token);

}
