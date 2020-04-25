package club.deepblue.twilight.controller;


import club.deepblue.twilight.pojo.User;
import club.deepblue.twilight.utils.RequestUtil;
import club.deepblue.twilight.utils.TwFileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

  @Autowired
  RedisTemplate redisTemplate;


  /**
   * 上传文件
   *
   * @param file
   * @return
   */
  @PostMapping("/upload")
  public JSONObject fileUpload(HttpServletRequest request, @NotNull MultipartFile file) {
    JSONObject jsonObject=null;
    try {
      //将文件转成文件流
      InputStream inputStream=file.getInputStream();
      //文件后缀
      String extName=StrUtil.subAfter(file.getOriginalFilename(), ".", true);
      ValueOperations valueOperations = redisTemplate.opsForValue();
      User user = (User) valueOperations.get(RequestUtil.getSession(request));
      String savePath = String.valueOf(user.getU_id());
      String fileName=TwFileUtil.saveFileMD5(inputStream,savePath,extName);
      if (!(null ==fileName)){
        jsonObject=new JSONObject();
        jsonObject.put("errcode",0);
        jsonObject.put("fileName",fileName);
        return jsonObject;
      }else {
        jsonObject=new JSONObject();
        jsonObject.put("errcode",52);
        jsonObject.put("fileName",fileName);
        return jsonObject;
      }
    } catch (IOException e) {
      e.printStackTrace();
      jsonObject = new JSONObject();
      jsonObject.put("errcode", 51);
      return jsonObject;
    }
  }


  /**
   * 文献下载
   *
   * @return
   */
  @GetMapping("/download")
  public ResponseEntity fileDownload(HttpServletRequest request, @RequestParam String fileName) {
    ValueOperations valueOperations = redisTemplate.opsForValue();
    User user = (User) valueOperations.get(RequestUtil.getSession(request));
    String savePath = String.valueOf(user.getU_id());
    File file = new File(savePath, fileName);
    if (!file.exists()) {
      return ResponseEntity.notFound().build();
    }
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    //chrome浏览器下载文件可能出现：ERR_RESPONSE_HEADERS_MULTIPLE_CONTENT_DISPOSITION，
    //产生原因：可能是因为文件名中带有英文半角逗号,
    //解决办法：确保 filename 参数使用双引号包裹[1]
    headers.add("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    return ResponseEntity
      .ok()
      .headers(headers)
      .contentType(MediaType.parseMediaType("application/octet-stream"))
      .body(new FileSystemResource(file));
  }
}
