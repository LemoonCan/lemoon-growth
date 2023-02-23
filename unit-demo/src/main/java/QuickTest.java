import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 * @author lee
 * @date 2022/9/29
 */
public class QuickTest {
    public static void main(String[] args) throws UnsupportedEncodingException, GeneralSecurityException {
        String x = "\n请联系金融专员[#{customerManagerName} #{customerManagerPhone}]发送签约短信";
        x = x.replace("#{customerManagerName}","8888");
        System.out.println(x);

//        boolean flag = Pattern.matches("^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[1|8|9]))\\d{8}$",
//                "13777o34811");
//        System.out.println(flag);

        

        // 原文:
        String message = "{\"ruleId\":1000L,\"ruleVersion\":\"1.0\",\"priceType\":3,\"downPaymentAmount\":1000000,\"installment\":100000,\"interestRate\":13,\"loanAmount\":12000000,\"term\":36,\"carTransactionPrice\":13000000,\"investProductCode\":\"8888\",\"transactionPrice\":14000000,\"gpsCost\":100000}";
        System.out.println("Message: " + message);
        // 128位密钥 = 16 bytes Key:
        byte[] key = "1234567890abcdef".getBytes("UTF-8");
        // 加密:
        byte[] data = message.getBytes("UTF-8");
        byte[] encrypted = encrypt(key, data);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        // 解密:
        byte[] decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));

    }

    // 加密:
    public static byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    // 解密:
    public static byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }
}
