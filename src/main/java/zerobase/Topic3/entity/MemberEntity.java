package zerobase.Topic3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import zerobase.Topic3.dto.MemberDTO;

@SuppressWarnings("checkstyle:MissingJavadocType")
@Entity
@Setter
@Getter
@Table(name = "member_table") // 테이블 이름 정의
public class MemberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private Long id;

  @Column(unique = true) // unique 제약조건 추가
  private String memberEmail;

  @Column private String memberPassword;

  @Column private String memberName;

  @Column private String memberBiretDate;

  @SuppressWarnings({"checkstyle:MissingJavadocMethod", "checkstyle:AbbreviationAsWordInName"})
  public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setMemberEmail(memberDTO.getMemberEmail());
    memberEntity.setMemberPassword(memberDTO.getMemberPassword());
    memberEntity.setMemberName(memberDTO.getMemberName());
    memberEntity.setMemberBiretDate(memberDTO.getMemberBiretDate());
    return memberEntity;
  }

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MissingJavadocMethod"})
  public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setId(memberDTO.getId());
    memberEntity.setMemberEmail(memberDTO.getMemberEmail());
    memberEntity.setMemberPassword(memberDTO.getMemberPassword());
    memberEntity.setMemberName(memberDTO.getMemberName());
    memberEntity.setMemberBiretDate(memberDTO.getMemberBiretDate());
    return memberEntity;
  }
}
