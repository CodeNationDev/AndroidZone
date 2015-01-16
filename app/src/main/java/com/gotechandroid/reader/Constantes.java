package com.gotechandroid.reader;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;


 public  class Constantes {

	 public final String URL_TODAS ="http://www.gotechandroid.blogspot.com.es/feeds/posts/default?alt=rss&max-results=20";
	 private  String URL_HOY = "http://www.gotechandroid.blogspot.com.es/feeds/posts/default?alt=rss&published-min=";
	 public final String URL_OFERTAS = "http://www.gotechandroid.blogspot.com.es/feeds/posts/default/-/aplicaciones_ofertas?alt=rss";
	 public final String TAM_IMAGEN = "height = \"320\" width=\"240\"";
	 @SuppressWarnings("unused")
	private final String URL_APLICACIONES_OFERTAS = "http://www.gotechandroid.blogspot.com.es/feeds/posts/default/-/aplicaciones_ofertas?alt=rss";
	 
	 
	 
	 
	 @SuppressLint("SimpleDateFormat")
	public String url_hoy(){
		 
		 String url_completa=URL_HOY;
		 
		 SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		 String fecha = date.format(new Date());
		 
		 url_completa += fecha+"T00:00:00&amp";
		 
		 
		 return url_completa; 
	 }
}

 