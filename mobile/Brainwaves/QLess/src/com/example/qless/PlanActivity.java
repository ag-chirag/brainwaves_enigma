package com.example.qless;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PlanActivity extends Activity {

	EditText purpose_text;
	Spinner time_list;
	Spinner category_list;
	Button submit;
	String pan="111";
	String category_slot;
	SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan);
		purpose_text=(EditText)findViewById(R.id.purpose);
		time_list=(Spinner)findViewById(R.id.time_list);
		time_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				// TODO Auto-generated method stub
				Object selection=parent.getItemAtPosition(pos);
				String time_slot=selection.toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		category_list=(Spinner)findViewById(R.id.category_list);
		category_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				// TODO Auto-generated method stub
				Object selection=parent.getItemAtPosition(pos);
				category_slot=selection.toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		submit=(Button)findViewById(R.id.submit);
	}	
	public void OnSubmitButtonClick(View v)
	{ 
		//submit the data obtained to the server.
		sp=getSharedPreferences("bank",MODE_PRIVATE);
		int bid=sp.getInt("bankid",1111);
		new PlanTask(this,this).execute("?pan="+pan+"&bid="+String.valueOf(bid)+"&category="+category_slot.toString()+"&purpose="+purpose_text.getText().toString());
	}
}
