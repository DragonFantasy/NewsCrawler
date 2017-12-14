package com.verano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.verano.task.CrawlerTask;
import com.verano.task.IGNTask;
import com.verano.task.PCGamerTask;
import com.verano.task.PSNTask;
import com.verano.task.PolygonTask;
import com.verano.task.SteamTask;
import com.verano.task.VGTask;

public class Entrance 
{
	public static void main(String[] args)
	{
		ArrayList<CrawlerTask> task_list = new ArrayList<CrawlerTask>();
		//add each task
		task_list.add(new VGTask());
		task_list.add(new IGNTask());
		task_list.add(new PolygonTask());
		task_list.add(new PCGamerTask());
		//html code
		StringBuilder sb_output = new StringBuilder("<h1 style=\"text-align:center\">Crawl Result</h1>");
		try
		{
			for(CrawlerTask task : task_list)
			{
				sb_output.append(task.newsInfo.getHTMLOutput());
			}
			PSNTask psn_jp = new PSNTask("PSN日服");
			psn_jp.runTask("https://store.playstation.com/ja-jp/grid/PN.CH.JP-PN.CH.MIXED.JP-PSPLUSFREEPLAY/1?SMCID=pscomjp_psplus_webtop_a1"
					, "https://store.playstation.com/ja-jp/grid/PN.CH.JP-PN.CH.MIXED.JP-CATEGORY00000830/1?SMCID=pscomjp_psplus_webtop_a2");
			sb_output.append(psn_jp.getOutput());
			PSNTask psn_us = new PSNTask("PSN美服");
			psn_us.runTask("https://store.playstation.com/en-us/grid/STORE-MSF77008-PSPLUSFREEGAMES/1"
					, "https://store.playstation.com/en-us/grid/STORE-MSF77008-PSPLUSDISCOUNTS/1");
			sb_output.append(psn_us.getOutput());
			PSNTask psn_hk = new PSNTask("PSN港服");
			psn_hk.runTask("https://store.playstation.com/zh-hans-hk/grid/STORE-MSF86012-PLUS_FTT_CONTENT/1"
					, "https://store.playstation.com/zh-hans-hk/grid/STORE-MSF86012-PLUS_DIS_CONTENT/1");
			sb_output.append(psn_hk.getOutput());
	
			SteamTask steam = new SteamTask("Steam");
			steam.runTask("http://store.steampowered.com/search/?specials=1&os=win"
					, "http://store.steampowered.com/search/?filter=globaltopsellers&os=win");
			sb_output.append(steam.getOutput());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//output
		File fileDir = new File("./result/");
		if(!fileDir.exists())
		{
			fileDir.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_hh");
		String date_str = sdf.format(new Date());
		File file = new File("./result/"+date_str+".html");
		if(!file.exists())
		{
			try 
			{
				file.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(sb_output.toString().getBytes("UTF-8"));
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("fin...");
	}
}
