package com.verano.task;

import org.jsoup.select.Elements;

import com.verano.SingleNews;

public class PolygonTask extends CrawlerTask
{
	public PolygonTask() 
	{
		super("https://www.polygon.com/news", "Polygon");
	}
	
	@Override
	void runTask() 
	{
		Elements news_list = page.getElementsByClass("c-entry-box--compact__title");
		for(int i = 0; i < news_list.size(); i++)
		{
			SingleNews news = new SingleNews(news_list.get(i).text()
					, news_list.get(i).child(0).attr("href"));
			newsInfo.addNews(news);
		}
	}
	
}
