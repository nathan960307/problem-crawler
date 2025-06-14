# 🚨 Troubleshooting Guide

이 문서는 개발 중 발생한 문제와 그에 대한 해결 과정을 기록합니다.

---

### Spring Boot 실행 시 DataSource 설정 오류
문제: DB 설정 누락으로 실행 실패
원인: application.properties에 DB 정보 없음
해결: VM 옵션 제거 또는 임시 DB 설정 추가

properties
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### 복합 클래스명 선택 오류
문제: By.className("a > b") 사용 시 예외 발생
원인: className()은 단일 클래스만 허용
해결: By.cssSelector("td.title > div.bookmark > a")

### 콘솔에서 한글 깨짐
문제: 콘솔에 한글이 깨짐
원인: VM 인코딩 옵션 누락
해결:

Run Configurations → VM options에 -Dfile.encoding=UTF-8 추가
또는 애플리케이션 전체 실행

### 페이지 반복으로 무한 크롤링
문제: 마지막 페이지에서 같은 문제가 반복됨
원인: 존재하지 않는 페이지에서 리디렉션 발생 + URL 비교 실패
해결:

WebElement titlelink = rows.get(0).findElement(By.cssSelector("td.title a"));
String currentFirstUrl = titlelink.getAttribute("href");
if (!lastFirstUrl.isEmpty() && currentFirstUrl.equals(lastFirstUrl)) break;
lastFirstUrl = currentFirstUrl;