package com.verano.task;

import org.jsoup.select.Elements;

import com.verano.SingleNews;

public class VGTask extends CrawlerTask
{
	public VGTask() 
	{
		super("http://www.vgtime.com/topic/index.jhtml", "VGTime");
	}
	
	@Override
	void runTask() 
	{
		Elements news_list = page.getElementsByClass("info_box");
		for(int i = 0; i < news_list.size(); i++)
		{
			SingleNews news = new SingleNews(news_list.get(i).tagName("h2").text()
					, "http://www.vgtime.com"+news_list.get(i).tagName("a").attr("href"));
			newsInfo.addNews(news);
		}
	}
	
}
