package com.verano.task;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PSNTask
{
	StringBuilder sb_output;
	public PSNTask(String name)
	{
		sb_output = new StringBuilder("<div><p>");
		sb_output.append(name).append("</p>");
	}
	public void runTask(String free_url, String sale_url)
	{
		try {
			sb_output.append("<div><a href=\"").append(free_url).append("\">会免游戏</a></div>");
			Document page = Jsoup.connect(free_url).get();
			Elements list = page.getElementsByClass("grid-cell__title");
			Elements platform_list = page.getElementsByClass("grid-cell__left-detail--detail-1");
			for(int i = 0; i < list.size(); i++)
			{
				sb_output.append("<div><span>").append(list.get(i).text()).append("</span>");
				sb_output.append("<span style=\"color:#009922\">").append(platform_list.get(i).text()).append("</span></div>");
			}
			sb_output.append("<div><a href=\"").append(sale_url).append("\">会员折扣</a></div>");
			Document sale = Jsoup.connect(sale_url).get();
			Elements sale_list = sale.getElementsByClass("__desktop-presentation__grid-cell__base__0ba9f");
			for(int i = 0; i < sale_list.size(); i++)
			{
				sb_output.append("<div><span>").append(sale_list.get(i).getElementsByClass("grid-cell__title").get(0).text()).append("</span>");
				sb_output.append("<span style=\"color:#009922\">").append(sale_list.get(i).getElementsByClass("grid-cell__left-detail--detail-1").get(0).text())
						.append("</span>");
				if(sale_list.get(i).getElementsByClass("discount-badge__message").size() > 0)
				{
					sb_output.append("<span style=\"color:#ff2200\">").append(sale_list.get(i).getElementsByClass("discount-badge__message").get(0).text())
							.append("</span></div>");
				}
				
				else
				{
					sb_output.append("</div>");
				}
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
