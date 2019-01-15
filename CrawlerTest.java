/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author u137535
 */
public class CrawlerTest {
    public static void main(String[] args)
    {
        String seedPage = "http://en.wikipedia.org";
        GlobalCrawler crawler = new GlobalCrawler();
        crawler.crawling(seedPage);
    }
}
