package com.example.qless;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Debug;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class Utils {
	public static final int ROUNDED = 75;
	public static final int SQUIRE = 0;

	private static String TAG  =  "UTILS";
	public static int screenHeight  =  0;
	public static int screenWidth  =  0;
	public static float density  =  0;
	private static Toast toast;;
	public static int peirod = 3000;
	private static int delay = 1;
	private static DisplayMetrics metrics;



	public static boolean isOnline(Context ctx) {
		ConnectivityManager cm  =  (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		try{
			return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		}catch(Exception ex){
			Log.d(TAG, "probably no active networks");
		}
		return false;

	}

	@SuppressWarnings("deprecation")
	public static int getScreenOrientation(Activity ctx)
	{
		Display getOrient  =  ctx.getWindowManager().getDefaultDisplay();
		int orientation  =  Configuration.ORIENTATION_UNDEFINED;
		if(getOrient.getWidth()  ==  getOrient.getHeight()){
			orientation  =  Configuration.ORIENTATION_SQUARE;
		} else{ 
			if(getOrient.getWidth() < getOrient.getHeight()){
				orientation  =  Configuration.ORIENTATION_PORTRAIT;
			}else { 
				orientation  =  Configuration.ORIENTATION_LANDSCAPE;
			}
		}
		Log.d(TAG, "width x height"+getOrient.getWidth()+getOrient.getHeight());
		return orientation;
	}

	@SuppressWarnings("deprecation")
	public static boolean isLandscape(Activity ctx){
		Display getOrient  =  ctx.getWindowManager().getDefaultDisplay();
		return getOrient.getHeight()<getOrient.getWidth();
	}

	public static boolean isNull(Object obj){
		return obj  ==  null;
	}

	@SuppressLint("DefaultLocale")
	public static void logMemory(){
		Debug.MemoryInfo memoryInfo  =  new Debug.MemoryInfo();
		Debug.getMemoryInfo(memoryInfo);

		String memMessage  =  String.format(
				"Memory: Pss = %.2f MB, Private = %.2f MB, Shared = %.2f MB, native heap = %f MB",
				memoryInfo.getTotalPss() / 1024.0,
				memoryInfo.getTotalPrivateDirty() / 1024.0,
				memoryInfo.getTotalSharedDirty() / 1024.0,
				Debug.getNativeHeapAllocatedSize()/(1024.0*1024.0));
		Log.w("MEMORY", memMessage);

	}


	public static boolean isResolutionIncompatible(Activity ctx) {
		DisplayMetrics metrics  =  new DisplayMetrics();
		ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height  =  metrics.heightPixels;
		int width  =  metrics.widthPixels;
		Log.d("HEIGHT", "height : "+height);
		Log.d("WIDTH", "width : "+width);
		if(height>width?height<426||width<320:width<426||height<320){
			Log.d("UTIL", "disabling screen");
			return true;
		}
		Log.d("UTIL", "AOK");
		return false;
	}



	public static void initializeConstants(Activity ctx){
		try {
			metrics  =  new DisplayMetrics();
			ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);

			Utils.screenHeight  =  metrics.heightPixels;
			Utils.screenWidth  =  metrics.widthPixels;
			Utils.density  =  ctx.getResources().getDisplayMetrics().density;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeStreamQuietly(InputStream inputStream) {
		try {
			if (inputStream !=  null) {
				inputStream.close();
			}
		} catch (IOException e) {
			// ignore exception
		}
	}

//	public static boolean isConnectedToNetwork() {
//		Context ctx  =  MainApplication.getContext();
//		ConnectivityManager cm  =  (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo i  =  cm.getActiveNetworkInfo();
//
//		if (i   ==   null)
//			return false;
//
//		if (!i.isConnected())
//			return false;
//		if (!i.isAvailable())
//
//			return false;
//		return true;
//	}

	public static SharedPreferences getPreferencesInstance(SharedPreferences preferences) {

		if(preferences   ==   null){

			return	PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
		}

		return preferences;
	}




	@SuppressLint("SimpleDateFormat")
	public static String getDateParse(String input){
		try {
			SimpleDateFormat df1  =  new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy");
			//SimpleDateFormat df2  =  new SimpleDateFormat("MMM dd, yyyy | hh:mm a");

			SimpleDateFormat df2  =  new SimpleDateFormat("dd MMMM yyyy");
			return df2.format(df1.parse(input));
		}
		catch (ParseException e) {
			Log.d(TAG, "input  = ",  e);
			return input;
		}

	}



	@SuppressWarnings("deprecation")
	@SuppressLint("Wakelock")
	public static PowerManager.WakeLock setActiveScrren(Context context) {

		PowerManager.WakeLock mWakeLock ;
		final PowerManager pm = (PowerManager)context. getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, context.getString(R.string.app_name));

		try {
			mWakeLock.acquire();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return mWakeLock;
	}

	public static void releseActiveScreen(PowerManager.WakeLock mWakeLock ) {

		try {
			if(mWakeLock != null){

				mWakeLock.release();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}



	@SuppressLint("SimpleDateFormat")
	static	public  String getTimeParse(String input){
		try {
			SimpleDateFormat df1  =  new SimpleDateFormat("HH:mm:ss");
			//SimpleDateFormat df2  =  new SimpleDateFormat("MMM dd, yyyy | hh:mm a");

			SimpleDateFormat df2  =  new SimpleDateFormat("hh.mm a");
			return df2.format(df1.parse(input));
		}
		catch (ParseException e) {
			Log.d(TAG, "input  = ",  e);
			return input;
		}
	}


	public static String trimBaseUrl(String url) {


		try {
			String[] separated = url.split("wisdenindia.com");
			return separated[1];
		} catch (Exception e2) {
			return url;

		}
	}

	public static int getGridViewHeight(Context context, int numCols, int columnPerRow, int cellHeight) {
		int height = 300;

		int noOfRows = (int)Math.ceil(((numCols / (float)columnPerRow)));

		try{

			final float scale = context.getResources().getDisplayMetrics().density;
			int pixels = (int) (cellHeight * scale + 0.5f);
			height = (pixels * noOfRows);

		}
		catch(Exception e){}

		return height;
	}

	/**
	 * This method converts dp unit to equivalent pixels, depending on device density. 
	 * 
	 * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on device density
	 */
	public static float convertDpToPixel(float dp, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent pixels.
	 * 
	 * @param px A value in px (pixels) unit. Which we need to convert into db
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}


	public static  void saveData(String id, String value) {

		SharedPreferences preferences = Utils.getPreferencesInstance(null);
		preferences.edit().putString(id, value).commit() ;


	}

	public static void saveData(String id, int value) {
		SharedPreferences preferences = Utils.getPreferencesInstance(null);
		preferences.edit().putInt(id, value).commit() ;
	}

	public static int getData(String id, int value) {
		if(id != null && id.trim().length() > 0){
			SharedPreferences preferences = Utils.getPreferencesInstance(null);
			value = preferences.getInt(id, value) ;
		}
		return value; 
	}

	public static String getData(String id, String value) {
		if(id != null && id.trim().length() > 0){

			SharedPreferences preferences = Utils.getPreferencesInstance(null);
			value = preferences.getString(id, value) ;
		}
		return value; 
	}


	//	public static  void openLeaderboard(final Activity activity, final String id, TextView textView, final GamesClient gamesClient) {
	//
	//		try {
	//
	//			if(textView !=  null){
	//				textView.setOnClickListener(new OnClickListener() {
	//					//@Override
	//					@Override
	//					public void onClick(View v) {
	//						Log.d(TAG, "opening leadboard activity :: game client connected " + gamesClient.isConnected()); 
	//						if(gamesClient != null && gamesClient.isConnected()){
	//							activity.startActivityForResult(gamesClient.getLeaderboardIntent(id), 1 );
	//
	//							activity.overridePendingTransition(R.anim.slide_in_right, R.anim.zoom_exit);
	//						}
	//					}
	//				});
	//			}
	//
	//		} catch (Exception e) {
	//
	//			e.printStackTrace();
	//		}
	//
	//
	//
	//	}

	public static void showMessage(final Activity activity, final String description) {

		activity.runOnUiThread(new Runnable() {



			@Override
			public void run() {
				LayoutInflater inflater = LayoutInflater.from(activity);
				View v = inflater.inflate(R.layout.toast, null);

				//				if (toast != null){
				//					toast.cancel();
				//					toast = null;
				//				}

				Toast	toast = Toast.makeText(activity, description, 100);


				TextView msg = (TextView) v.findViewById(R.id.message);
				msg.setText(description);

				toast.show();

			}
		});
	}

	public static int getPeirod() {
		// TODO Auto-generated method stub
		return peirod;
	}

	public static int getDelay() {
		// TODO Auto-generated method stub
		return delay;
	}

	public static boolean getData(String id, boolean value) {
		// TODO Auto-generated method stub
		if(id != null && id.trim().length() > 0){
			SharedPreferences preferences = Utils.getPreferencesInstance(null);
			value = preferences.getBoolean(id, value) ;
		}
		return value;
	}

	public static void saveData(String id, boolean value) {
		// TODO Auto-generated method stub
		SharedPreferences preferences = Utils.getPreferencesInstance(null);
		preferences.edit().putBoolean(id, value).commit() ;
	}
}
