package controller;

import org.json.JSONObject;

public interface APIListener {
	public abstract void onResult(Object res,Exception e);
	public abstract void onHold();
	public abstract void onStart();
}
