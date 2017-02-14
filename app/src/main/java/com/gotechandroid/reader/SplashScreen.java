package com.gotechandroid.reader;

import com.gotechandroid.reader.R;
import com.gotechandroid.reader.util.SystemUiHider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SplashScreen extends Activity {
	 // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        try{
        setContentView(R.layout.activity_splash_screen);
        Typeface miTipo = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotocondensedlight.ttf");
        TextView txEstado = (TextView)findViewById(R.id.txEstado);
        txEstado.setText("Cargando noticias...");
        txEstado.setTypeface(miTipo);
        ConnectivityManager conMgr = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if(i==null){
	    	Toast.makeText(this, "Conexión de datos no disponible, por favor, conéctate a una red y vuelve a intentarlo.", Toast.LENGTH_LONG).show();
	        this.finish();
	    	return;
    	}
        if(!i.isConnected()||!i.isAvailable()){
        	Toast.makeText(this, "Conexión de datos no disponible, por favor, conéctate a una red y vuelve a intentarlo.", Toast.LENGTH_LONG).show();
	        	this.finish();
	        	return;
        	}
        
        TextView txUrl = (TextView)findViewById(R.id.txUrl);
    	txUrl.setTypeface(miTipo);
    	txUrl.setText("www.gotechandroid.com");
        }
        catch(Exception s){
        	
        }
  
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
        	
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
            	
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        
    }
}



