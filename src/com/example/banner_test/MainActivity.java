package com.example.banner_test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends Activity {
	private ViewPager banner, listViewPager, banner_mask;
	private TextView tab_1, tab_2, tab_3;
	private List<View> listViews, listViews2, listVew_mask;
	public model md;
	private SlidingMenu menu;
	private ListView listView;
	public String cal[] = { "0", "日", "一", "二", "三", "四", "五", "六" };
	public void initMenu(){
		menu=new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);  
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu_main);
		listView=(ListView) menu.findViewById(R.id.listView_001);
		List<String> textList=new ArrayList<String>();
		textList.add("搜索");
		textList.add("周一");
		textList.add("周二");
		textList.add("周三");
		textList.add("周四");
		textList.add("周五");
		textList.add("周六");
		textList.add("周日");
		textList.add("设置");
		textList.add("退出");
		menu.toggle();
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(menu.getContext(), android.R.layout.simple_list_item_1, textList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			 AlertDialog.Builder builder;
			 View view2;
			 EditText editText;
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position==0){
					builder=new AlertDialog.Builder(MainActivity.this);
					LayoutInflater inflater=LayoutInflater.from(menu.getContext());
					view2 =inflater.inflate(R.layout.diost, null);
					editText=(EditText) view2.findViewById(R.id.editText1);
					builder.setTitle("请输入搜索关键字");
					builder.setView(view2);
					builder.setNegativeButton("检索", new android.content.DialogInterface.OnClickListener(){
						Message msg;
						Intent intent;
						Bundle bundle ;
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(editText.getText().toString().equals("")){
								Toast.makeText(menu.getContext(), "Orz打点关键字啦~", Toast.LENGTH_SHORT).show();
								return ;
							}
							 intent = new Intent(menu.getContext(), MessageShowView.class);
							 bundle = new Bundle();
							bundle.putSerializable("list", (Serializable) lists);
							intent.putExtra("position", -1);
							intent.putExtra("edit", editText.getText().toString());
							 msg=new Message();
							new Thread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									msg.obj="请稍后，收集数据中(大概1分钟）";
									msg.arg2=2;
									msg.arg1=-1;
									MainActivity.this.getImg(editText.getText().toString());
									MainActivity.this.handler.sendMessage(msg);
									while (MainActivity.this.getKey()==-1) {
									}
									intent.putExtra("img", MainActivity.this.getImg());
									MainActivity.this.setXF_Info(editText.getText().toString());
									while (MainActivity.this.getKey()==-1) {
									}
									List<Datas> list=MainActivity.this.getXF_Info();
									bundle.putSerializable("info", (Serializable) list);
									intent.putExtra("list", bundle);
									menu.getContext().startActivity(intent);
								}
							}).start();
							
						}
						
					});
					builder.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							view2=null;
							
						}
						
					});
					builder.show();
				}
				
				switch (position) {
				case 1:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					break;
				case 2:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					
					break;
				case 3:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					
					break;
				case 4:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					
					break;
				case 5:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					
					break;
				case 6:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					
					break;
				case 7:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					
					break;
				case 8:
					Toast.makeText(menu.getContext(), "开发中", Toast.LENGTH_SHORT).show();
					
					break;
				case 9:
					System.exit(0);
					break;

				default:
					break;
				}
				
			}
		});
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initMenu();
		Toast.makeText(this, "获取数据中，请稍后", Toast.LENGTH_LONG).show();
		listViewPager = (ViewPager) findViewById(R.id.vPager);
		banner = (ViewPager) findViewById(R.id.vPager2);
		banner_mask = (ViewPager) findViewById(R.id.vPager3);
		tab_1 = (TextView) findViewById(R.id.text1);
		tab_2 = (TextView) findViewById(R.id.text2);
		tab_3 = (TextView) findViewById(R.id.text3);
		listViews = new ArrayList<View>();
		listViews2 = new ArrayList<View>();
		listVew_mask = new ArrayList<View>();
		/*
		 * Message message=new Message(); message.arg2=1;
		 * handler.handleMessage(message);
		 */
		
		tab_1.setOnClickListener(new tabClick(0));
		tab_2.setOnClickListener(new tabClick(1));
		tab_3.setOnClickListener(new tabClick(2));
		View v1, v2, v3;
		LayoutInflater inflater;
		inflater = getLayoutInflater();
		v1 = inflater.inflate(R.layout.images_1, null);
		v2 = inflater.inflate(R.layout.images_2, null);
		v3 = inflater.inflate(R.layout.images_3, null);

		listViews.add(v1);
		listViews.add(v2);
		listViews.add(v3);
		v1 = inflater.inflate(R.layout.images_tm, null);
		v2 = inflater.inflate(R.layout.images_tm, null);
		v3 = inflater.inflate(R.layout.images_tm, null);

		listVew_mask.add(v1);
		listVew_mask.add(v2);
		listVew_mask.add(v3);
		banner.setAdapter(new MPA(listViews)); // banner用来显示，而无法交互
		banner.setCurrentItem(0);
		banner_mask.setAdapter(new MPA(listVew_mask));// banner_mask用来遮罩，不显示内容，但可交互，并且将同步操作banner
		List<Datas> list = new ArrayList<Datas>();
		listViews2.add(getList(R.layout.list_view_1, list));
		listViews2.add(getList(R.layout.list_view_2, list));
		listViews2.add(getList(R.layout.list_view_3, list));

		listViewPager.setAdapter(new MPA(listViews2));// list模块
		listViewPager.setCurrentItem(0);
		tab_1.setBackgroundResource(R.drawable.bj);

		banner_mask.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "显示第" + (arg0 + 1) + "张图片",
						Toast.LENGTH_SHORT).show();
				banner.setCurrentItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		listViewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

				switch (arg0) {
				case 0:
					tab_1.setBackgroundResource(R.drawable.bj);
					tab_2.setBackgroundColor(new Color().argb(0, 0, 0, 0));
					tab_3.setBackgroundColor(new Color().argb(0, 0, 0, 0));
					break;
				case 1:
					tab_1.setBackgroundColor(new Color().argb(0, 0, 0, 0));
					tab_2.setBackgroundResource(R.drawable.bj);
					tab_3.setBackgroundColor(new Color().argb(0, 0, 0, 0));
					break;
				case 2:
					tab_1.setBackgroundColor(new Color().argb(0, 0, 0, 0));
					tab_2.setBackgroundColor(new Color().argb(0, 0, 0, 0));
					tab_3.setBackgroundResource(R.drawable.bj);
					break;

				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

		});
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		md = new model(MainActivity.this);
		md.setMs(0, "title=");
	}
	int key=-1;

	public void setKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}
	String img="";
	public void setImg(String url){
		key=1;
		img=url;
	}
	public String getImg(){
		key=-1;
		return img;
	}
	public void getImg(String name){
		md.setMs(4, name);
	}
	public void showTitle(List<Datas> list) {
		if (list == null) {
			return;
		}
		listViews2 = new ArrayList<View>();
		List<Datas> list_1, list_2, list_3;
		list_1 = new ArrayList<Datas>();
		list_2 = new ArrayList<Datas>();
		list_3 = new ArrayList<Datas>();
		Calendar ca = Calendar.getInstance();
		Date date = new Date();
		ca.setTime(date);
		int st = ca.get(Calendar.DAY_OF_WEEK);
		System.out.println("ST:" + st);
		for (Datas datas : list) {
			// System.out.println(datas.getName());
			if (st == 1) {
				if (datas.getJmd().contains(cal[7])) {
					list_1.add(datas);
				}
			} else if (datas.getJmd().contains(cal[st - 1])) {
				list_1.add(datas);
			}
			if (datas.getJmd().contains(cal[st])) {
				list_2.add(datas);
			}
			if (st == 7) {
				if (datas.getJmd().contains(cal[1])) {
					list_3.add(datas);
				}
			} else if (datas.getJmd().contains(cal[st + 1])) {
				list_3.add(datas);
			}
		}
		listViews2.add(getList(R.layout.list_view_1, list_1));
		listViews2.add(getList(R.layout.list_view_2, list_2));
		listViews2.add(getList(R.layout.list_view_3, list_3));

		listViewPager.setAdapter(new MPA(listViews2));// list模块
		listViewPager.setCurrentItem(1);

	}
	public void setXF_Info(String name){
		md.setMs(2,name);
	}
	List<Datas> DataList;
	public void setXF_Info(List<Datas> list){
		DataList=list;
		setKey(1);
	}
	public List<Datas> getXF_Info(){
		setKey(-1);
		return DataList;
	}
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		handler.sendMessage(message);
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(final Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.arg2 == 1) {

			}
			switch (msg.arg1) {
			case 0:
				showTitle((List<Datas>) msg.obj);
				break;
			case 1:

				break;
			case 2:
				setXF_Info((List<Datas>)msg.obj);
				break;
			case 3:

				break;
			case 4:
				setImg(((List<Datas>)msg.obj).get(0).getImg());
				break;

			default:
				break;
			}

			if (msg.arg2 == 2) {
				try {
					Looper.prepare();
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("MSG:"+msg.obj);
				Toast.makeText(MainActivity.this, (CharSequence) msg.obj,
						Toast.LENGTH_LONG).show();
				msg.arg2=0;
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							
						if(((String)msg.obj).equals("网络错误,3秒后退出程序")){
							System.exit(0);
						}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}).start();
				try {
					Looper.loop();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			msg.arg1=0;
		}
	};

	class tabClick implements OnClickListener {
		int click = 0;

		public tabClick(int click) {
			// TODO Auto-generated constructor stub
			this.click = click;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			listViewPager.setCurrentItem(click);
		}

	}

	List<Map<String, Object>> lists;

	public View getList(int r, List<Datas> list) {
		LayoutInflater inflater = getLayoutInflater();
		View v1;
		v1 = inflater.inflate(r, null);
		if (list == null) {
			return v1;
		}
		ListView listView = (ListView) v1.findViewById(R.id.MsgList);
		lists = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", list.get(i).getName());
			// map.put("Image",Image); //可加入图片
			lists.add(map);
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});
		LinearLayout.LayoutParams Lparams = (android.widget.LinearLayout.LayoutParams) listViewPager
				.getLayoutParams();
		LinearLayout.LayoutParams Lparams2 = (android.widget.LinearLayout.LayoutParams) listView
				.getLayoutParams();
		System.out.println("list:" + list.size());
		DisplayMetrics dm = new DisplayMetrics();getWindowManager().getDefaultDisplay().getMetrics(dm);

		int height = dm.heightPixels; //屏幕高

		Lparams2.height =height;

		listViewPager.setLayoutParams(Lparams2);
		listView.setAdapter(new myListViewAdapter(lists, this));
		listView.setFocusable(false);
		return v1;
	}

}
