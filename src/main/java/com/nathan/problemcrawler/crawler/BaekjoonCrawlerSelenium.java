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
public class BaekjoonCrawlerSelenium {

    private final ProblemService problemService;

    public void crawlAll() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver-win64/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            String site = "https://www.acmicpc.net/problemset?sort=ac_desc&page=";
            int page = 1;
            String lastFirstUrl = "";

            while (true) {
                String link = site + page;
                driver.get(link);

                try {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table tbody tr")));
                } catch (Exception e) {
                    System.out.println("페이지 " + page + "에 더 이상 문제가 없음.");
                    break;
                }

                List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

                if (rows.isEmpty()) {
                    System.out.println("더 이상 문제가 없음.");
                    break;
                }

                WebElement firstRow = rows.get(0).findElement(By.cssSelector("td:nth-child(2) a"));
                String currentFirstUrl = firstRow.getAttribute("href");

                if (!lastFirstUrl.isEmpty() && currentFirstUrl.equals(lastFirstUrl)) {
                    System.out.println("같은 페이지 반복 감지. 종료.");
                    break;
                }
                lastFirstUrl = currentFirstUrl;

                for (WebElement row : rows) {

                    String problemId = row.findElement(By.cssSelector("td:nth-child(1)")).getText().trim();
                    WebElement titleEl = row.findElement(By.cssSelector("td:nth-child(2) a"));

                    String title = titleEl.getText().trim();
                    String url = titleEl.getAttribute("href");

                    System.out.println(title);
                    System.out.println(url);

                    ProblemDto dto = ProblemDto.builder()
                            .site("백준")
                            .title(title)
                            .url(url)
                            .level(0)
                            .tier("")
                            .tags("")
                            .build();

                    problemService.save(dto);
                }

                page++;
                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
