package com.nathan.problemcrawler.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProgrammersCrawler {

    public static void main(String[] args) throws Exception {
        String url = "https://school.programmers.co.kr/learn/challenges";
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc.html());

//      Elements titles = doc.select(".challenge-card__title");
        Elements titles = doc.select("td.title > a");

        for (Element title : titles) {
            System.out.println("문제명: " + title.text());
        }


    }
}
