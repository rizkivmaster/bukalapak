package model.system;

import java.io.Serializable;

import listener.APIListener;
import listener.AppListener;
import listener.NetworkListener;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONObject;

public class InternetTask implements Comparable<InternetTask>{
	private HttpUriRequest request;
	private NetworkListener networkListener;
	private APIListener apiListener;
	private AppListener appListener;
	private int priority; 
	
	public HttpUriRequest getRequest() {
		return request;
	}

	public void setNetworkListener(NetworkListener networkListener) {
		this.networkListener = networkListener;
	}
	public void setRequest(HttpUriRequest request) {
		this.request = request;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}


	public void setAppListener(AppListener appListener) {
		this.appListener = appListener;
	}

	public InternetTask(HttpUriRequest r,int priority,APIListener l)
	{
		apiListener= l;
		request = r;
	}
	
	public void tellHold()
	{
		if(apiListener!=null)
		{
			apiListener.onHold(this);
		}
	}
	
	public void tellStart()
	{
		if(apiListener!=null)
		{
			apiListener.onExecute(this);
		}
	}
	
	public void tellResult(Object res,Exception e)
	{
		if(apiListener!=null)
		{
			apiListener.onSuccess(res, e,this);
		}
	}
	
	public void tellFinish(JSONObject res)
	{
		if(networkListener!=null)
		{
			networkListener.onGivingResult(res);
		}
	}
	
	public void tellEnqueued()
	{
		if(apiListener!=null)
		{
			apiListener.onEnqueue(this);
		}
	}
	
	public void cancelProcess()
	{
		if(request!=null)
		{
			request.abort();
		}
		if(appListener!=null)
		{
			appListener.onCancel();
		}
	}

	@Override
	public int compareTo(InternetTask arg0) {
		// TODO Auto-generated method stub
		return getPriority();
	}
}
