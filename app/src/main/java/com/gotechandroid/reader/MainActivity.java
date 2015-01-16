package com.gotechandroid.reader;

import java.util.ArrayList;
import com.gotechandroid.reader.R;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


@SuppressLint("Recycle")
public class MainActivity extends Activity{
	String[] titulos;
	private ActionBarDrawerToggle mDraweToggle;
	private DrawerLayout NavDrawerLayout;
	private ListView NavList;
	private ArrayList<Item_object> NavItem;
	private TypedArray NavIcons;
	NavigationAdapter NavAdapter;
	Context contexto;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = getApplicationContext();
        
      
       
        mDraweToggle = new ActionBarDrawerToggle(this, NavDrawerLayout, 
        		R.drawable.ic_navigation_drawer, 
        		R.string.app_name, 
        		R.string.app_name){
        	public void onDrawerClosed(View view){
        		Log.e("Cerrado completo","!!");
        	}
        	
        	public void onDrawerOpened(View drawerView){
        		Log.e("Apertura completa", "!!");
        		
        		}
        	};
            	          
     NavDrawerLayout = (DrawerLayout) findViewById(R.id.container);
     NavList = (ListView)findViewById(R.id.lista);
     NavDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
     View header = getLayoutInflater().inflate(R.layout.header,null);
     
     NavList.addHeaderView(header);
      
     NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
     titulos = getResources().getStringArray(R.array.nav_options);
     NavItem = new ArrayList<Item_object>();
     NavItem.add(new Item_object(titulos[0],NavIcons.getResourceId(0, -1)));
     NavItem.add(new Item_object(titulos[1], NavIcons.getResourceId(1, -1)));
     NavItem.add(new Item_object(titulos[2], NavIcons.getResourceId(2, -1)));
     NavItem.add(new Item_object(titulos[3], NavIcons.getResourceId(3, -1)));
     
     NavAdapter = new NavigationAdapter(this, NavItem);
     NavList.setAdapter(NavAdapter);
     
     NavDrawerLayout.setDrawerListener(mDraweToggle);
 	getActionBar().setDisplayHomeAsUpEnabled(true);
        
    
    

	
 	
     NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			MostrarFragment(position);
			
		}
    	 
    	 
	});
        
     MostrarFragment(3);
     
     
    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    
    private void MostrarFragment(int position){
    	
    	
    	HomeFragment home = null;
    	ProfileFragment profile_fragment  = null;
    	LectorFragment lector_todas = null;
    	LectorFragment lector_24h = null;
    	switch(position){
    	case 1:
    	    home = new HomeFragment();
    		break;
    	case 2:
    		profile_fragment = new ProfileFragment();
    		break;
    	case 3:
    	lector_todas = new LectorFragment();
    	break;
    	case 4:
        	lector_24h = new LectorFragment();
    	break;
    	
    	
    	
    	default:
    		Toast.makeText(getApplicationContext(), "Opcion "+titulos[position-1]+" no disponible", Toast.LENGTH_LONG).show();
    		
    		home = new HomeFragment();
    		position = 1;
    		break;
    	
    	}
    	
    	if(home!=null){
    		FragmentManager fragmentManager = getFragmentManager();
    		getActionBar().setTitle("Visitar sitio");
    		getActionBar().setSubtitle("Navegador");
    		fragmentManager.beginTransaction().replace(R.id.content_frame, home).commit();
    		NavList.setItemChecked(position, true);
    		NavList.setSelection(position);
    		
    		setTitle(titulos[position-1]);
    		NavDrawerLayout.closeDrawer(NavList);
    		overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
    	}
    	
    	if(profile_fragment!=null){
    		FragmentManager fragmentManager = getFragmentManager();
    		getActionBar().setTitle("Sígueme");
    		getActionBar().setSubtitle("Pulsa las imágenes");
    		fragmentManager.beginTransaction().replace(R.id.content_frame, profile_fragment).commit();
    		NavList.setItemChecked(position, true);
    		NavList.setSelection(position);
    		
    		setTitle(titulos[position-1]);
    		NavDrawerLayout.closeDrawer(NavList);
    		overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
    	}
    	if(lector_todas!=null){
    		FragmentManager fragmentManager = getFragmentManager();
    		getActionBar().setTitle("Noticias");
    		getActionBar().setSubtitle("Todas las noticias");
    		fragmentManager.beginTransaction().replace(R.id.content_frame, lector_todas).commit();
    		NavList.setItemChecked(position, true);
    		NavList.setSelection(position);
    		Bundle bundle = new Bundle();
    		Constantes c = new Constantes();
    		bundle.putString("url", c.URL_TODAS);
    		lector_todas.setArguments(bundle);
    		setTitle(titulos[position-1]);
    		NavDrawerLayout.closeDrawer(NavList);
    		overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
    	}
    	
    	if(lector_24h!=null){
    		FragmentManager fragmentManager = getFragmentManager();
    		getActionBar().setTitle("Noticias");
    		getActionBar().setSubtitle("Últimas 24h");
    		fragmentManager.beginTransaction().replace(R.id.content_frame, lector_24h).commit();
    		NavList.setItemChecked(position, true);
    		NavList.setSelection(position);
    		Bundle bundle = new Bundle();
    		Constantes c = new Constantes();
    		bundle.putString("url", c.url_hoy());
    		lector_24h.setArguments(bundle);
    		setTitle(titulos[position-1]);
    		NavDrawerLayout.closeDrawer(NavList);
    		overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
    	}
    }
    
  
    
    
}
