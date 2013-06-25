package controller;

import org.json.JSONObject;

public interface APIListener {
	public abstract void onEnqueue();
	public abstract void onExecute();
	public abstract void onSuccess(Object res,Exception e);
	public abstract void onHold();
}
