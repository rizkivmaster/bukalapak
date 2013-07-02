package view.product.list;

import java.util.ArrayList;
import java.util.List;

import com.bukalapakmobile.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ListProductActivity extends Activity implements OnItemSelectedListener{
	TextView header;
	Button btn_action;
	Spinner spinner_action;
    
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.view_product_list_main);
		 header = (TextView) findViewById(R.id.header);
		 btn_action = (Button) findViewById(R.id.btn_action);
		 spinner_action =(Spinner) findViewById(R.id.spinner_action);
		 
		 // Spinner click listener
	        spinner_action.setOnItemSelectedListener(this);
	 
	        // Spinner Drop down elements
	        List<String> categories = new ArrayList<String>();
	        categories.add("Hapus");
	        categories.add("Set terjual");
	        categories.add("Set tidak dijual");
	        
	     // Creating adapter for spinner
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
	 
	        // Drop down layout style - list view with radio button
	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
	        spinner_action.setAdapter(dataAdapter);
	    }
	 
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
