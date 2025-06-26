# problem-crawler

## 📌 개요
- 프로그래머스, 백준 알고리즘 사이트에서 문제 목록을 크롤링하여 DB에 저장
- TODO-RPG 서비스에서 사용자 맞춤 문제 추천 기능에 활용 예정

---

## 🛠 기술 스택
- Java 21
- Spring Boot 3.5.0
- Gradle
- Selenium (동적 크롤링)
- MySQL (저장소)
- Jsoup (보조 HTML 파서)

---

## ✅ 진행 현황

### 1. 프로젝트 초기 설정
- [x] Gradle 기반 Spring Boot 프로젝트 생성
- [x] GitHub 저장소 [`problem-crawler`](https://github.com/nathan960307/problem-crawler) 연동 완료

### 2. 크롤러 기능 구현
- [x] Selenium 의존성 및 ChromeDriver 설정
- [x] 프로그래머스 문제 크롤링 구현
- [x] 문제 목록 페이지 전체 순회 및 데이터 추출

### 3. DB 연동
- [x] `Problem` 엔티티 및 Repository 구성
- [x] 크롤링 데이터 DB 저장 로직 구현 (`@Component + ApplicationRunner` 기반 자동 실행)

---

## 🐛 트러블슈팅

| 날짜 | 내용 |
|------|------|
| 2025-06-13 | `.env` 반영 후 DB 연결 오류 → 프로젝트 rebuild로 해결 |
| 2025-06-13 | `@Table(name = "problem")` 누락으로 테이블 생성 실패 → 명시 후 해결 |
| 2025-06-14 | 크롤러가 한 페이지만 순회함 → Selenium의 DOM 렌더링 대기 문제 해결 (명시적 wait 추가 예정) |

---

