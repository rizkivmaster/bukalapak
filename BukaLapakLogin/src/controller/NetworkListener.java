package controller;

import org.json.JSONObject;

public interface NetworkListener {
	public abstract void onGivingResult(JSONObject res);
}
