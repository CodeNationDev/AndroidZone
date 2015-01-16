package com.gotechandroid.reader;

import com.gotechandroid.reader.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeFragment extends Fragment {
	 ProgressDialog progressDialog;
	
	public HomeFragment(){}

	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
	
		View rootView = inflater.inflate(R.layout.home, container,false);
		
		 WebView wb = new WebView(rootView.getContext());
	     wb =(WebView)rootView.findViewById(R.id.navegador);
	     wb.setWebViewClient(new WebViewClient());
	     wb.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
	     wb.getSettings().setLoadWithOverviewMode(true);
	     wb.getSettings().setUseWideViewPort(true);
	     wb.getSettings().setBuiltInZoomControls(true);
	    
	     progressDialog = new ProgressDialog(rootView.getContext());
	     progressDialog.setMessage("Cargando sitio...");
	     progressDialog.show();
	     
	     wb.setWebViewClient(new WebViewClient(){
	    	 @Override
	    	 public void onPageFinished(WebView view, final String url){
	    		 progressDialog.dismiss();
	    	 }
	     });
	     
	     wb.loadUrl("http://www.gotechandroid.com");
		
	 	return rootView;
	}
	
   
}
