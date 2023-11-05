package zerobase.Topic3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zerobase.Topic3.entity.BoardEntity;

@SuppressWarnings("checkstyle:MissingJavadocType")
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  // 기본적으로 Entity클레스 (JpaRepository)만 받아준다.
  // update board_table set board_hits=board_hits+1 where id=? (mysql 기준)
  @Modifying // update or delete 같은 커리를 실행해야 될때는 추가적으로 @Modifying 어노테이션 필수로 작성
  @Query(
      value =
          "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id") // entity 기준으로 Query문
  // 작성
  void updateHits(@Param("id") Long id); // ("id") 부분이 id =:id 랑 매칭이 된다
}
