package com.nathan.problemcrawler.crawler;

import com.nathan.problemcrawler.ProblemService;
import com.nathan.problemcrawler.dto.ProblemDto;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgrammersCrawlerSelenium {

    private final ProblemService problemService;

    public void crawlAll() {

        // 크롬 드라이버 경로 설정 (상대 경로 기준)
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver-win64/chromedriver.exe");


        WebDriver driver = new ChromeDriver();// 크롬 브라우저 실행
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // 크롤링할 사이트 지정
            String site = "https://school.programmers.co.kr/learn/challenges?order=recent&page=";
            int page = 1;
            String lastFirstUrl = "";

            // 페이지 접속
            while (true) {
                String link = site + page;
                driver.get(link);

                try {
                    // table tbody 가 나올 때까지 대기
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table tbody tr")));
                } catch (Exception e) {
                    System.out.println("페이지 " + page + "에 더 이상 로딩할 문제가 없음.");
                    break;
                }


                List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

                if (rows.isEmpty()) {
                    System.out.println("더 이상 문제가 없음.");
                    break;
                }

                WebElement titlelink = rows.get(0).findElement(By.cssSelector("td.title a"));
                String currentFirstUrl = titlelink.getAttribute("href");

                // 이전 페이지와 같으면 반복으로 판단 → 종료
                if (!lastFirstUrl.isEmpty() && currentFirstUrl.equals(lastFirstUrl)) {
                    System.out.println("같은 페이지 반복 감지. 종료.");
                    break;
                }
                lastFirstUrl = currentFirstUrl;


                for (WebElement row : rows) {

                    WebElement titleEl = row.findElement(By.cssSelector("td.title a"));
                    WebElement levelEl = row.findElement(By.cssSelector("td.level span"));
//                WebElement partEl = row.findElement(By.cssSelector("small.part-title"));

                    // 각각 텍스트 추출
                    String title = titleEl.getText();
                    String url = "https://school.programmers.co.kr" + titleEl.getAttribute("href");
                    String level = levelEl.getText().replaceAll("[^0-9]", "");
//                String partTitle = partEl.getText();
                    String tier = "Lv." + level;


                    System.out.println(site);
                    System.out.println(title);
                    System.out.println(url);
                    System.out.println(level);
//            System.out.println(partTitle);
                    System.out.println(tier);
                    System.out.println("=============================");

                    ProblemDto dto = ProblemDto.builder()
                            .site("프로그래머스")
                            .title(title)
                            .url(url)
                            .level(Integer.parseInt(level))
                            .tier(tier)
                            .tags("") // 추후 확장
                            .build();

                    problemService.save(dto);
                }
                page++;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        finally {
            driver.quit();
        }
    }
}
