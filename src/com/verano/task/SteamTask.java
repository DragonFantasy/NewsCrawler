package com.verano.task;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SteamTask
{
	StringBuilder sb_output;
	public SteamTask(String name)
	{
		sb_output = new StringBuilder("<div><p>");
		sb_output.append(name).append("</p>");
	}
	public void runTask(String free_url, String sale_url)
	{
		try {
			sb_output.append("<div><a href=\"").append(free_url).append("\">特别优惠</a></div>");
			Document page = Jsoup.connect(free_url).get();
			Elements list = page.getElementsByClass("search_result_row");
			for(int i = 0; i < list.size(); i++)
			{
				sb_output.append("<div><span>").append(list.get(i).getElementsByClass("title").get(0).text()).append("</span>");
				sb_output.append("<span style=\"color:#ff2200\">").append(list.get(i).getElementsByClass("search_discount").get(0).child(0).text())
						.append("</span>");
				String price_str = list.get(i).getElementsByClass("search_price").get(0).text();
				String[] price_arr = price_str.split("¥");
				if(price_arr.length > 2)
				{
					price_str = "<strike>¥"+price_arr[1]+"</strike>¥"+price_arr[2];
				}
				else
				{
					price_str = "¥"+price_arr[1];
				}
				sb_output.append("<span>").append(price_str).append("</span></div>");
			}
			sb_output.append("<div><a href=\"").append(sale_url).append("\">全球热销</a></div>");
			page = Jsoup.connect(sale_url).get();
			list = page.getElementsByClass("search_result_row");
			for(int i = 0; i < list.size(); i++)
			{
				sb_output.append("<div><span>").append(list.get(i).getElementsByClass("title").get(0).text()).append("</span>");
				String price_str = list.get(i).getElementsByClass("search_price").get(0).text();
				String[] price_arr = price_str.split("¥");
				if(price_arr.length > 2)
				{
					price_str = "<strike>¥"+price_arr[1]+"</strike>¥"+price_arr[2];
				}
				else
				{
					price_str = "¥"+price_arr[1];
				}
				sb_output.append("<span>").append(price_str).append("</span></div>");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		sb_output.append("</div>");
	}
	public String getOutput()
	{
		return sb_output.toString();
	}
}
