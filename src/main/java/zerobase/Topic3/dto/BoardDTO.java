package zerobase.Topic3.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import zerobase.Topic3.entity.BoardEntity;
import zerobase.Topic3.entity.BoardFileEntity;

// DTO (Data Transfer Object) 데이터를 전송할때 사용하는 객체라고 표현/ VO,Bean
@Getter
@Setter
@ToString // 필드값 확인 할때 많이 사용 (필수X)
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개 변수로 하는 생성자
public class BoardDTO {
  private Long id;
  private String boardWriter;
  private String boardPass;
  private String boardTitle;
  private String boardContents;
  private int boardHits; // 조회수
  private LocalDateTime boardCreatedTime; // 게시글 작성 시간
  private LocalDateTime boardUpdatedTime; // 게시글 수정 시간

  private MultipartFile boardFile; // save.html -> Controller 파일 담는 용도 (여러개를 받는걸 List 작성)
  private List<String> originalFileName; // 원본 파일 이름
  private List<String> storedFileName; // 서버 저장용 파일 이름
  private int fileAttached; // 파일 첨부 여부 (첨부 1, 미첨부 0)

  // command + N 단축기 -> 생성자 생성
  public BoardDTO(
      Long id,
      String boardWriter,
      String boardTitle,
      int boardHits,
      LocalDateTime boardCreatedTime) {
    this.id = id;
    this.boardWriter = boardWriter;
    this.boardTitle = boardTitle;
    this.boardHits = boardHits;
    this.boardCreatedTime = boardCreatedTime;
  }

  // entity 에서 DTO로 변환
  public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setId(boardEntity.getId());
    boardDTO.setBoardWriter(boardEntity.getBoardWriter());
    boardDTO.setBoardPass(boardEntity.getBoardPass());
    boardDTO.setBoardTitle(boardEntity.getBoardTitle());
    boardDTO.setBoardContents(boardEntity.getBoardContents());
    boardDTO.setBoardHits(boardEntity.getBoardHits());
    boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
    boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
    if (boardEntity.getFileAttached() == 0) {
      boardDTO.setFileAttached(boardEntity.getFileAttached()); // 0 이라면
    } else {
      List<String> originalFileNameList = new ArrayList<>();
      List<String> storedFileNameLsit = new ArrayList<>();
      boardDTO.setFileAttached(boardEntity.getFileAttached()); // 1
      // 파일 이름을 가져가야함. (storedFileName)
      // orginalFileName, storedFileName (은): board_file_table(BoardFileEntity) 에 들어있다.
      for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
        originalFileNameList.add(boardFileEntity.getOriginalFileName());
        // entity에 있는 OriginalFileName 을 꺼내서 originalFileNameList  에 담는것 하나씩
        storedFileNameLsit.add(boardFileEntity.getStoredFileName());
      }
      boardDTO.setOriginalFileName(originalFileNameList);
      boardDTO.setStoredFileName(storedFileNameLsit); // 담아주기
    } // 파일이 여러개라 반복문 필요
    return boardDTO;
  }
}
