package com.verano.task;

import org.jsoup.select.Elements;

import com.verano.SingleNews;

public class IGNTask extends CrawlerTask
{
	public IGNTask() 
	{
		super("http://www.ign.com/articles", "IGN");
	}
	
	@Override
	void runTask() 
	{
		Elements news_list = page.getElementsByClass("listElmnt-blogItem");
		for(int i = 0; i < news_list.size(); i++)
		{
			SingleNews news = new SingleNews(news_list.get(i).getElementsByClass("listElmnt-storyHeadline").get(0).text()
					, news_list.get(i).getElementsByClass("listElmnt-storyHeadline").get(0).attr("href"));
			newsInfo.addNews(news);
		}
	}
	
}
