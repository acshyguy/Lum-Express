package africa.semicolon.lumexpress.util;

import java.security.SecureRandom;

public class LumExpressUtils {
    public static String generateToken(){
        SecureRandom secureRandom = new SecureRandom();
        int number = secureRandom.nextInt(89999);
        return String.valueOf(10000+number);
    }
}
