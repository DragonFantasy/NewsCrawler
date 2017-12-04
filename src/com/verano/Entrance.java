package com.verano;

import java.awt.Polygon;
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
import com.verano.task.PolygonTask;
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
		StringBuilder sb_output = new StringBuilder("<h1 style=\"text-align:center\">Crawl Result<h1/>");
		for(CrawlerTask task : task_list)
		{
			sb_output.append(task.newsInfo.getHTMLOutput());
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
			fos.write(sb_output.toString().getBytes());
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("fin...");
	}
}
