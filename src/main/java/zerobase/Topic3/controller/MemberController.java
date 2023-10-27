package zerobase.Topic3.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import zerobase.Topic3.dto.MemberDTO;
import zerobase.Topic3.service.MemberService;

@SuppressWarnings("checkstyle:MissingJavadocType")
@Controller
@RequiredArgsConstructor // 이 어노테이션이 만들어줌 (3)
public class MemberController { // 컨트롤러 생성자(MemberController)를 (2)
  // 생성자 주입 (컨트롤러 클레스가 서비스 클레스에 자원 (메서드나 필드 등 사용 할 수 있는 권한이 생긴다.)
  private final MemberService memberService; // 이 필드를 메개변수로 하는 (1)

  // 회원가입 페이지 출력 요청
  @GetMapping("/member/save1")
  public String save1Form() { // 화면민 필요하면 곧바로 return
    return "save1";
  }

  @SuppressWarnings({"checkstyle:MissingJavadocMethod", "checkstyle:AbbreviationAsWordInName"})
  @PostMapping("/member/save1") // DB 작업이 필요하게 되면 service repository 를 거치는 형태
  public String save1(@ModelAttribute MemberDTO memberDTO) {
    System.out.println("MemberController.save1");
    System.out.println("memberDTO = " + memberDTO);
    memberService.save1(memberDTO);
    return "login";
  }

  // /member/login 주소 요청이 왔을 때는("login") 로그인 페이지를 띄어주자
  @GetMapping("/member/login")
  public String loginFrom() {
    return "login";
  }

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MissingJavadocMethod"})
  @PostMapping("/member/login")
  public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
    MemberDTO loginResult = memberService.login(memberDTO); // 로그인을 성공 했을 때만 담아서 주겠다는 것
    // 로그인을 제대로 했나 못 했나에 따라서 나눠서 처리
    if (loginResult != null) {
      // login 성공 (main_page 이라는 html 페이지로)
      session.setAttribute("loginEmail", loginResult.getMemberEmail());
      // loginResult.getMemberEmail() (로그인한 회원)의 이메일 정보를 (loginEmail) 세션에 담아준다.
      return "success";
    } else {
      // login 실패 (로그인 페이지에 머물도록)
      return "login";
    }
  }

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MissingJavadocMethod"})
  @GetMapping("/member/") // 링크를 클릭하는 방식은 무조건 Get
  public String findAll(Model model) { // 스프링에서 실어 나르는 역할은 해주는 객체 (model)
    List<MemberDTO> memberDTOList = memberService.findAll(); // 회원은 여러개의 데이터를 가져옴, List 타입 사용
    // 여러개의 데이터를 가져올땐 List 형태를 많이 쓴다.
    // 어떠한 html 로 가져갈 데이터가 있다면 model 사용
    model.addAttribute("memberList", memberDTOList);
    return "List";
    // Model model 를 받아서 memberList 에 담아서 "List" html 로 넘어간다.
  }

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MissingJavadocMethod"})
  @GetMapping("/member/{id}") // {id} 경로에 있는 어떤 값을 취하겠다
  public String findById(
      @PathVariable Long id, Model model) { // 경로 상에 있는 값을 가져올때는 @PathVariable 어노테이션 사용
    MemberDTO memberDTO = memberService.findById(id); // 한명이니까 DTO
    model.addAttribute("member", memberDTO);
    return "Id_detail";
  }

  // 수정
  @SuppressWarnings({"checkstyle:MissingJavadocMethod", "checkstyle:AbbreviationAsWordInName"})
  @GetMapping("/member/update")
  public String updateForm(HttpSession session, Model model) {
    //    내정보를 수정하는 거기 때문에 세션에 있는 로그인 이메일 값을 가져와서 그걸로 나의 전체 정보를 DB 로부터 가져온다
    //    그거를 모델에 담아서 update html 로 가져간다.
    String myEmail = (String) session.getAttribute("loginEmail"); // 담을때는 set, 가져올땐 get
    MemberDTO memberDTO = memberService.updateForm(myEmail);
    model.addAttribute("updateMember", memberDTO);
    return "update";
  }

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MissingJavadocMethod"})
  @PostMapping("/member/update")
  public String update(@ModelAttribute MemberDTO memberDTO) {
    memberService.update(memberDTO);
    return "redirect:/member/" + memberDTO.getId(); // 넘겨받은 DTO의 아이디가 같이 담겨있다.
    // 내정보를 수정하고나서 수정이 완료된 나의 상세 페이지를 띄어준다.
  }

  // 삭제 처리
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  @GetMapping("/member/Id_delete/{id}") // 링크이기 때문에 GetMapping
  public String deleteById(@PathVariable Long id) { // 경로상으로 id 값을 같이 넘겨 받았기 때문에 @PathVariable 사용
    memberService.deleteById(id);
    return "redirect:/member/"; // 삭제가 완료되면 목록을 보여줌
    // redirect 뒤에서 반드시 주소가 온다. html 파일 이름이 오지 않는다.
  }

  // 로그아웃
  @GetMapping("/member/logout")
  public String logout(HttpSession session) {
    session.invalidate(); // session 을 매개변수로 받고, session 을 무효화 한다고 이해
    return "Main";
  }
}
