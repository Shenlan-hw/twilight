package club.deepblue.twilight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("club.deepblue.twilight.mapper")
public class TwilightApplication {
  public static void main(String[] args) {
    SpringApplication.run(TwilightApplication.class, args);
  }
}
