package zerobase.Topic3.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerobase.Topic3.dto.BoardDTO;
import zerobase.Topic3.service.BoardService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // 대표주소 먼저 작성
public class BoardController { // board 로 시작하는 컨트롤러가 받는다 (1)
  private final BoardService boardService;

  @GetMapping("/detail") // 그 이하의 주소를 각각의 메서드 중에서 매핑 값이 일치하는 메서드가 호출 된다.
  public String saveForm() {
    return "detail";
  }

  @PostMapping("/detail") // post 로 보냈기 때문에
  // public String save(@RequestParam("boardWriter") String boardWriter) { // 이렇게 받아도 문제는 없음
  public String detail(@ModelAttribute BoardDTO boardDTO) {
    System.out.println("boardDTO = " + boardDTO); // 파라미터 값 확인
    boardService.save(boardDTO);
    return "Main"; // save를 완료하고 나서 index 로 다시 보냄
  }

  @GetMapping("/")
  public String findAll(Model model) { // 데이터를 가져온다면 model 객체 사용
    // DB 에서 전체 게시글 데이터를 가져와서 article-list.html에 보여준다.
    List<BoardDTO> boardDTOList = boardService.findAll();
    model.addAttribute("boardList", boardDTOList);
    return "article-list";
  }

  @GetMapping("/{id}")
  public String findById(@PathVariable Long id, Model model) { // 경로상에 있는 값을 가져올 때는 @PathVariable 사용
    /*
    해당 게시글의 조회수를 하나 올리고
    게시글 데이터를 가져와서 detail.html에 출력
     */
    boardService.updateHits(id); // (1)
    BoardDTO boardDTO = boardService.findById(id); // 두번의 호출이 발생 (2)
    model.addAttribute("board", boardDTO);
    return "list lookup";
  }

  @GetMapping("/list_update/{id}")
  public String updateForm(@PathVariable Long id, Model model) { // 데이터를 담아야하니 model
    BoardDTO boardDTO = boardService.findById(id);
    model.addAttribute("boardUpdate", boardDTO);
    return "list_update";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
    BoardDTO board = boardService.update(boardDTO);
    model.addAttribute("board", board);
    return "list lookup";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    boardService.delete(id);
    return "redirect:/board/";
  }

  // /board/paging?page=1 이런식으로 요청이감
  @GetMapping("/paging")
  public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
    // 페이징 처리된 데이터를 가지고 화면으로 넘어가야하기 때문에 model 객체 사용
    // pageable.getPageNumber();
    Page<BoardDTO> boardList = boardService.paging(pageable);
  }
}
