package listener;

import model.system.InternetTask;

public interface APIListener {
	public abstract void onEnqueue(InternetTask task);
	public abstract void onExecute(InternetTask task);
	public abstract void onSuccess(Object res,Exception e,InternetTask task);
	public abstract void onHold(InternetTask task);
}
