package zerobase.Topic3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zerobase.Topic3.entity.MemberEntity;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
  // ETO 라는 클레스는 회원 정보에 필요한 내용들을 필드로 정의
  private Long id;
  private String memberEmail;
  private String memberPassword;
  private String memberName;
  private String memberBiretDate;

  public static MemberDTO toMemberDTO(MemberEntity memberEntity) { // Entity 에 했던거와 반대로
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setId(memberEntity.getId());
    memberDTO.setMemberEmail(memberEntity.getMemberEmail());
    memberDTO.setMemberPassword(memberEntity.getMemberPassword());
    memberDTO.setMemberName(memberEntity.getMemberName());
    memberDTO.setMemberBiretDate(memberEntity.getMemberBiretDate());
    return memberDTO;
  }
}
