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

-> 현재 아직 application.properties에 db 정보를 적은것이 아니기 때문에 해당 방법으로는 해결 불가

- **방법 2**: 임시 DB 설정 추가
# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

