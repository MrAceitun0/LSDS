/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author u137535
 */
public class Crawler {

    public static void main(String[] args){
        String seedPage = "http://en.wikipedia.org";

        try{
            Document doc = Jsoup.connect(seedPage).get();
            Elements links = doc.select("#mp-itn b a");
            for(Element url:links)
            {
                System.out.println(url.attr("abs:href"));
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}