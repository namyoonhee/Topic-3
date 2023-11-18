package zerobase.Topic3.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerobase.Topic3.dto.CommentDTO;
import zerobase.Topic3.service.CommentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
  private final CommentService commentService;

  @PostMapping("/detail")
  public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {
    System.out.println("commentDTO = " + commentDTO);
    Long saveResult = commentService.save(commentDTO);
    if (saveResult != null) { // (성공하고 save 는 끝나면 안됨 댓글을 작성하면 기존 댓글에 추가된 댓글을 다시 화면에 보여줘야함)
      // 작성 성공하면 댓글목록을 가져와서 리턴
      // 댓글목록: 해당 게시글의 댓글 전체 (해당 게시글 아이디를 기준으로 아이디에 해당하는 댓글 전체를 가져와야한다.)
      List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
      return new ResponseEntity<>(
          commentDTOList, HttpStatus.OK); // ResponseEntity는 바디와 헤더를 같이 다룰수 있는 객체
      // 내가 보내고자 하는 commentDTOList(바디값), HttpStatus.OK (상태코드)를 같이 전달 가능
    } else {
      return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
  } // 댓글 작성이 제대로 되면 목록을 가져와서 res 에다가 가져온 데이터를 콘솔에 찍음
}
