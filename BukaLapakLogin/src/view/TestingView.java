package view;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import service.APIService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.widget.Toast;

import com.example.bukalapaklogin.R;

import controller.APIController;
import controller.AsyncTaskResult;

public class TestingView extends Activity {

private APIService api;
    
    private ServiceConnection  mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			api = ((APIService.MyBinder) arg1).getService();
	        Toast.makeText(getApplicationContext(), "Connected to API Service", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Toast.makeText(getApplicationContext(), "Disconnected from API Service", Toast.LENGTH_SHORT).show();
		}
      };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testing_view);
		Intent intent = new Intent(this, APIService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
//        api.retrieveNewAccess("rizkivmaster", pwd, l)
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_testing_view, menu);
		return true;
//	}
//	class PostData extends AsyncTask<String, AsyncTaskResult<String>, AsyncTaskResult<String>>
//    {
//    	APIController api;
//		@Override
//		protected AsyncTaskResult<String> doInBackground(String... arg0) {
//			// TODO Auto-generated method stub
//	        // Create a new HttpClient and Post Header
//			try {
//				api = new APIController(getApplicationContext(),"rizkivmaster","18091992gnome");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				return new AsyncTaskResult<String>(e);
//			}
//			return null;
//		}
//		@Override
//		protected void onPostExecute(AsyncTaskResult<String> result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//
//	        new Test().execute();
//		}
//			
//    }
//
//	class Test extends AsyncTask<String, String, String>
//    {
//		
//		@Override
//		protected String doInBackground(String... params) {
//			// TODO Auto-generated method stub
//			APIController api = new APIController(getApplicationContext());
//			String result = null;
//			InputStream is;
//			try {
//				is = getAssets().open("sepeda.jpg");
//				Bitmap bm = BitmapFactory.decodeStream(is);
//				JSONObject response =  api.createImage(bm);
//				result = response.getString("id");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return result;
//		}
//		@Override
//		protected void onPostExecute(String result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			new  TestUploadProduk().execute(result);
//		}
//    	
//    }
//	class TestUploadProduk extends AsyncTask<String, String, String>
//    {
//		
//		@Override
//		protected String doInBackground(String... params) {
//			// TODO Auto-generated method stub
//			APIController api = new APIController(getApplicationContext());
//			String result = null;
//			InputStream is;
//			try {
//				api.createProduct(params[0]);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return result;
//		}
//		@Override
//		protected void onPostExecute(String result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			
//		}
//    	
    }
}
