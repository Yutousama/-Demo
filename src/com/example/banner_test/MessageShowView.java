package com.example.banner_test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MessageShowView extends Activity{
	TextView title,context,info;
	ImageView image;
	ListView msgList;
	model model;
	int position;
	List<Datas> d_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		setContentView(R.layout.show_message);
		
		if(intent==null){
			Toast.makeText(this, "数据错误", Toast.LENGTH_LONG).show();
		}
		title=(TextView) findViewById(R.id.MsgTitle);
		image=(ImageView) findViewById(R.id.MsgImage);
		msgList=(ListView) findViewById(R.id.MsgList);
		List<Map<String, Object>> list=(List<Map<String, Object>>) intent.getBundleExtra("list").getSerializable("list");
		position=intent.getIntExtra("position", 0);
		if(position==-1)
		title.setText((intent.getStringExtra("edit")));
		else
		title.setText((CharSequence) list.get(position).get("name"));
	String img=intent.getStringExtra("img");
		new DownLoadImage(image).execute(img);
		d_list=(List<Datas>) intent.getBundleExtra("list").getSerializable("info");
		List<String> list_View=new ArrayList<String>();
		System.out.println(d_list.size());
			for (int i = 0; i < d_list.size(); i++) {
				System.out.println(d_list.get(i).getMangent());
				list_View.add(d_list.get(i).getTime()+"|"+d_list.get(i).getName());
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_View);
		msgList.setAdapter(adapter);
		Toast.makeText(this, "单击复制，长按打开网页", Toast.LENGTH_SHORT).show();
		msgList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub	
				ClipboardManager cmb = (ClipboardManager)MessageShowView.this.getSystemService(Context.CLIPBOARD_SERVICE);  
				cmb.setText(d_list.get(position).getMangent().trim());
				Toast.makeText(MessageShowView.this, "Mangent地址已复制到剪切板", Toast.LENGTH_LONG).show();
			}
			
		});
		msgList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String url=("https://share.dmhy.org"+d_list.get(position).getUrl());
				Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
				return false;
			}
		});
	}
}
