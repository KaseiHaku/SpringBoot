package kasei.springboot.utility;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MessageDigestUtil {


    public static String generateMD(String content) throws NoSuchAlgorithmException {
        return generateMD(content.getBytes(Charset.forName("utf8")));
    }


    public static String generateMD(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        String base64Digest = Base64.getUrlEncoder().encodeToString(digest);
        return base64Digest;
    }

}
