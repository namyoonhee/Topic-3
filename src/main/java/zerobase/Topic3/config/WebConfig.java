package zerobase.Topic3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정 클래스로 등록
public class WebConfig implements WebMvcConfigurer {
  private String resourcePath = "/upload/**"; // view 에서 접근할 경로
  private String savePath = "file:///Users/YoonHeeNam/springboot_img/"; // 실제 파일 저장 경로

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
  }
}
