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
import zerobase.Topic3.dto.CommentDTO;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 20, nullable = false)
  private String commentWriter;

  @Column private String commentContents;

  /* Board:Comment = 1:N */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private BoardEntity boardEntity;

  public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
    CommentEntity commentEntity = new CommentEntity(); // 클래스 내부
    commentEntity.setCommentWriter(commentDTO.getCommentWriter()); // 넘겨 받은 작성자
    commentEntity.setCommentContents(commentDTO.getCommentContents()); // 내용
    commentEntity.setBoardEntity(boardEntity);
    // 게시글 번호만 넣는 것이 아니라 게시글 번호로 조회한 부모 Entity 값을 넣어줘야한다.
    return commentEntity;
  }
}
