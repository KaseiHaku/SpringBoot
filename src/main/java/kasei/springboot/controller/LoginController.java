package kasei.springboot.controller;

import kasei.springboot.utility.JwtUtil;
import kasei.springboot.utility.MessageDigestUtil;
import kasei.springboot.utility.UniversalResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Login")
public class LoginController {

    @RequestMapping(value={"/authenticateByAccountPwd"}, method= {RequestMethod.GET, RequestMethod.POST}) // 生产环境不能用 GET 方法
    UniversalResponse authenticateByAccountPwd(@RequestParam String account, @RequestParam String pwd) throws NoSuchAlgorithmException {

        /* 根据 user 从数据库中获取该用户的 salt(盐)*/
        String salt = "1234";   // 假设用户的盐为该值

        /* 把 pwd 和 salt 合并在一起，计算 hash 值 */
        String md = MessageDigestUtil.generateMD(account+pwd+salt);


        /* 将得到的 hash 值与数据库中保存的 hash 值做比较，如果一样认证通过 */
        String dbMD = "Bb6F9SDzD0VmnPKs09SICHKlrPYpbPnmTEPx_ewTcH0=";  // 假设数据库中的摘要信息为该值，account=Kasei pwd=Haku salt=1234

        if (md.equals(dbMD)) {
            /* 生成一个 JWT 返回给客户端 */
            Map<String, Object> claims = new HashMap<>();
            claims.put("account", account);
            String jwt = JwtUtil.createJWT(claims);
            return new UniversalResponse(0, "success", jwt);
        } else {
            return new UniversalResponse(1, "验证不通过", null);
        }
    }

    @RequestMapping(value={"/authenticateByJwt"}, method= {RequestMethod.GET, RequestMethod.POST})
    UniversalResponse authenticateByJwt(@RequestParam String jwt)  {
        try {
            JwtUtil.parseJWT(jwt);
            return new UniversalResponse(0, "success", null);
        } catch (Exception e) {
            return new UniversalResponse(1, "认证不通过", null);
        }
    }

}
