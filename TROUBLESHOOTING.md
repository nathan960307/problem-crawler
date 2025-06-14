# 🚨 Troubleshooting Guide

이 문서는 개발 중 발생한 문제와 그에 대한 해결 과정을 기록합니다.

---

## (0614) Spring Boot 실행 시 DataSource 설정 오류

### ❗ 오류 메시지
Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
Reason: Failed to determine a suitable driver class

### 📌 원인
- DB 설정 정보가 `application.properties`에 누락됨.
- `spring-boot-starter-data-jdbc` 의존성이 포함되어 있어 Spring이 자동으로 데이터베이스 연결 시도.

### ✅ 해결 방법
- **방법 1**: DB가 아직 필요 없다면 의존성 제거
```gradle
// build.gradle
// 제거: spring-boot-starter-data-jdbc, mysql-connector-j

현재 아직 application.properties에 db 정보를 적은것이 아니기 때문에 해당 방법으로는 해결 불가

- **방법 2**: 임시 DB 설정 추가
# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

## 복합 클래스명 선택 오류
문제: By.className("td.title > div.bookmark > a") 사용 시 InvalidSelectorException 발생
원인: className()은 공백 없는 단일 클래스명만 지원함. td.title, div.bookmark, a 등 복합 선택자는 지원하지 않음
해결: By.cssSelector("td.title > div.bookmark > a")로 변경

## 콘솔 화면에서 한글이 제대로 인코딩 되지 않는 오류
문제 : 프로그램 실행 후 콘솔 화면에서 한글이 제대로 인코딩 되지 않고 깨지는 오류 발생
원인: 인텔리제이에서 단일 Java 파일 실행 시 VM 인코딩 옵션 적용 안 됨
해결:
Run Configuration에서 실행 (우상단 ▶ 버튼 → Edit Configurations)
VM options에 -Dfile.encoding=UTF-8 추가
또는 Application 전체 기준으로 실행 (Run 'Application: MainClassName' 방식)