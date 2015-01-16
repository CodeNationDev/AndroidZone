package com.gotechandroid.reader;


import com.gotechandroid.reader.R;

import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Xml.Encoding;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



public class CuerpoNoticia extends ActionBarActivity {

	public String link ="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuerpo_noticia);
		Intent intent = getIntent();
		String html="";

		
		WebView cuerpo = (WebView)findViewById(R.id.webView1);
		WebView capa = (WebView)findViewById(R.id.webview2);
		String cuerpo_noticia = intent.getStringExtra("cuerpo");
		
		
		if(intent!=null){
			
			link = intent.getStringExtra("link");
			capa.setWebViewClient(new WebViewClient());
			capa.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
			capa.getSettings().setLoadWithOverviewMode(true);
			capa.getSettings().setUseWideViewPort(true);
			capa.getSettings().setBuiltInZoomControls(true);
			capa.loadUrl(intent.getStringExtra("link"));
			WebSettings settings = cuerpo.getSettings();
			settings.setDefaultTextEncodingName(Encoding.ISO_8859_1.toString());
			
			html = traduceHTML(cuerpo_noticia);
			html = capturaTagsHTML(html);
			html = agregaEstilo(html,intent.getStringExtra("titulo"),intent.getStringExtra("fecha"), intent.getStringExtra("autor"));
			settings.setBuiltInZoomControls(false);	
			cuerpo.setBackgroundColor(Color.WHITE);
			cuerpo.getSettings().setCacheMode(MODE_APPEND);
			cuerpo.getSettings().setAppCacheEnabled(true);
			cuerpo.loadDataWithBaseURL("file:///android_asset/", html, "text/html", Encoding.ISO_8859_1.toString(), null);
			getActionBar().setTitle("Noticia");
			getActionBar().setSubtitle(generarFecha(intent.getStringExtra("fecha")));
			
			
		}
		overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cuerpo_noticia, menu);
		MenuItem shareOpt = menu.findItem(R.id.action_settings);
		ShareActionProvider myShareActionPRovider = (ShareActionProvider)MenuItemCompat.getActionProvider(shareOpt);
		
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_TEXT, "Mira esta noticia de GoTechAndroid: " + link);
		myShareActionPRovider.setShareIntent(i);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_cuerpo_noticia,
					container, false);
			return rootView;
		}
	}

	
	
	private String traduceHTML(String html){
		
		html=html.replace("á", "&aacute");
		html=html.replace("é", "&eacute");
		html=html.replace("í", "&iacute");
		html=html.replace("ó", "&oacute");
		html=html.replace("ú", "&uacute");
		
		html=html.replace("Á", "&Aacute");
		html=html.replace("É", "&Eacute");
		html=html.replace("Í", "&Iacute");
		html=html.replace("Ó", "&Oacute");
		html=html.replace("Ú", "&Uacute");
		
		html=html.replace("ñ", "&ntilde");
		html=html.replace("Ñ", "&Ntilde");
		
		//html=html.replace("!", "&#33");
		html=html.replace("¡", "&#161");
		html=html.replace("?", "&#63");
		html=html.replace("¿", "&#191");
		//html=html.replace("%", "&#37");
		
		return html;
	}
	
	
	private String agregaEstilo(String html, String titulo, String fecha, String autor){
		
		String estilo = "<style>@font-face {font-family: 'roboto'; src: url('robotocondensedlight.ttf');} body {font-family: 'roboto'; color: black;text-align: justify;line-height:26px;} </style>"+
				"<h1 style=\"color:grey; font-style: bold; font-size:22px;text-align: left;\">"+traduceHTML(titulo)+"</h1><hr>"+"<h3 style=\"font-size:12px;font-style: italic;color:grey;\">"+traduceHTML(obtenerAutor(autor))+" / "+generarFecha(fecha)+"</h3>"+"<hr><p>";
		estilo+=html;
		
			
		return estilo;
	}
	
	private String obtenerAutor(String autor){
		
		autor=autor.substring(autor.indexOf("(")+1,autor.indexOf(")"));
		return autor;
		
	}
	
	private String generarFecha(String fecha){
		
		fecha = fecha.replace("Mon", "Lunes");
		fecha = fecha.replace("Tue", "Martes");
		fecha = fecha.replace("Wed", "Miércoles");
		fecha = fecha.replace("Thu", "Jueves");
		fecha = fecha.replace("Fri", "Viernes");
		fecha = fecha.replace("Sat", "Sábado");
		fecha = fecha.replace("Sun", "Domingo");
		
		fecha = fecha.replace(" Jan ", " Enero ");
		fecha = fecha.replace(" Feb ", " Febrero ");
		fecha = fecha.replace(" Mar ", " Marzo ");
		fecha = fecha.replace(" Apr ", " Abril ");
		fecha = fecha.replace(" May ", " Mayo ");
		fecha = fecha.replace(" Jun ", " Junio ");
		fecha = fecha.replace(" Jul ", " Julio ");
		fecha = fecha.replace(" Aug ", " Agosto ");
		fecha = fecha.replace(" Sep ", " Septiembre ");
		fecha = fecha.replace(" Oct ", " Octubre ");
		fecha = fecha.replace(" Nov ", " Noviembre ");
		fecha = fecha.replace(" De c", " Diciembre ");
		
		fecha = fecha.substring(0,fecha.indexOf(":")-3);
		
		String fecha_1parte = fecha.substring(0,fecha.indexOf(",")+2);
		String fecha_2parte = fecha.substring(fecha.indexOf(",")+2,fecha.length()).replace(" ", " de ");
		
		
		
		return fecha_1parte+fecha_2parte;
	}
	
	private String capturaTagsHTML(String html){
		String prueba="";
		String ancho ="";
		String alto ="";
		String medidas = "";
		String aux = html;
		
		try{
		if(html.contains("<img") && html.contains("height")){
			while (aux.contains("<img")){ 
				int pos = aux.indexOf("<img"); 
				aux = aux.substring(pos,aux.length());
				prueba = aux;
				prueba = prueba.substring(0,prueba.indexOf("/>")+2);
				aux = aux.replace(prueba, "");
				prueba = prueba.substring(prueba.indexOf("height"),prueba.lastIndexOf("\"")+1);
				
				alto = prueba.substring(prueba.indexOf("height"),prueba.indexOf("width"));
				alto = alto.substring(alto.indexOf("\"")+1,alto.lastIndexOf("\""));
				alto = Integer.parseInt(alto)/2+"";
				
				ancho = prueba.substring(prueba.indexOf("width"),prueba.lastIndexOf("\"")+1);
				ancho = ancho.substring(ancho.indexOf("\"")+1,ancho.lastIndexOf("\""));
				ancho = Integer.parseInt(ancho)/2+"";
				
				 medidas ="height = \""+alto+"\" width=\""+ancho+"\"";
				 html = html.replace(prueba, medidas);
			}
		}
		
		
		}
		
		catch(Exception s){
			Toast.makeText(this, "Uuuups, ha ocurrido un error al cargar la noticia. Inténtalo más tarde: "+s.getMessage(), Toast.LENGTH_LONG).show();
		}
		return html;
	}
	

}
