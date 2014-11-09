package com.example.qless;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Home extends Activity implements LocationListener {

	private static final TextView CheckinTask = null;

	//glob obj=new glob();
	Button status_button,checkin_button,plan_button;
	
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected Context context;
	
	protected String latitude,longitude; 
	protected boolean gps_enabled,network_enabled;
	protected double loc_lat,loc_long,purpose;
	TextView location;
	TextView welcome; 
	SharedPreferences sp,sp1,sp2;
	
	static public String pan,prps;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        status_button=(Button)findViewById(R.id.status_button);
        checkin_button=(Button)findViewById(R.id.checkin_button);
        plan_button=(Button)findViewById(R.id.plan_button);
        welcome=(TextView)findViewById(R.id.welcome);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        sp=getSharedPreferences("name",Context.MODE_PRIVATE);
        welcome.setText("Welcome " + sp.getString("name","dummy"));
        pan=sp.getString("userid", "007");
        
        sp1=getSharedPreferences("bank",Context.MODE_PRIVATE);
      //  bid=sp1.getString("bankid","000");
    }


    public void OnStatusButtonClick(View v)
    {
    	Intent i=new Intent(this,StatusActivity.class);
    	startActivity(i);
    }
    public double mode(double  d)
    {
    	if( d<0)
    		return - d;
    	else
    		return d;
    }
    public void OnCheckinButtonClick(View v)
    {
    	
    	if((mode(loc_lat - 10))<0)
    		Toast.makeText(Home.this,"This feature is available only when you enter the bank",Toast.LENGTH_LONG).show();
    	else
    	{
    		//code to check purpose from database
    		SharedPreferences userid = getSharedPreferences("userid",0);
    		int pan = userid.getInt("userid", 0);
    			 new CheckinTask(this,prps,this).execute(String.valueOf(pan));
    			//we will pass array of string here.
    			 Toast.makeText(Home.this,prps,Toast.LENGTH_LONG).show();
    		//System.out.println("Nouman1: "+glob.prs);
    		sp2=getSharedPreferences("DataStorage",Context.MODE_PRIVATE);
    		System.out.println("Vishan " + sp2.getString("val","1111"));
    		prps=sp2.getString("val","1111");
    		System.out.println("home"+prps);
    		  if(prps.equals("1"))
    		  {
    		//code to increment queue length	  
    			  Toast.makeText(Home.this,"Your slot was successfully updated",Toast.LENGTH_LONG).show();
    			  
    		  }
    		  
    		  else
    		  {
    			  Intent i=new Intent(this,PlanActivity.class);
    		    	startActivity(i);
    		  }
         }
    }
    
    public void OnPlanButtonClick(View v)
    {
    	Intent i=new Intent(this,PlanActivity.class);
    	startActivity(i);
    }

    //NOTE!!!!
    //all the below code has something or the other to do with GPS coordinate handling

	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		location=(TextView)findViewById(R.id.location_box);
	location.setText("Latitude: " + loc.getLatitude() + "Longitude: " + loc.getLongitude());
		loc_lat=loc.getLatitude();
		loc_long=loc.getLongitude();
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extra) {
		// TODO Auto-generated method stub
		
	}
    
    public Double getLatitude(Location loc)
    {
    	Double lat=loc.getLatitude();
    	return lat;
    }
    
    public Double getLongitude(Location loc)
    {
    	Double longi=loc.getLongitude();
    	return longi;
    }
    
    protected void onPause()
    {
    	super.onPause();
    	finish();
    }
    
}