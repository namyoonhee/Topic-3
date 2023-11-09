package zerobase.Topic3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  } // 조회수 처리 메서드

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

  public BoardDTO update(BoardDTO boardDTO) {
    BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO); // entity 로 변환을 위한 메서드 호출
    boardRepository.save(boardEntity); // 업데이트인지 인서트인지 아느냐는 id 값이 있냐 없냐 차이
    return findById(boardDTO.getId());
  }

  public void delete(Long id) {
    boardRepository.deleteById(id);
  }

  public Page<BoardDTO> paging(Pageable pageable) {
    int page = pageable.getPageNumber() - 1;
    int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
    // 한 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
    // page 위치에 있는 값은 0부터 시작 (실제 사용자가 요청한 페이지 값에서 -1 한 값이 요청이 되어야한다.)
    Page<BoardEntity> boardEntities =
        boardRepository.findAll(
            PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
    // page = 몇 페이지를 보고 싶은지, 전체를 Sort.by(Sort.Direction.DESC,"id") 기준으로 해서 해당 페이지 값을 가져온다 (DESC -
    // 내림차순 정렬)
    // "id" 부분 - 기준 컬럼 (여기 쓰는 값은 entity 작성한 이름 기준), DB 컬럼 기준 X
  }
}
