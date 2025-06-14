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

        // 크롤링할 사이트 지정
        String site = "https://school.programmers.co.kr/learn/challenges?order=recent&page=";
        int page = 1;

        // 페이지 접속
        while (true) {
            String link = site + page;
            driver.get(link);

            List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

            if (rows.isEmpty()) {
                System.out.println("더 이상 문제가 없음.");
                break;
            }


            for (WebElement row : rows) {

                WebElement titleEl = row.findElement(By.cssSelector("td.title a"));
                WebElement levelEl = row.findElement(By.cssSelector("td.level span"));
                WebElement partEl = row.findElement(By.cssSelector("small.part-title"));

                // 각각 텍스트 추출
                String title = titleEl.getText();
                String url = "https://school.programmers.co.kr" + titleEl.getAttribute("href");
                String level = levelEl.getText().replaceAll("[^0-9]", "");
                String partTitle = partEl.getText();
                String tier = "Lv." + level;


                System.out.println(site);
                System.out.println(title);
                System.out.println(url);
                System.out.println(level);
//            System.out.println(partTitle);
                System.out.println(tier);
                System.out.println("=============================");
            }
            page++;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }
        // 브라우저 닫기
        driver.quit();
    }
}
