package club.deepblue.twilight.config;

import club.deepblue.twilight.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
  @Autowired
  SessionInterceptor sessionInterceptor;
  @Value("${tw.file-path-prefix}")
  public String uploadedFilePathPrefix;

  protected void addInterceptors(InterceptorRegistry registry) {
    // 多个拦截器组成一个拦截器链
    // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
    // excludePathPatterns 用户排除拦截
    registry.addInterceptor(sessionInterceptor).addPathPatterns("/**")
      .excludePathPatterns("/user/login", "/user/register", "/images/**","/user/searchUsers");
    super.addInterceptors(registry);
  }

  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/**").addResourceLocations("file:" + uploadedFilePathPrefix + "/images/", "file:" + uploadedFilePathPrefix + "/data/images/");
    // registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/data/images/");
  }
}
