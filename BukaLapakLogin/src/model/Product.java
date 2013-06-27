package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;

public class Product {

	private HashMap<String,String> specs;
	private String id;
	private String name;
	public HashMap<String, String> getSpecs() {
		return specs;
	}
	public void setSpecs(HashMap<String, String> specs) {
		this.specs = specs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ArrayList<String> getCategory_structure() {
		return category_structure;
	}
	public void setCategory_structure(ArrayList<String> category_structure) {
		this.category_structure = category_structure;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getNego() {
		return nego;
	}
	public void setNego(String nego) {
		this.nego = nego;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public String getPayment_ready() {
		return payment_ready;
	}
	public void setPayment_ready(String payment_ready) {
		this.payment_ready = payment_ready;
	}
	private String category;
	private ArrayList<String> category_structure;
	private String city;
	private String province;
	private ArrayList<String> images;
	private String url;
	private String desc;
	private String price;
	private String condition;
	private String nego;
	private String seller_name;
	private String payment_ready;
	public Product(String id)
	{
		this.id = id;
	}
		
}
