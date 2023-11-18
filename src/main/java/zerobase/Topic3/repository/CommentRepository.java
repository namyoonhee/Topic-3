package zerobase.Topic3.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.Topic3.entity.BoardEntity;
import zerobase.Topic3.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
  // select * from comment_table wher board_id=? order by id desc;
  List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity); // 대소문자 확인
}
