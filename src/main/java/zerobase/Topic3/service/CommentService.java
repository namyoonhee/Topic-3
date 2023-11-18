package zerobase.Topic3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.Topic3.dto.CommentDTO;
import zerobase.Topic3.entity.BoardEntity;
import zerobase.Topic3.entity.CommentEntity;
import zerobase.Topic3.repository.BoardRepository;
import zerobase.Topic3.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final BoardRepository boardRepository;

  public Long save(CommentDTO commentDTO) {
    /* 부모엔티티(BoardEntity) 조회 */
    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
    if (optionalBoardEntity.isPresent()) {
      BoardEntity boardEntity = optionalBoardEntity.get();
      CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
      // saveEntity 로 변환 할때 boardEntity 도 같이 보내야함
      return commentRepository.save(commentEntity).getId();
    } else {
      return null;
    } // 댓글 작성하는 기능
    // builder
  }

  public List<CommentDTO> findAll(Long boardId) {
    // select * from comment_table wher board_id=? order by id desc;
    BoardEntity boardEntity = boardRepository.findById(boardId).get();
    List<CommentEntity> commentEntityList =
        commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
    /* EntityList -> DTOList */
    List<CommentDTO> commentDTOList = new ArrayList<>();
    for (CommentEntity commentEntity : commentEntityList) {
      CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
      commentDTOList.add(commentDTO);
    }
    return commentDTOList;
  }
}
