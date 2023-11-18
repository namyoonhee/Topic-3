package zerobase.Topic3.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zerobase.Topic3.entity.CommentEntity;

@Getter
@Setter
@ToString
public class CommentDTO {
  private Long id; // 댓글의 id 값
  private String commentWriter; // 작성자
  private String commentContents; // 내용
  private Long boardId; // 게시글 번호
  private LocalDateTime commentCreatedTime; // 댓글 작성 시간

  public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardId) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(commentEntity.getId());
    commentDTO.setCommentWriter(commentEntity.getCommentWriter());
    commentDTO.setCommentContents(commentEntity.getCommentContents());
    commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
    // commentDTO.setBoardId(commentEntity.getBoardEntity().getId()); // Service 메서드에 @Transactional
    // 붙이기
    commentDTO.setBoardId(boardId);
    return commentDTO;
  } // Entity -> DTO
}
