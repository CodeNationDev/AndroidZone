package com.gotechandroid.feedreader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;




import android.os.AsyncTask;


class RetrieveFeedTask extends AsyncTask<String, Void, List<Noticia>> {
	
    @SuppressWarnings("unused")
	private Exception exception;
   
   
    

    protected List<Noticia> doInBackground(String... urls) {
        try {
            URL url= new URL(urls[0]);
            SAXParserFactory factory =SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlreader=parser.getXMLReader();
            RssHandler theRSSHandler=new RssHandler();
            xmlreader.setContentHandler(theRSSHandler);
            InputSource is=new InputSource(url.openStream());
            xmlreader.parse(is);
            List<Noticia> noticias = new ArrayList<Noticia>();
            noticias = theRSSHandler.getNoticias();
          
            return noticias;
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(List<Noticia> feed) {
       
    }
    
 
}