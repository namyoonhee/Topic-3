# [YH] 게시판 만들기
-  간단하게 사용자들과 커뮤니케이션할 수 있는 게시판 서비스

# 프로젝트 기능 및 설계
- 회원가입이 가능

  - 사용자는 회원가입을 할 수 있습니다. 일반적으로 모든 사용자는 회원 가입시 USER 권한(일반 권한)을 받아들이다.
  - 회원가입시 인증과 로그인을 입력하며, 인증은 고유해야 합니다.
  - 이메일 인증
  - 로그인이 가능합니다

  - 사용자는 로그인을 할 수 있습니다. 로그인 회원가입을 할 때 아이디와 함께 일치해야 합니다.
 
- 아이디/비번 분실시 (희망사항)
  - 회원가입시 인증 받은 메일로 아이디 송부합니다.
  - 비밀번호를 초기화 합니다.

- 게시글 작성 기능

  - 로그인한 사용자는 권한에 관계없이 글을 취할 수 있습니다.
  - 사용자는 게시글 제목(텍스트), 게시글 내용(텍스트)을 찾을 수 있습니다.
- 게시글 목록 조회 기능

  - 로그인하지 않은 사용자를 포함한 모든 사용자는 게시글을 조회할 수 있습니다.
  - 게시글은 최신순으로 기본적으로 거부할 수 없으며, 댓글이 많은 순/적은 순으로만 선택할 수 있습니다.
  - 게시글 목록 조회시 응답에는 게시글과 제목 작성일, 댓글 수의 정보가 필요합니다.
  - 게시글은 종류가 많고 우수한 페이징 처리를 합니다.
- 특정 게시글 조회 기능

  - 로그인하지 않은 사용자를 포함한 모든 사용자는 게시글을 조회할 수 있습니다.
  - 게시글 제목, 게시글 내용, 작성자, 작성일이 조회됩니다.
- 댓글 목록 조회 기능

  - 특정 게시글 조회시 댓글 목록도 함께 조회됩니다. 외계인 댓글은 테스트할 수 있기 때문에 별도의 API로 구성됩니다. 이 또한 로그인하지 않은 사용자를 포함하여 모든 사용자가 댓글을 볼 수 있습니다.
  - 댓글은 최신 순으로만 거부하고, 페이징 처리를 해야 합니다.
  - 댓글 목록 조회에는 작성자와 댓글 내용, 댓글 작성일의 정보가 필요합니다.
- 댓글 작성 기능

  - 로그인한 사용자는 권한에 관계없이 로그인할 수 있습니다.
  - 사용자는 댓글 내용(텍스트)을 어색할 수 있습니다.
 
# ERD
![이미지](https://github.com/namyoonhee/Topic-3/assets/135304661/bb2e7490-8a60-4ad1-acc4-5715e06ab19d)

# 문제 해결
[문제 해결 과정](TROUBLE_SHOOTING.md)

# 기술 스택
1. 언어 JAVA (JDK 17)
2. Spring Framework 3.1.4
3. DB_MYSQL
4. IDE Intellij 2023.2.2
