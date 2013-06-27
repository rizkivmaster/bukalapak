package view;

import service.APIService;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bukalapaklogin.R;

import controller.APIController;
import controller.APIListener;
import controller.AsyncTaskResult;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {
	ProgressBar progress;
	EditText userText;
    EditText passText;
    private APIService api;
    
    private ServiceConnection  mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			api = ((APIService.MyBinder) arg1).getService();
	        Toast.makeText(MainActivity.this, "Connected to API Service", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Toast.makeText(MainActivity.this, "Disconnected from API Service", Toast.LENGTH_SHORT).show();
		}

      };
      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, APIService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        userText = (EditText) findViewById(R.id.editText1);
        passText = (EditText) findViewById(R.id.editText2);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        
        Button submitBtn = (Button) findViewById(R.id.button1);
        submitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					String username = userText.getText().toString();
					String password = passText.getText().toString();
					api.retrieveNewAccess(username,password , new APIListener() {

						@Override
						public void onSuccess(Object res, Exception e) {
							progress.setVisibility(ProgressBar.GONE);
							if(e==null)
								startActivity(new Intent(MainActivity.this,Berhasil.class));
							else
								Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onHold() {
							progress.setVisibility(ProgressBar.GONE);
							Toast.makeText(MainActivity.this, "Connection is pending", Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onExecute() {
							progress.setVisibility(ProgressBar.VISIBLE);
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onEnqueue() {
							// TODO Auto-generated method stub
							
						}
						
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
    }
    
    @Override
    protected void onResume() {
      super.onPause();
      bindService(new Intent(this, APIService.class), mConnection,Context.BIND_AUTO_CREATE);
    }
    
    @Override
    protected void onPause() {
      super.onPause();
      unbindService(mConnection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
//    class PostData extends AsyncTask<String, AsyncTaskResult<String>, AsyncTaskResult<String>>
//    {
//    	APIController api;
//    	@Override
//    	protected void onPreExecute() {
//    		// TODO Auto-generated method stub
//    		super.onPreExecute();
//    		progress.setVisibility(ProgressBar.VISIBLE);
//    	}
//		@Override
//		protected AsyncTaskResult<String> doInBackground(String... arg0) {
//			// TODO Auto-generated method stub
//	        // Create a new HttpClient and Post Header
//			String username = userText.getText().toString();
//			String password = passText.getText().toString();
//			try {
//				api = new APIController(getApplicationContext(),username,password);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				return new AsyncTaskResult<String>(e);
//			}
//			return new AsyncTaskResult<String>("");
//		}
//		
//		protected void onPostExecute(AsyncTaskResult<String> result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			if(result.getError()!=null)
//			{
//				Toast.makeText(getApplicationContext(), result.getError().getMessage(), Toast.LENGTH_LONG).show();
//				Log.e("Error", result.getError().getMessage());
//			}
//			else
//			{
//				Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_LONG).show();
//				startActivity(new Intent(getApplicationContext(),Berhasil.class));
//				finish();
//			}
//			progress.setVisibility(ProgressBar.GONE);
//		}
//			
//    }
//    
//    class Test extends AsyncTask<String, String, String>
//    {
//
//		@Override
//		protected String doInBackground(String... params) {
//			// TODO Auto-generated method stub
//			APIController api = new APIController(getApplicationContext());
//			try {
//				api.listProducts(null,1, 1);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
//		}
//    	
//    }
    
}
