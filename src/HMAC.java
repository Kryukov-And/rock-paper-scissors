import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class HMAC {

    public static String execute(String compChoice) {
        String key = generateKey();
        System.out.println("HMAC:");
        System.out.printf("%064X%n", new BigInteger(1, encodeChoice(key, compChoice)));
        return key;
    }

    public static String generateKey() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            key.append(generateDigit());
        }
        return key.toString();
    }

    private static char generateDigit() {
        SecureRandom secureRandom = new SecureRandom();
        int digit = secureRandom.nextInt(16);
        return (digit < 10) ? (char) (digit + 48) : (char) (digit + 55);
    }

    public static byte[] encodeChoice(String key, String choice) {
        byte[] message = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            message = mac.doFinal(choice.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return message;
    }
}
