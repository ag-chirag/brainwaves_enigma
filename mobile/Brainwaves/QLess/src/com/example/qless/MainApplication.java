package com.example.qless;

import java.util.HashMap;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MainApplication extends Application {



	private static MainApplication instance;
	public MainApplication() {
		instance  =  this;
	}

	public static Context getContext() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}



	public static boolean isAppRunning() {
		return appRunning;
	}  

	public static void appStarting() {
		appRunning = true;
	}

	public static void appStop() {
		appRunning = false;
	}

	private static boolean appRunning;


	@Override
	public void onTerminate(){
		super.onTerminate();
	}


}
