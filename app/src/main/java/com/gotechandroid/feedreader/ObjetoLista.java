package com.gotechandroid.feedreader;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gotechandroid.feedreader.NavigationAdapter.Fila;

public class ObjetoLista {
	private String titulo;
	private String fecha;
	private String imagen;
	private String preview;
	private int icono;
	private Activity activity;
	ArrayList<ObjetoLista> arrayitems;
	
	
		public Object getItem(int position){
			return arrayitems.get(position);
		}
	
	//getters
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	public void setFecha(String fecha){
		this.fecha = fecha;
	}
	
	public void setImagen(String imagen){
		this.imagen = imagen;
	}
	
	public void setPreview(String preview){
		this.preview = preview;
	}
	
	public void setIcono(int icono){
		this.icono = icono;
	}
	
	//setters
	public String getTitulo(){
		return this.titulo;
	}
	
	public String getFecha(){
		return this.fecha;
	}
	
	public String getImagen(){
		return this.imagen;
	}
	
	public String getPreview(){
		return this.preview;
	}
	
	public int getIcono(){
		return this.icono;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		Fila view;
		LayoutInflater inflator = activity.getLayoutInflater();
		
		if(convertView == null)
		{
			view = new Fila();
			ObjetoLista itm = arrayitems.get(position);
			convertView = inflator.inflate(R.layout.elementolista, null);
			view.titulo_itm=(TextView)convertView.findViewById(R.id.title_item);
			view.titulo_itm.setText(itm.getTitulo());
			view.icono = (ImageView) convertView.findViewById(R.id.icon);
			view.icono.setImageResource(itm.getIcono());
		}
		else
		{
			view = (Fila)convertView.getTag();
		}
		return convertView;
	}
}
