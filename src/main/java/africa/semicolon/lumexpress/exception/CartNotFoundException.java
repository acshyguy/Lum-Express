package africa.semicolon.lumexpress.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message, Long cartId) {
        super(message);
    }
}
