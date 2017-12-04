package com.verano;

public class SingleNews 
{
	String title;
	String url;
	public SingleNews(String _title, String _url)
	{
		title = _title;
		url = _url;
	}
	public String getHTML()
	{
		return "<a target=\"_blank\" href=\""+url+"\">"+title+"</a>";
	}
	public boolean checkNews()
	{
		if(title == null || url == null)
		{
			return false;
		}
		return (!title.isEmpty() && !url.isEmpty());
	}
}
