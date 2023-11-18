package zerobase.Topic3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.Topic3.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {}
