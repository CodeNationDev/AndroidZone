package com.gotechandroid.feedreader;

import android.text.Html;

public class HTMLparser {
	
	public String Htmlparser(String html){
		
	
		return Html.fromHtml(html).toString();
	}

}
