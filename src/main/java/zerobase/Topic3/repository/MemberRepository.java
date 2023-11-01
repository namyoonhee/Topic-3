package zerobase.Topic3.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.Topic3.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  // interface 이기 때문에 추상메서드 정의
  // 규칙을 잘 지켜주면 메서드만 정의해도 그 메서드에 맞는 쿼리가 알아서 만들어짐 (spring jpa 가 해줌)
  // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
  // Repository 에서 주고 받는 객체는 Entity 객체
  Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
