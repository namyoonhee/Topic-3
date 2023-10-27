package zerobase.Topic3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.Topic3.dto.BoardDTO;
import zerobase.Topic3.entity.BoardEntity;
import zerobase.Topic3.repository.BoardRepository;

// DTO -> Entity 변환 하거나 (Entity class 에서 작업)
// Entity -> DTO 가져온 데이터를 dto 클레스로 변환 하거나 (DTO class 에서 작업)

@SuppressWarnings("checkstyle:MissingJavadocType")
@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository; // 생성자 주입 방식 사용

  @SuppressWarnings({"checkstyle:EmptyLineSeparator", "checkstyle:AbbreviationAsWordInName"})
  public void save(BoardDTO boardDTO) {
    BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
    boardRepository.save(boardEntity); // boardEntity 를 save 메서드로 넘겨주게 된면 나가게 된다
  }
}
