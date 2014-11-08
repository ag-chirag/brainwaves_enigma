package com.example.qless;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class BankAdaptor extends BaseAdapter{

	Activity activity;

	private LayoutInflater inflater;
	private List<BankList> list;

	public BankAdaptor(Activity activity,
			List<BankList> list2) {

		this.activity  =  activity;
		this.list = list2;
		this.inflater  =  LayoutInflater.from(activity);

	}

	@Override
	public int getCount(){
		return list.size();
	}

	@Override
	public BankList getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@SuppressLint("DefaultLocale")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{

		View v = convertView;

		v  =  this.inflater.inflate(R.layout.bank_card,null);

		TextView bankName = (TextView) v.findViewById(R.id.bank_name);
		TextView distance = (TextView) v.findViewById(R.id.distance);


		try 
		{
			bankName.setText(list.get(position).getBank_name());
			double latitude = list.get(position).getBank_latitude();
			double longitude = list.get(position).getBank_latitude();
			double user_longitude=0;
			double user_latitude=0;
			
			int R = 6371; // km
			double dLat = toRadians(latitude-user_longitude);
			double dLon = toRadians(longitude-user_longitude);
			double dist= 2*R*Math.asin(Math.sqrt(Math.pow(Math.sin(dLat/2.0),2)+Math.cos(user_longitude)*Math.cos(longitude)*Math.pow(Math.sin(dLon/2),2)));
			
			distance.setText(String.valueOf(dist));
					
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	}
	public double toRadians( double deg) {
		  return deg * (Math.PI/180);
		}
}