package com.example.qless;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends Activity implements SignUpDelegate
{
	SignUpManager signUpManager = null;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		Bundle extras = getIntent().getExtras();
		String email="";
		String pwd="";


		if(email!="" || pwd!="")
		{
			EditText email_view = (EditText)findViewById(R.id.email_address_signup);
			EditText pwd_view = (EditText)findViewById(R.id.password_signup);

			email_view.setText(email.toCharArray(), 0, email.length());
			pwd_view.setText(pwd.toCharArray(), 0, pwd.length());
		}
	}

	public void send_signup_data(View v)
	{
		EditText email = (EditText)findViewById(R.id.email_address_signup);
		EditText pwd = (EditText)findViewById(R.id.password_signup);
//		EditText name = (EditText)findViewById(R.id.name);
		String email_text = email.getText().toString();
		String pwd_text = pwd.getText().toString();



		if((email_text.length()<=0) || (pwd_text.length()<=0))
		{


			return;
		}
		else
		{
//			if(!validate(email_text))
//			{
//
//				return;    			
//			}
		}


		if(signUpManager == null){
			signUpManager = new SignUpManager(this);
		}

		signUpManager.emailSignUpPlayerProfile(email_text, pwd_text, this,this);
	}


	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr)
	{
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}

	public void failSignUp(String errorDesc, int type) {
	}

	@Override
	public void notifySginUp(SignUpResponseJson signUpResponseJsons) {
		
		System.out.println(signUpResponseJsons.toString());
		if(signUpResponseJsons.getUserId() > 0)
		{

	        SharedPreferences.Editor editor;
	        SharedPreferences userid = getSharedPreferences("userid",0);
	        SharedPreferences name = getSharedPreferences("name",0);
	        SharedPreferences signedin = getSharedPreferences("signedin",0);
        	editor = userid.edit();
        	editor.putInt("userid", signUpResponseJsons.getUserId());
        	editor.commit();
        	editor = name.edit();
        	editor.putString("name",signUpResponseJsons.getName());
        	editor.commit();
        	signedin.edit().putBoolean("value_exist", true).commit();
	        
		}


		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				Utils.saveData("logged in", true);
				//Intent i = new Intent(SignUp.this, Home.class);
				Intent i = new Intent(SignUp.this, SelectBankActivity.class);
				startActivity(i);

			}
		});
	}	

public void onRestart()
{
	Intent intent = new Intent();
	intent.putExtra("result",-1);
	setResult(RESULT_OK, intent);
	finish();
	super.onRestart();
}	
}
