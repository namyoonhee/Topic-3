package zerobase.Topic3.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings({"checkstyle:MissingJavadocType", "checkstyle:AbbreviationAsWordInName"})
// DTO (Data Transfer Object) 데이터를 전송할때 사용하는 객체라고 표현/ VO,Bean
@Getter
@Setter
@ToString // 필드값 확인 할때 많이 사용 (필수X)
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개 변수로 하는 생성자
public class BoardDTO {
  private Long id;
  private String boardWriter;
  private String boardPass;
  private String boardTitle;
  private String boardContents;
  private int boardHits; // 조회수
  private LocalDateTime boardCreatedTime; // 게시글 작성 시간
  private LocalDateTime boardUpdatedTime; // 게시글 수정 시간
}
