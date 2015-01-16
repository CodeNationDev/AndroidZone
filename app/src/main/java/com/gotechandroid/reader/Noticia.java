package com.gotechandroid.reader;

import android.media.Image;

public class Noticia {
    private String titulo;
    private String link;
    private String descripcion;
    private String guid;
    private String fecha;
    private Image imagen;
    private String category;
    private String author;
 
    public String getTitulo() {
        return titulo;
    }
 
    public String getLink() {
        return link;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    public String getGuid() {
        return guid;
    }
 
    public String getFecha() {
        return fecha;
    }
    
    public Image getImage(){
    	return imagen;
    }
    
    public String getCategory(){
    	return category;
    }
    
    public String getAuthor(){
    	return author;
    }
    
    public void setImage(Image i){
    	imagen = i;
    }
 
    public void setTitulo(String t) {
        titulo = t;
    }
 
    public void setLink(String l) {
        link = l;
    }
 
    public void setDescripcion(String d) {
        descripcion = d;
    }
 
    public void setGuid(String g) {
        guid = g;
    }
 
    public void setFecha(String f) {
        fecha = f;
    }
    
    public void setCatgory(String c){
    	category = c;
    }
    
    public void setAuthor(String a){
    	author = a;
    }
}