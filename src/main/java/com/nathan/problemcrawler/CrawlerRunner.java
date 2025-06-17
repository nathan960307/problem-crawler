package com.nathan.problemcrawler;

import com.nathan.problemcrawler.crawler.BaekjoonCrawlerSelenium;
import com.nathan.problemcrawler.crawler.ProgrammersCrawlerSelenium;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrawlerRunner implements CommandLineRunner {

    private final ProgrammersCrawlerSelenium crawler;
    private final BaekjoonCrawlerSelenium crawler2;



    @Override
    public void run(String... args) {
        crawler2.crawlAll();
    }
}
