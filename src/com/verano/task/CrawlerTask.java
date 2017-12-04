package com.verano.task;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.verano.NewsInfo;

public abstract class CrawlerTask 
{
	Document page;
	public NewsInfo newsInfo;
	public CrawlerTask(String url, String name)
	{
		try {
			page = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		newsInfo = new NewsInfo(name);
		runTask();
	}
	abstract void runTask();
}
