package model;

import java.util.HashMap;
import java.util.List;

import android.graphics.Bitmap;

public class Product {
	HashMap<String,String> attributes;
	String id;
	List<Bitmap> images;
	
	public Product(String id)
	{
		this.id = id;
	}
	
	public HashMap<String,String> getAttributes()
	{
		return attributes;
	}
	
	public void setAttributes(HashMap<String,String> attr)
	{
		this.attributes = attr;
	}
	
	public List<Bitmap> getImages()
	{
		return images;
	}
	
	public void setImages(List<Bitmap> img)
	{
		images = img;
	}
	
}
