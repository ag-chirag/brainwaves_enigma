package com.example.qless;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        SharedPreferences.Editor editor;
        SharedPreferences stat_initialize = getSharedPreferences("signedin",0);
        boolean initial_value = stat_initialize.getBoolean("signedin", false); 
        if(initial_value == true)
        {
        	
        }
        else
        {
    		Intent i = new Intent(this,SignUp.class);
    		startActivity(i);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onResume()
    {
    	super.onResume();
    	finish();
    }




}
