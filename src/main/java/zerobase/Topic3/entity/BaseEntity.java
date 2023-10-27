package zerobase.Topic3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("checkstyle:MissingJavadocType")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity { // 시간 정보를 다룰 수 있도록
  @CreationTimestamp
  @Column(updatable = false) // 수정 시에는 updatable 는 관여를 안하게
  private LocalDateTime createdTime; // 생성 되었을때 시간을 만들어 주는 부분

  @UpdateTimestamp
  @Column(insertable = false) // 입력시 관여를 안하겠끔
  private LocalDateTime updatedTime; // 업데이트가 발생 했을때 만들어 주는 부분
}
