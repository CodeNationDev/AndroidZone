package com.gotechandroid.feedreader;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
/*import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;*/
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;




public class LectorFragment extends Fragment{
	public LectorFragment(){}
	
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_lector_feed, container,false);
		List<Noticia> noticias = null;		
		setHasOptionsMenu(true);
		
		try{	
		
		 noticias = new ArrayList<Noticia>();
		 
		    String[] urls = {getArguments().getString("url") };
		    
		    AsyncTask<String, Void, List<Noticia>> pagina = new RetrieveFeedTask().execute(urls);
		    noticias = pagina.get();	 
		    
		   
		    /*File dirPath = new File("Android/data/com.gotechandroid.reader");
		    if(!dirPath.exists())
		    	dirPath.mkdir();*/
		    
		    
			String[] txNoticias = new String[noticias.size()];
		    String[] txFechas = new String[noticias.size()];
		    String[] txResumen = new String[noticias.size()];
		    Integer[] imNoticias = new Integer[noticias.size()];
		    
		    
		    if(noticias.size()>0){
			    
			    for(int i=0;i<noticias.size();i++){
			    	txNoticias[i]=noticias.get(i).getTitulo();
			    	txFechas[i]=noticias.get(i).getFecha().substring(noticias.get(i).getFecha().indexOf(",")+1,noticias.get(i).getFecha().indexOf(":")-2);
			    	txResumen[i]=extraerResumen(noticias.get(i).getDescripcion());
			    	
			    if(noticias.get(i).getCategory()!=null && noticias.get(i).getCategory().equals("samsung"))
			    	imNoticias[i]=R.drawable.samsungicon;
			    else if(noticias.get(i).getCategory()!=null && noticias.get(i).getCategory().equals("lg"))
			    	imNoticias[i]=R.drawable.lgicon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("general"))
			    	imNoticias[i]=R.drawable.ic_launcher;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("aplicaciones_ofertas"))
			    	imNoticias[i]=R.drawable.googleplayflat;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("1+"))
			    	imNoticias[i]=R.drawable.oneplus;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("amd"))
			    	imNoticias[i]=R.drawable.amdicon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("twitter"))
			    	imNoticias[i]=R.drawable.twitter;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("amazon"))
			    	imNoticias[i]=R.drawable.amazonblackicon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("nvidia"))
			    	imNoticias[i]=R.drawable.nvidiaicon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("android"))
			    	imNoticias[i]=R.drawable.androidicon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("gadgets"))
			    	imNoticias[i]=R.drawable.gadgeticon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("nexus"))
			    	imNoticias[i]=R.drawable.nexusicon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("google"))
			    	imNoticias[i]=R.drawable.googleicon;
			    else if(noticias.get(i).getCategory()!=null &&noticias.get(i).getCategory().equals("motorola"))
			    	imNoticias[i]=R.drawable.motorolaicon;
			    else
			    	imNoticias[i]=R.drawable.logonombre;
	
			    }
		    }
		    else
		    {
		    	txNoticias = new String[1];
		    	txFechas = new String[1];
		    	imNoticias = new Integer[1];
		    	Calendar cal = Calendar.getInstance();
		    	txResumen = new String[1];
		    	
		    	
    	
		    	txNoticias[0]="No hay nuevas noticias para mostrar...";
		    	txFechas[0]= cal.get(Calendar.DAY_OF_MONTH)+" de "+traduceFechas(cal.get(Calendar.MONTH)+"")+" de "+cal.get(Calendar.YEAR);
 		    	imNoticias[0]=R.drawable.logonombre;
 		    	txResumen[0]="Mantente atento, pronto se publicarán nuevos artículos en GoTechAndroid...";
		    }
		    
		    ListView listaNoticias = (ListView)rootView.findViewById(R.id.listaNoticias);
		    listaNoticias.setAnimation(AnimationUtils.loadAnimation(rootView.getContext(), R.anim.abc_fade_in));
		    CustomAdapter adapter = new CustomAdapter(getActivity(), txNoticias,txFechas,imNoticias,txResumen);
		    listaNoticias.setAdapter(adapter);
		    
		    Animation anim = AnimationUtils.loadAnimation(rootView.getContext(), android.R.anim.slide_out_right);
		    anim.setDuration(500);
		    
		
		    final List<Noticia> noticias_int = noticias;
		    
		    
		    listaNoticias.setOnItemClickListener(new OnItemClickListener() {
		    	
		    	public void onItemClick(AdapterView<?>parent,View view, int position, long id){
		    		
		    		Intent intent = new Intent(getActivity(), CuerpoNoticia.class);
		    		intent.putExtra("cuerpo", noticias_int.get(position).getDescripcion());
		    		intent.putExtra("titulo", noticias_int.get(position).getTitulo());
		    		intent.putExtra("fecha", noticias_int.get(position).getFecha());
		    		intent.putExtra("autor", noticias_int.get(position).getAuthor());
		    		intent.putExtra("link", noticias_int.get(position).getLink());
		    		startActivity(intent);
		    		
		    		
		    		
		    	}
		    	
			});	
		   
		}
		catch(Exception s)
		{
			Log.e("ERROR", s.getMessage());
		}
		
		   return rootView;
		
	}
	
/*	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
		
		inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.numero_noticias, menu);
		super.onCreateOptionsMenu(menu,inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}
	*/
	public String extraerResumen(String cuerpo){
		
		cuerpo = Html.fromHtml(cuerpo).toString();
		cuerpo = cuerpo.substring(0,cuerpo.indexOf("."));
		cuerpo = cuerpo.replaceAll("\\p{C}", "|");
		cuerpo = cuerpo.substring(cuerpo.lastIndexOf("|")+1,cuerpo.length())+"...";
		
		return cuerpo;
	}
	
	public String traduceFechas(String mes){
		
		mes=mes.replace("1", "Enero");
		mes=mes.replace("2", "Febrero");
		mes=mes.replace("3", "Marzo");
		mes=mes.replace("4", "Abril");
		mes=mes.replace("5", "Mayo");
		mes=mes.replace("6", "Junio");
		mes=mes.replace("7", "Julio");
		mes=mes.replace("8", "Agosto");
		mes=mes.replace("9", "Septiembre");
		mes=mes.replace("10", "Octubre");
		mes=mes.replace("11", "Noviembre");
		mes=mes.replace("12", "Diciembre");
		
		return mes;
	}
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_lector_feed,
					container, false);
			return rootView;
		}
	}
	
}
