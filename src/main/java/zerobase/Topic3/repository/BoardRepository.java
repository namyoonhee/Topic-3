package zerobase.Topic3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.Topic3.entity.BoardEntity;

@SuppressWarnings("checkstyle:MissingJavadocType")
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  // 기본적으로 Entity클레스만 받아준다.

}
