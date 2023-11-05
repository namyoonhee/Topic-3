package zerobase.Topic3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.Topic3.dto.MemberDTO;
import zerobase.Topic3.entity.MemberEntity;
import zerobase.Topic3.repository.MemberRepository;

@Service // 스프링이 관리해주는 객체 즉, 스프링빈으로 등록 시킨다.
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;


  public void save1(MemberDTO memberDTO) {
    // 1. dto -> entity 변화
    // 2. repository 의 save 메서드 호출
    MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
    // toMemberEntity 호출하고, 메개변수는 memberDTO 넘겨준다.
    memberRepository.save(memberEntity); // repository 의 save 메서드는 만드는게 아니라 jp에서 제공해주는 메서드
    // repository 의 save 메서드 호출 (조건. entity 객체를 넘겨줘야 함)
  }


  public MemberDTO login(MemberDTO memberDTO) {
    /*
    1. 회원이 입력한 이메일로 DB에서 조회를 함
    2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
     */
    Optional<MemberEntity> byMemberEmail =
        memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
    if (byMemberEmail.isPresent()) {
      // 조회 결과가 있다. (해당 이메일을 가진 회원 정보가 있다.)
      MemberEntity memberEntity = byMemberEmail.get();
      if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
        // memberEntity.getMemberPassword() 를 memberDTO.getMemberPassword() 랑 같은지
        // 비밀번호 일치
        // entity -> dto 변환 후 리턴 (entity 객체는 service 객체 안에서 만 사용하게 끔 함)
        MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
        return dto;
      } else {
        // 비밀번호 불일치 (로그인 실패)
        return null;
      }
    } else {
      // 조회 결과가 없다. (해당 이메일을 가진 회원이 없다.)
      return null;
    }
  }


  public List<MemberDTO> findAll() {
    List<MemberEntity> memberEntityList =
        memberRepository.findAll(); // Repository 가 제공해주는 메서드(findAll)
    // List 객체에는 Entity 가 담겨옴 (repository 와 관련된 것은 무조건 Entity 객체로 주고 받게 된다.)
    List<MemberDTO> memberDTOList = new ArrayList<>();
    for (MemberEntity memberEntity : memberEntityList) {
      memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
      //      MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
      //      memberDTOList.add(memberDTO);
    }
    return memberDTOList;
  }


  public MemberDTO findById(Long id) {
    Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
    if (optionalMemberEntity.isPresent()) {
      //      MemberEntity memberEntity = optionalMemberEntity.get();
      //      MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
      //      return memberDTO;
      // 위 세줄을 아래 한줄로 표현
      return MemberDTO.toMemberDTO(optionalMemberEntity.get());
    } else {
      return null;
    }
  }


  public MemberDTO updateForm(String myEmail) {
    Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
    if (optionalMemberEntity.isPresent()) { // findById 와 거의 유사 방법
      return MemberDTO.toMemberDTO(optionalMemberEntity.get());
      // optionalMemberEntity.get() 객체를 까서 DTO로 변환해서 return 한다.
    } else {
      return null;
    }
  }

  public void update(MemberDTO memberDTO) {
    memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    // save: id가 없으면 Insert query 를 수행, DB에 있는 아이디가 있는 Entity 객체가 넘어오면 update query를 날려준다.
  }

  public void deleteById(Long id) {
    memberRepository.deleteById(id);
  }
}
