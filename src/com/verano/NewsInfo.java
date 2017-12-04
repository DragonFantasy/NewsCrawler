package com.verano;

import java.util.ArrayList;

public class NewsInfo 
{
	ArrayList<SingleNews> news_list;
	String name;
	public NewsInfo(String name)
	{
		news_list = new ArrayList<SingleNews>();
		this.name = name;
	}
	public int addNews(SingleNews news)
	{
		if(news != null && news.checkNews())
		{
			news_list.add(news);
		}
		return news_list.size();
	}
	public String getHTMLOutput()
	{
		StringBuilder sb_output = new StringBuilder("");
		sb_output.append("<div><p>").append(name).append("</p>");
		for(SingleNews news : news_list)
		{
			sb_output.append("<div>").append(news.getHTML()).append("</div>");
		}
		sb_output.append("</div>");
		return sb_output.toString();
	}
}