package com.example.banner_test;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class myListViewAdapter extends BaseAdapter {
	List<Map<String, Object>> lists;
	Context context;
	private android.view.LayoutInflater listContainer;

	public myListViewAdapter(List<Map<String, Object>> list, Context context) {
		// TODO Auto-generated constructor stub
		this.lists = list;
		this.context = context;
		listContainer = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int i = position;
		item item = null;
		if (convertView == null) {
			item = new item();
			convertView = listContainer.inflate(R.layout.list_view, null);
			item.textView = (TextView) convertView.findViewById(R.id.text_View);
			convertView.setTag(item);
		} else {
			item = (item) convertView.getTag();
		}
		convertView.setOnClickListener(new OnClickListener() {
			Message msg;
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (intent == null) {
					intent = new Intent(context, MessageShowView.class);
					bundle = new Bundle();
				}
				bundle.putSerializable("list", (Serializable) lists);
				intent.putExtra("position", i);
				 msg=new Message();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						msg.obj="请稍后，收集数据中(大概1分钟）";
						msg.arg2=2;
						msg.arg1=-1;
						((MainActivity)context).getImg((String) lists.get(i).get("name"));
						((MainActivity)context).handler.sendMessage(msg);
						while (((MainActivity)context).getKey()==-1) {
						}
						intent.putExtra("img", ((MainActivity)context).getImg());
						((MainActivity)context).setXF_Info((String)lists.get(i).get("name"));
						while (((MainActivity)context).getKey()==-1) {
						}
						List<Datas> list=((MainActivity)context).getXF_Info();
						bundle.putSerializable("info", (Serializable) list);
						intent.putExtra("list", bundle);
						context.startActivity(intent);
					}
				}).start();
			
			}
		});
		//intent 传List<OBJ>
		item.textView.setText(String.valueOf(lists.get(position).get("name")));
		return convertView;
	}

	Bundle bundle;
	Intent intent;

	class item {
		public TextView textView;
		public ImageView imageView;
	}
}
