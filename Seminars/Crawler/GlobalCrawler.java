/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author u137535
 */
public class GlobalCrawler 
{
    private static final int MAX_PAGES_TO_SEARCH = 1000;
    private List<String> pagesToVisit = new LinkedList<String>();
    private Set<String> pagesVisited = new HashSet<String>();
    
    public void crawling (String url)
    {
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
            String currentURL;
            
            if(this.pagesToVisit.isEmpty())
            {
                currentURL = url;
                this.pagesVisited.add(url);
            }
            else
            {
                currentURL = this.nextURL();
            }
            
            parsing(currentURL);
        }
    }
    
    private String nextURL(){
        String nextURL;
        
        do{
            nextURL = this.pagesToVisit.remove(0);
        }while(this.pagesVisited.contains(nextURL));
        
        this.pagesVisited.add(nextURL);
        
        return nextURL;
    }
    
    private boolean parsing(String url)
    {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a");
            
            for(Element newLink:links)
            {
                System.out.println(newLink.attr("abs:href"));
                this.pagesToVisit.add(newLink.attr("abs:href"));
            }
            return true;
            
        }catch (Exception e){
            return false;
        }
    }
}