package zerobase.Topic3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String originalFileName;

  @Column private String storedFileName;

  @ManyToOne(fetch = FetchType.LAZY) // 자식클래스
  @JoinColumn(name = "board_id")
  private BoardEntity boardEntity;

  // 게시글 과 파일의 관계는 1 : N 이라는 관계를 가지게 된다. boardFile 기준에서는 N : 1 의 관계

  public static BoardFileEntity toBoardFileEntity(
      BoardEntity boardEntity, String originalFileName, String storedFileName) {
    BoardFileEntity boardFileEntity = new BoardFileEntity();
    boardFileEntity.setOriginalFileName(originalFileName);
    boardFileEntity.setStoredFileName(storedFileName);
    boardFileEntity.setBoardEntity(boardEntity); // 부모 entity 객체를 넘겨주어야 한다.
    return boardFileEntity;
  }
}
