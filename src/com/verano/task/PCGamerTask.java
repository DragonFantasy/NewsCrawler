package com.verano.task;

import org.jsoup.select.Elements;

import com.verano.SingleNews;

public class PCGamerTask extends CrawlerTask
{
	public PCGamerTask() 
	{
		super("http://www.pcgamer.com/news/", "PCGamer");
	}
	
	@Override
	void runTask() 
	{
		SingleNews item1 = new SingleNews(page.getElementById("Item1").getElementsByClass("article-name").get(0).text()
				, page.getElementById("Item1").child(0).attr("href"));
		newsInfo.addNews(item1);
		SingleNews item2 = new SingleNews(page.getElementById("Item2").getElementsByClass("article-name").get(0).text()
				, page.getElementById("Item2").child(0).attr("href"));
		newsInfo.addNews(item2);
		SingleNews item3 = new SingleNews(page.getElementById("Item3").getElementsByClass("article-name").get(0).text()
				, page.getElementById("Item3").child(0).attr("href"));
		newsInfo.addNews(item3);
		Elements news_list = page.getElementsByClass("listingResult");
		for(int i = 0; i < news_list.size(); i++)
		{
			if(news_list.get(i).getElementsByClass("article-name").size() > 0)
			{
				SingleNews news = new SingleNews(news_list.get(i).getElementsByClass("article-name").get(0).text()
						, news_list.get(i).child(0).attr("href"));
				newsInfo.addNews(news);
			}
		}
	}
	
}
