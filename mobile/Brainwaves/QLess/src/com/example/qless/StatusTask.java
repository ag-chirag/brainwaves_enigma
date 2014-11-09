package com.example.qless;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.TextView;

public class StatusTask  extends AsyncTask<String,Void,String>{

   private TextView statusField,roleField;
   private Context context;
   private int byGetOrPost = 0; 
   String prps;
   SharedPreferences sp;
   Editor editor;
   String result;
   Activity home;
   TextView customer1,customer2,customer3,customer4;
   TextView time1,time2,time3,time4;
   //glob obj=new glob();
   //flag 0 means get and 1 means post.(By default it is get.)
   public StatusTask(Context context,Activity home, TextView customer1, TextView customer2, TextView customer3, TextView customer4, TextView time1, TextView time2, TextView time3, TextView time4) {
      this.context = context;
      this.prps = prps;
      this.home = home;
      sp=context.getSharedPreferences("DataStorage",Context.MODE_PRIVATE);
      this.customer1 = customer1;
      this.customer2 = customer2;
      this.customer3 = customer3;
      this.customer4 = customer4;
      this.time1 = time1;
      this.time2 = time2;
      this.time3 = time3;
      this.time4 = time4;
      editor=sp.edit();
   }



protected void onPreExecute()
   {
	   
   }
   @Override
   protected String doInBackground(String... arg0) {
      
     
         try{
            String str = (String)arg0[0];
            String link="http://www.brainwave.byethost11.com/enigma/status.php" ;
            /*String data  = URLEncoder.encode("?pan", "UTF-8") 
               + "=" +  URLEncoder.encode(str, "UTF-8");*/
            URL url = new URL(link);
            URLConnection conn = url.openConnection(); 
            conn.setDoOutput(true); 
            OutputStreamWriter wr = new OutputStreamWriter
            (conn.getOutputStream());
            //wr.write( data ); 
            wr.flush(); 
            BufferedReader reader = new BufferedReader
            (new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
               sb.append(line);
               break;
            }
            return sb.toString();
         }catch(Exception e){
            return new String("Exception: " +  e.getMessage());
         }
      }
   
   
   protected void onPostExecute(String result){
	   
	   //Home.prps = result;
	   //System.out.println("Answer " + result.split("#"));
	   String res[] = result.split("#");
	   customer1.setText(res[0]);
	   customer2.setText(res[2]);
	   customer3.setText(res[4]);
	   customer4.setText(res[6]);
	   time1.setText(res[1]);
	   time2.setText(res[3]);
	   time3.setText(res[5]);
	   time4.setText(res[7]);
	   //editor.putString("val",result);
	   //editor.commit();
   }
	   }