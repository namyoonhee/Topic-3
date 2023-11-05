package zerobase.Topic3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
  @GetMapping("/member/save")
  public String saveForm() {
    return "detail";
  }
}
