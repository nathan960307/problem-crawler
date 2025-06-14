package com.nathan.problemcrawler.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ProgrammersCrawlerSelenium {
    public static void main(String[] args) {
        // 크롬 드라이버 경로 설정 (상대 경로 기준)
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver-win64/chromedriver.exe");

        // 크롬 브라우저 실행
        WebDriver driver = new ChromeDriver();

        try {
            // 페이지 접속
            driver.get("https://school.programmers.co.kr/learn/challenges");

            // 페이지 로딩 대기 시간 (간단한 방식으로 Thread.sleep 사용)
            Thread.sleep(3000); // 3초

            // 문제 제목들 가져오기
            List<WebElement> titles = driver.findElements(By.cssSelector("td.title div.bookmark a"));

            for (WebElement title : titles) {
                System.out.println("문제명: " + title.getText());
                System.out.println("링크: https://school.programmers.co.kr" + title.getAttribute("href"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 브라우저 닫기
            driver.quit();
        }
    }
}
