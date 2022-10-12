package africa.semicolon.lumexpress.exception;

import africa.semicolon.lumexpress.data.models.VerificationToken;

public class VerificationTokenException extends RuntimeException{
    public VerificationTokenException (String message){
        super(message);
    }
}
