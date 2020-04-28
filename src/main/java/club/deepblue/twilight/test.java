package club.deepblue.twilight;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class test {
  private RestTemplate restTemplate;

  @Test
  public void test()  {
    Integer i=12;
    Integer j= i +2;
    System.out.println(j);
  }

  public String getFile(String url)  {
    String RELATIVELY_PATH = System.getProperty("user.dir") +  File.separatorChar+"src"+ File.separatorChar+"main"+ File.separatorChar+"resources"+ File.separatorChar+"static"+ File.separatorChar+"data"+ File.separatorChar;

    restTemplate = new RestTemplate();
    ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
    MediaType mediaType=rsp.getHeaders().getContentType();
    String md5File = DigestUtil.md5Hex(rsp.getBody());
    String fileName=md5File+"."+mediaType.getSubtype();
    try {
      Files.write(Paths.get(RELATIVELY_PATH+fileName),rsp.getBody());
      return fileName;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
