package kasei.boot.web.controller;

import kasei.boot.web.utility.JwtUtil;
import kasei.boot.web.utility.MessageDigestUtil;
import kasei.boot.web.utility.UniversalResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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



    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        String fileName = URLEncoder.encode("SpringBootFileDownload.txt", "utf-8");
        response.addHeader("Content-Disposition","attachment;filename=" + fileName); // 表示时附件，需要下载


        ServletOutputStream sos = response.getOutputStream();
        String fileContent = "123214423";
        ByteArrayInputStream bis = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("utf8")));

        IOUtils.copy(bis, sos);
        response.flushBuffer();
    }


    @PostMapping(value = "/singleUpload")
    UniversalResponse singleUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        String content = new String(bytes, Charset.forName("utf8"));
        System.out.println(content);
        return new UniversalResponse(0, "success", content);
    }

    @PostMapping("/multiUpload")
    UniversalResponse multiUpload(HttpServletRequest request) throws IOException {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        Iterator<MultipartFile> iterator = files.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            MultipartFile next = iterator.next();
            if(next.isEmpty()){
                return new UniversalResponse(1, "has file upload failure", null);
            }
            sb.append(next.getOriginalFilename() + ": " + new String(next.getBytes(), Charset.forName("utf8")) + "\n");
        }
        return new UniversalResponse(0, "success", sb.toString());
    }

    @GetMapping
    UniversalResponse remoteInvoke(){

        RestTemplate restTemplate = new RestTemplate();

        return new UniversalResponse();
    }


}
