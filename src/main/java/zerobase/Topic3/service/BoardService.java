package zerobase.Topic3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.Topic3.dto.BoardDTO;
import zerobase.Topic3.entity.BoardEntity;
import zerobase.Topic3.repository.BoardRepository;

// DTO -> Entity 변환 하거나 (Entity class 에서 작업)
// Entity -> DTO 가져온 데이터를 dto 클레스로 변환 하거나 (DTO class 에서 작업)

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository; // 생성자 주입 방식 사용

  public void save(BoardDTO boardDTO) {
    BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
    boardRepository.save(boardEntity); // boardEntity 를 save 메서드로 넘겨주게 된면 나가게 된다
  }

  public List<BoardDTO> findAll() {
    List<BoardEntity> boardEntityList = boardRepository.findAll();
    // repository 로 부터 무언갈 가져올 때는 거의 무조건 list 형태의 Entity 로 온다.
    List<BoardDTO> boardDTOList = new ArrayList<>();
    // entity 객체를 DTO 객두로 옮겨담는다.
    for (BoardEntity boardEntity : boardEntityList) {
      boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
      // entity 객체를 (boardDTOList) DTO로 변환을 하고 변화된 객체를 boardDTOList 에 다가 담는다.
    }
    return boardDTOList;
  }

  @Transactional // 제공되는 메서드가 아니라 별도로 추가된 메서드를 쓰는 경우 @Transactional 사용 (없으면 에러)
  public void updateHits(Long id) {
    boardRepository.updateHits(id);
  }

  public BoardDTO findById(Long id) {
    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
    if (optionalBoardEntity.isPresent()) {
      BoardEntity boardEntity = optionalBoardEntity.get();
      BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
      return boardDTO;
    } else {
      return null;
    }
  }
}
