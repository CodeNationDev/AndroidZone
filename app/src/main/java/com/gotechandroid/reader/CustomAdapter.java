package com.gotechandroid.reader;
import com.gotechandroid.reader.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{

	
		
		 private final Activity context;
		 private final String[] web;
		 private final String[] fechas;
		 private final Integer[] imageId;
		 private final String[] resumen;
		 
		 public CustomAdapter(Activity context, String[] web, String[] fechas, Integer[] imageId, String[] resumen) {
		 super(context, R.layout.elementolista, web);
		 this.context = context;
		 this.web = web;
		 this.imageId = imageId;
		 this.fechas = fechas;
		 this.resumen = resumen;
		 }
		 @Override
		 public View getView(int position, View view, ViewGroup parent) {
		 Typeface miTipo = Typeface.createFromAsset(context.getAssets(), "robotocondensedlight.ttf");
		 LayoutInflater inflater = context.getLayoutInflater();
		 View rowView= inflater.inflate(R.layout.elementolista, null, true);
		 TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		 TextView txtFecha = (TextView) rowView.findViewById(R.id.txt2);
		 TextView txResumen = (TextView) rowView.findViewById(R.id.txResumen);
		 ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		 txtTitle.setTypeface(miTipo);
		 txtFecha.setTypeface(miTipo);
		 txResumen.setTypeface(miTipo);
		 txtFecha.setTextColor(Color.GRAY);
		 txResumen.setTextColor(Color.GRAY);
		 txtTitle.setText(web[position]);
		 txtFecha.setText(fechas[position]);
		 imageView.setImageResource(imageId[position]);
		 txResumen.setText(resumen[position]);
		 return rowView;
		 } 
		             
     }



