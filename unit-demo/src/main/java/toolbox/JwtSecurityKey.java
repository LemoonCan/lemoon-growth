package toolbox;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * @author lemoon
 * @since 2025/4/28
 */
public class JwtSecurityKey {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 生成安全的Key
        // 把Key转成Base64编码的字符串
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Base64-encoded secret key: " + base64Key);

    }
}
