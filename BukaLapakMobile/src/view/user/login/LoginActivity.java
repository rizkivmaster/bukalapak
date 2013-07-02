package view.user.login;

import com.bukalapakmobile.R;

import listener.APIListener;
import services.APIService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class LoginActivity extends Activity {
	ProgressBar progress;
	EditText userText;
    EditText passText;
    private APIService api;
    
    private ServiceConnection  mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			api = ((APIService.MyBinder) arg1).getService();
	        Toast.makeText(LoginActivity.this, "Connected to API Service", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Toast.makeText(LoginActivity.this, "Disconnected from API Service", Toast.LENGTH_SHORT).show();
		}

      };
      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_login_main);
        Intent intent = new Intent(this, APIService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        userText = (EditText) findViewById(R.id.login_email_username);
        passText = (EditText) findViewById(R.id.login_password);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        
        Button submitBtn = (Button) findViewById(R.id.btnLogin);
        submitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					String username = userText.getText().toString();
					String password = passText.getText().toString();
					api.retrieveNewAccess(username,password ,null/* new APIListener() {

						@Override
						public void onSuccess(Object res, Exception e) {
							progress.setVisibility(ProgressBar.GONE);
							if(e==null)
								startActivity(new Intent(LoginActivity.this,Berhasil.class));
							else
								Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onHold() {
							progress.setVisibility(ProgressBar.GONE);
							Toast.makeText(LoginActivity.this, "Connection is pending", Toast.LENGTH_SHORT).show();
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
						
					}*/);
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
    
}