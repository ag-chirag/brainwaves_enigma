package com.example.qless;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SelectBankActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank);
		List<BankList> list = new ArrayList<BankList>();
		final ListView listView = (ListView) findViewById(R.id.bank_list);
		final Activity bank_activity = this;
		BankList b1 = new BankList();
		b1.setBank_name("MSRIT");
		b1.setBid(123);
		b1.setBank_latitude(13.031441);
		b1.setBank_longitude(77.565176);
		BankList b2 = new BankList();
		b2.setBank_name("RT Nagar");
		b2.setBid(310);
		b2.setBank_latitude(13.019894);
		b2.setBank_longitude(77.596860);		
		BankList b3 = new BankList();
		b3.setBank_name("Majectic");
		b3.setBid(678);
		b3.setBank_latitude(12.977300);
		b3.setBank_longitude(77.572868);
		BankList b4 = new BankList();
		b4.setBank_name("HSR");
		b4.setBid(456);
		b4.setBank_latitude(12.977195);
		b4.setBank_longitude(77.572857);
		BankList b5 = new BankList();
		b5.setBank_name("Whitefield");
		b5.setBid(789);
		b5.setBank_latitude(12.970013);
		b5.setBank_longitude(77.749912);
		BankList b6 = new BankList();
		b6.setBank_name("Aloft");
		b6.setBid(996);
		b6.setBank_latitude(12.995171);
		b6.setBank_longitude( 77.732532);
		
		list.add(b1);list.add(b2);list.add(b3);list.add(b4);list.add(b5);list.add(b6);
		final BankAdaptor ba = new BankAdaptor(SelectBankActivity.this, list);
		listView.setAdapter(ba);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO Auto-generated method stub
		       // String s = (String)listView.getItemAtPosition(position);
		        BankList item = (BankList)ba.getItem(position);
		        SharedPreferences.Editor editor;
		        SharedPreferences bid = getSharedPreferences("bank",0);
		        bid.edit().putInt("bankid", item.getBid()).commit();
		        
	    		
	    		runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                    	Intent i = new Intent(bank_activity,Home.class);
        	    		startActivity(i); 
                    }
                });
			}
		    });

	}
}
