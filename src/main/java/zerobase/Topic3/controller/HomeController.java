package zerobase.Topic3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  // 기본 페이지 요청 메서드

  @GetMapping("/") // 기본 주소 요청이 오면 (1)
  public String Main() { // 메서드 호출 (2)
    return "Main"; // => templates 폴더의 Main.html 을 찾아감
  }
}
