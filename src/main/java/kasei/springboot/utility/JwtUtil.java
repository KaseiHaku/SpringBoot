package kasei.springboot.utility;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT(JSON Web Token)
 * 结构：Header.Payload.Signature
 * 结构：xxxxxxxx.yyyyyyyy.zzzzzzzz
 * Header: {"alg":"HS256", "typ":"JWT"}
 * Payload: 包含 Registered Claim, Public Claim, Private Claim
 * {"iss":"发行者", "exp":"到期时间", "sub":"主题", "aud":"受众", "custom":"自定义参数"}
 * Signature: "由 Header 和 Payload 经过算法计算出来的值"
 *
 *
 * JWT Generate Algorithm:
 * JWT = base64UrlEncode(Header) + "." +
 *      base64UrlEncode(Payload) + "." +
 *      HS256(base64UrlEncode(Header), base64UrlEncode(Payload), "salt") // signature = 前两个的 base64 编码 加盐 后 用哈希算法产生
 *
 * */

public class JwtUtil {

    public static final String JWT_SALT = "JWT Salt";


    public static SecretKey generateKey(){
        byte[] saltByteAry = JWT_SALT.getBytes();
        SecretKey key = new SecretKeySpec(saltByteAry, "AES256");

        // String base64Key = Base64.getUrlEncoder().encodeToString(key.getEncoded());
        // System.out.println(base64Key);
        return key;
    }

    public static String createJWT(Map<String, Object> claims){

        if (claims == null) {
            claims = new HashMap<>() ;
            claims.put("default", "there is no private claim");
        }


        Date now = new Date(System.currentTimeMillis()); // 生成 JWT 的时间
        Date expired = new Date(now.getTime() + 1000L * 60L * 60L);
        Date notBefore = new Date(now.getTime() + 1000L);

        Map<String, Object> headerParams = new HashMap<>() ;
        headerParams.put("alg","HS256");
        headerParams.put("typ", "JWT");


        JwtBuilder jwtBuilder = Jwts.builder()
                /** Header */
                .setHeaderParams(headerParams)

                /** Payload: Registered Claim */
                .setIssuer("Kasei")// 发行人
                .setSubject("Login")// 发行主题
                .setAudience("Client") // 受众
                .setExpiration(expired)// 过期时间
                .setNotBefore(notBefore)// 代表这个 JWT 的生效开始时间，在 创建时间 和 过期时间之间的一个节点
                .setIssuedAt(now) // 创建时间
                .setId(UUID.randomUUID().toString())// JWT ID

                /** Payload: Private Claim */
                .setClaims(claims)

                /** Signature */
                .signWith(SignatureAlgorithm.HS256, generateKey());

        return jwtBuilder.compact();
    }


    public static Claims parseJWT(String compactJwt){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(compactJwt);
        Claims body = claimsJws.getBody();
        return body;
    }


}
