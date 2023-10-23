# [YH] 게시판 만들기
-  간단하게 사용자들과 커뮤니케이션할 수 있는 게시판 서비스

# 프로젝트 기능 및 설계
- 회원가입이 가능

  - 사용자는 회원가입을 할 수 있습니다. 일반적으로 모든 사용자는 회원 가입시 USER 권한(일반 권한)을 받아들이다.
  - 회원가입시 이메일 인증을 해야합니다.
  - 이름, 생년월일 작성이 필요합니다.
  - 로그인이 가능합니다

  - 사용자는 로그인을 할 수 있습니다. 로그인 회원가입을 할 때 아이디와 일치해야 합니다.
 

- 게시글 작성 기능

  - 로그인한 사용자는 권한에 관계없이 글 작성이 가능합니다.
  - 사용자는 게시글 제목(텍스트), 게시글 내용(텍스트)을 찾을 수 있습니다.
- 게시글 목록 조회 기능

  - 로그인하지 않은 사용자를 포함한 모든 사용자는 게시글을 조회할 수 있습니다.
  - 게시글은 최신순으로 기본적으로 거부할 수 없으며, 댓글이 많은 순/적은 순으로만 선택할 수 있습니다.
 
- 특정 게시글 조회 기능

  - 로그인하지 않은 사용자를 포함한 모든 사용자는 게시글을 조회할 수 있습니다.
  - 게시글 제목, 게시글 내용, 작성자, 작성일이 조회됩니다.

- 댓글 작성 & 수정 기능

  - 댓글 작성시 로그인이 필요합니다.
  - 로그인한 사용자는 권한에 관계없이 작성 할 수 있습니다.
  - 사용자는 댓글 내용(텍스트)을 수정 할 수 있습니다.
  - 작성 & 수정시 날짜와 시간이 표시 됩니다.
 
 
# ERD
![이미지](https://github.com/namyoonhee/Topic-3/assets/135304661/0639d331-62cc-4c58-8eef-ee325d415684)

# 문제 해결
[문제 해결 과정](TROUBLE_SHOOTING.md)

# 기술 스택
1. 언어 JAVA (JDK 17)
2. Spring Framework 3.1.4
3. DB_MYSQL
4. IDE Intellij 2023.2.2
