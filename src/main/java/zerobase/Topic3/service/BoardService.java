package zerobase.Topic3.service;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import zerobase.Topic3.dto.BoardDTO;
import zerobase.Topic3.entity.BoardEntity;
import zerobase.Topic3.entity.BoardFileEntity;
import zerobase.Topic3.repository.BoardFileRepository;
import zerobase.Topic3.repository.BoardRepository;

// DTO -> Entity 변환 하거나 (Entity class 에서 작업)
// Entity -> DTO 가져온 데이터를 dto 클레스로 변환 하거나 (DTO class 에서 작업)

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository; // 생성자 주입 방식 사용

  private final BoardFileRepository boardFileRepository; // BoardFileRepository 사용하기 위해 주입

  public void save(BoardDTO boardDTO) throws IOException {
    // 파일 첨부 여부에 따라 로직 분리
    if (boardDTO.getBoardFile().isEmpty()) {
      // 첨부 파일 없음.
      BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
      boardRepository.save(boardEntity); // boardEntity 를 save 메서드로 넘겨주게 된면 나가게 된다
    } else {
      // 첨부 파일 있음.
      /*
      1. DTO 에 담긴 파일을 꺼냄
      2. 파일의 이름 가져옴
      3. 서버 저장용 이름으로 만듦
      // 내사진.jpg -> 75437098032_내사진.jpg
      4. 저장 경로 설정
      5. 해당 경로에 파일 저장
      6. board_table 에 해당 데이터 save 처리
      7. board_file_table 에 해당 save 처리
       */

      BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO); // DTO -> Entity 로 변환
      Long saveId = boardRepository.save(boardEntity).getId(); // 저장
      BoardEntity board = boardRepository.findById(saveId).get(); // 부모 entity 를 DB 로 부터 다시 가져오기
      // 위 세줄이 먼저 나와야함 (부모 객체를 먼저 가져오기)
      MultipartFile boardFile = boardDTO.getBoardFile(); // 1.
      String originalFilename = boardFile.getOriginalFilename(); // 2.
      String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
      // System.currentTimeMillis() 란 지금 현재가 Mirisec 나 지났느냐 그 값을 주는
      String savePath =
          "Users/YoonHeeNam/springboot_img/"
              + storedFileName; // 4. ( C:/springboot_img/0473985743_내사진.jpg
      boardFile.transferTo(new File(savePath)); // 5.

      BoardFileEntity boardFileEntity =
          BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
      boardFileRepository.save(boardFileEntity); // 6. DB에 저장
    }
  }

  @Transactional
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

  @Transactional
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
    // "id" 부분 - 기준 컬럼 (여기 쓰는 값은 entity에 작성한 이름 기준), DB 컬럼 기준 X
    System.out.println(
        "boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
    System.out.println(
        "boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
    System.out.println(
        "boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
    System.out.println(
        "boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
    System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
    System.out.println(
        "boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
    System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
    System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

    // 목록: id, writer, title, hits, createdTime
    // map(board ~) 는 entity 객체 메개변수로 본다. 변수를 하나씩 꺼내서 dto 객체로 옮겨 담는 작업
    Page<BoardDTO> boardDTOS =
        boardEntities.map(
            board -> // board = entity, entity 를 boardDTO 객체로
            new BoardDTO(
                    board.getId(),
                    board.getBoardWriter(),
                    board.getBoardTitle(),
                    board.getBoardHits(),
                    board.getCreatedTime()));
    return boardDTOS;
  }
}
