package com.example.qless;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StatusActivity extends Activity {

	TextView customer1,customer2,customer3,customer4;
	TextView time1,time2,time3,time4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		customer1=(TextView)findViewById(R.id.customer_number1);
		customer2=(TextView)findViewById(R.id.customer_number2);
		customer3=(TextView)findViewById(R.id.customer_number3);
		customer4=(TextView)findViewById(R.id.customer_number4);
	
		time1=(TextView)findViewById(R.id.time_left1);
		time2=(TextView)findViewById(R.id.time_left2);
		time3=(TextView)findViewById(R.id.time_left3);
		time4=(TextView)findViewById(R.id.time_left4);
		
		new StatusTask(this,this,customer1,customer2,customer3,customer4,time1,time2,time3,time4).execute("0");
	}
}