
# Schedule Management System

## 프로젝트 설명

이 프로젝트는 일정 관리 시스템을 구현한 애플리케이션입니다. 
사용자는 일정 추가, 수정, 삭제 및 조회를 할 수 있습니다. 
또한, 로그인 인증 기능을 추가하여 보호된 URL에서만 접근할 수 있도록 설정하였습니다. 
각 일정은 `유저` 고유 식별자와 연결되어 있습니다.

## 기술 스택

- Java 17
- Spring Boot 3
- Spring MVC (Controller)
- Spring Data JPA (Repository)
- JDBC
- Filter와 Session을 활용한 인증 구현

## 주요 기능

### 1. 일정 관리
- **일정 생성 (Create)**: 사용자는 일정 정보를 입력하여 새로운 일정을 추가할 수 있습니다.
- **일정 조회 (Read)**: 모든 일정을 조회하거나, 특정 일정을 조회할 수 있습니다.
- **일정 수정 (Update)**: 일정의 내용을 수정할 수 있습니다.
- **일정 삭제 (Delete)**: 일정의 내용을 삭제할 수 있습니다.

### 2. 유저 관리
- **회원가입 (Sign Up)**: 유저는 이메일, 비밀번호 등을 사용하여 계정을 생성할 수 있습니다.
- **로그인 (Login)**: 유저는 이메일과 비밀번호를 통해 로그인합니다.
- **유저 인증**: `유저 고유 식별자`로 각 일정과 유저를 연관 지으며, 유저의 인증을 거친 후에 일정에 접근할 수 있습니다.

### 3. 로그인 인증
- **로그인**: 사용자는 이메일과 비밀번호를 통해 로그인합니다.
- **인증 필터**: 보호된 URL에 접근할 때 이메일 세션을 확인하여 인증을 처리합니다.

엔드포인트
1. 유저 회원가입
   POST /users/signup

요청 본문 예시:

{
"email": "user@example.com",
"password": "password123"
}
2. 유저 로그인
   POST /users/login

요청 본문 예시:

{
"email": "user@example.com",
"password": "password123"
}
3. 일정 생성
   POST /schedules

요청 본문 예시:

{
"todo": "Meeting",
"content": "Project meeting at 3PM",
"author": "john.doe@example.com",
"password": "password123"
}
4. 일정 조회
   GET /schedules/{id}

5. 일정 수정
   PUT /schedules/{id}

6. 일정 삭제
   DELETE /schedules/{id}?password=yourpassword

예외 처리
로그인 시 이메일 또는 비밀번호가 일치하지 않으면 HTTP 401 Unauthorized를 반환합니다.
인증이 필요한 URL에 인증되지 않은 사용자가 접근하면 HTTP 401 Unauthorized를 반환합니다.



