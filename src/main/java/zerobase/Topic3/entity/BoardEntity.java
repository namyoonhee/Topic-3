package zerobase.Topic3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import zerobase.Topic3.dto.BoardDTO;

@SuppressWarnings("checkstyle:MissingJavadocType")
// DB 의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table") // 특정 테이블 이름을 따로 주고 싶다면 Table 어노테이션 사용
public class BoardEntity extends BaseEntity { // 상속
  @Id // pk 컬럼 지정. 필수
  @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql 기준으로 auto_increment를 할수있는 어노테이션)
  private Long id;

  @Column(length = 20, nullable = false) // 크기 20, not null (null일 수 없다.)
  // DB를 정의할 때 컬럼의 크기를 정하게 되는데 그 컬럼의 크기 값을 정할 수 잇다.
  private String boardWriter;

  @Column // 크기 255, null 가능
  private String boardPass;

  @Column private String boardTitle;

  @Column(length = 500)
  private String boardContents;

  @Column private int boardHits;

  @SuppressWarnings({"checkstyle:MissingJavadocMethod", "checkstyle:AbbreviationAsWordInName"})
  public static BoardEntity toSaveEntity(BoardDTO boardDTO) { // 메서드로 정의
    // Entity 객체로 옮겨 담는 작업
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setBoardWriter(boardDTO.getBoardWriter());
    boardEntity.setBoardPass(boardDTO.getBoardPass());
    boardEntity.setBoardTitle(boardDTO.getBoardTitle());
    boardEntity.setBoardContents(boardDTO.getBoardContents());
    boardEntity.setBoardHits(0); // 조회수 값은 기본적으로 0
    return boardEntity; // 다옮겨 담으면 boardEntity 객체를 return
  }
  // html 에서 입력한 값을 BoardDTO 로 담아왔고, 담겨있는 작성자 값을 Entity의 작성자 값으로 set (옮겨담는 작업)
}
