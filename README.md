# JobFinder — 게시판/커뮤니티 서비스 (Board Service)

> JobFinder 구인구직 플랫폼의 MSA 게시판 서비스  
> Spring Boot + Thymeleaf + MyBatis + Oracle 기반

## 프로젝트 개요

| 항목 | 내용 |
|------|------|
| 개발 기간 | 2026.02.24 ~ 2026.03.31 (약 5주) |
| 인원 | 4명 |
| 역할 | 게시판/커뮤니티 기능 담당 |

## 관련 레포지토리

JobFinder는 MSA 4개 서비스로 구성되어 있습니다.

| 서비스 | 레포 | 포트 | 역할 |
|--------|------|------|------|
| Main Service | [jobfinder-main](https://github.com/kimseoyoung000/jobfinder-main) | 8001 | 회원/채용/이력서/지원 |
| Board Service | [jobfinder-board](https://github.com/kimseoyoung000/jobfinder-board) | 8002 | 게시판/커뮤니티 |
| API Gateway | [jobfinder-gateway](https://github.com/kimseoyoung000/jobfinder-gateway) | 8000 | 라우팅/JWT 검증 |
| Discovery | [jobfinder-discovery](https://github.com/kimseoyoung000/jobfinder-discovery) | 8761 | 서비스 등록/조회 |

## 기술 스택

| 구분 | 기술 |
|------|------|
| Backend | Java 17, Spring Boot, Spring Security, MyBatis, Oracle DB |
| Frontend | Thymeleaf, HTML5, CSS, JavaScript, jQuery |
| Auth | JWT (Access Token / Refresh Token) |
| MSA | Spring Cloud Gateway, Eureka Discovery |
| Infra | AWS EC2, Docker, Jenkins CI/CD |

## 환경변수 설정

민감 정보는 환경변수로 관리합니다. 실행 시 아래 환경변수를 설정해주세요.

```
JWT_SECRET=your-jwt-secret-key
DB_URL=jdbc:oracle:thin:@your-db-host:1521:xe
DB_USERNAME=your-db-username
DB_PASSWORD=your-db-password
SOLAPI_API_KEY=your-solapi-api-key
SOLAPI_API_SECRET=your-solapi-api-secret
SOLAPI_FROM_PHONE=your-phone-number
PORTONE_IMP_CODE=your-imp-code
PORTONE_API_KEY=your-portone-api-key
PORTONE_API_SECRET=your-portone-api-secret
```
