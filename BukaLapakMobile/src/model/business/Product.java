package model.business;

import java.util.HashMap;
import java.util.List;

import android.graphics.Bitmap;

public class Product {
	public HashMap<String, String> getSpecs() {
		return specs;
	}

	public void setSpecs(HashMap<String, String> specs) {
		this.specs = specs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Bitmap> getImages() {
		return images;
	}

	public void setImages(List<Bitmap> images) {
		this.images = images;
	}

	private HashMap<String,String> specs;
	private String id;
	private String name;
	private int price;
	List<Bitmap> images;
	
	public Product(String id)
	{
		this.id = id;
	}
		
}
