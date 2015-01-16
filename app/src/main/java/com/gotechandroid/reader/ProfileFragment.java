package com.gotechandroid.reader;

import com.gotechandroid.reader.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ProfileFragment extends Fragment {
public ProfileFragment(){}

@Override
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
	
	View rootView = inflater.inflate(R.layout.profile, container,false);
	ImageView goPlus = (ImageView)rootView.findViewById(R.id.gotechplus);
	ImageView goFace = (ImageView)rootView.findViewById(R.id.gotechface);
	ImageView goTwitt = (ImageView)rootView.findViewById(R.id.gotehtwitt);
	
	goTwitt.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			try{
				startActivity(getOpenTwitterIntent(getActivity()));
			}
			catch(Exception s){
				Log.e("Error conectando a Twitter", s.getMessage());	
			}
		}
	});
	
	goFace.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
				try{
					
					startActivity(getOpenFacebookIntent(getActivity()));
					}
					catch(Exception s){
					Log.e("Error conectando a Facebook", s.getMessage());	
						
					}
		}
	});
	
	
	goPlus.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			try{
			startActivity(getOpenGooglePlusIntent(getActivity()));
		}
		catch(Exception s){
		Log.e("Error conectando a google plus", s.getMessage());	
			
		}
			startActivity(getOpenGooglePlusIntent(getActivity()));
			
		}
	});
		
	return rootView;
	
}
public static class PlaceholderFragment extends Fragment {

	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.profile,
				container, false);
		return rootView;
	}
}




public static Intent getOpenTwitterIntent(Context context) {

	   try {
	    context.getPackageManager().getPackageInfo("com.twitter.android", 0);
	    return new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=2279598302"));
	   } catch (Exception e) {
	    return new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/2279598302"));
	   }
	}



public static Intent getOpenFacebookIntent(Context context) {

	   try {
	    context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
	    return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/gotechandroid"));
	   } catch (Exception e) {
	    return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/gotechandroid"));
	   }
	}
	
public static Intent getOpenGooglePlusIntent(Context context) {

	   try {
	    context.getPackageManager().getPackageInfo("com.google.android.apps.plus", 0);
	    return new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/109503197422935839184/posts"));
	   } catch (Exception e) {
	    return new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/109503197422935839184/posts"));
	   }
	}
}
