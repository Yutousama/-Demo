package com.example.banner_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;

public class model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1940771633411298983L;
	ExecutorService executorService;
	public boolean socketFlag = true;
	MainActivity activity;
	Sockets sockets;
	private int msg;
	private int position;
	public model(MainActivity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		executorService = Executors.newCachedThreadPool();
		sockets=new Sockets();
		executorService.execute(sockets);
	}
	public void setMs(int msg,String str){
		this.msg=msg;
		switch (msg) {
		case 0: //getTitle;
			sockets.setSocketMsg("title=",msg);
			break;
		case 1: //context;
			sockets.setSocketMsg("context=",msg);
			break;
		case 2: //searchContext;
			sockets.setSocketMsg("searchContext="+str,msg);
			break;
		case 3: //zc;
			sockets.setSocketMsg("zc=",msg);
			break;
		case 4:
			sockets.setSocketMsg("img="+str,msg);
			break;
		case 5:
			position=Integer.valueOf(str);
			sockets.setSocketMsg("zc=",msg);
			break;
		default:
			break;
		}
	}

	class Sockets extends Thread {
		BufferedReader bufferedReader;
		BufferedWriter bufferedWriter;
		JSONArray array;
		JSONObject object;
		boolean initFLag=false;
		int mse;
		public Sockets() {
			// TODO Auto-generated constructor stub
			this.mse=mse;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {//42.96.193.29
				Socket socket = new Socket("42.96.193.29", 2331);
				//Socket socket=new Socket("192.168.2.111", 2331);
				bufferedWriter = new BufferedWriter(new OutputStreamWriter(
						socket.getOutputStream(),"UTF-8"));
				bufferedReader = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), "UTF-8"));
				String str;
				
				initFLag=true;
				while (socketFlag) {
					// while里用来等待新数据， 获取新数据并解析后再通过message传送
					// 结果给MainActivity用来显示（通过list适配器）
					str = bufferedReader.readLine();
					array = new JSONArray(str);
					Message msg=new Message();
					msg.arg1=mse;
					System.out.println("MSE:"+mse);
					System.out.println("BBBB:"+array.toString());
					msg.obj=manipulationJSON(array);
					activity.handler.sendMessage(msg);
					array = null;

				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				Message meg=new Message();
				meg.arg2=2;
				meg.obj="1";
				activity.handler.handleMessage(meg);
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Message meg=new Message();
				meg.arg2=2;
				meg.arg1=-1;
				String str="网络错误,3秒后退出程序";
				meg.obj=str;
				activity.handler.handleMessage(meg);
				System.exit(0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Message meg=new Message();
				meg.arg2=2;
				meg.arg1=-1;
				String str="没有找到数据";
				meg.obj=str;
				activity.handler.handleMessage(meg);
			}
		}
		public void setSocketMsg(String msg, int m){
			try {
				this.mse=m;
				while(!initFLag){
					Thread.sleep(1000);
				}
				bufferedWriter.write(msg);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public List<Datas>  manipulationJSON(JSONArray array){
			int i=0;
			List<Datas> datasList=new ArrayList<Datas>();
			try {
			switch (msg) {
			case 0:
				JSONObject jsonObject;
				Datas datas = new Datas();
				try {
					int k=0;
					while (true) {
						jsonObject=array.getJSONObject(k++);
						datas.setName(jsonObject.getString("name"));
						datas.setUrl(jsonObject.getString("url"));
						datas.setJmd(jsonObject.getString("周次"));
						datasList.add(datas);
						datas=new Datas();
					}
			} catch (Exception e) {
				// TODO: handle exception
				Message message=new Message();
				message.arg1=0;
				message.obj=datasList;
				activity.setMessage(message);
			}
				break;
			case 1:
				
				break;
			case 2:
				try {
				for (int j = 0; j < array.length(); j++) {
					Datas datas2=new Datas();
					JSONObject jsonObject2=new JSONObject(array.getString(j));
					datas2.setName(jsonObject2.getString("title"));
					datas2.setUrl(jsonObject2.getString("url"));
					datas2.setTime(jsonObject2.getString("time"));
					try {
						datas2.setMangent(jsonObject2.getString("magnet"));
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						datas2.setMangent("null");
					}
					datasList.add(datas2);
				}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
			case 3:
				
				break;
			case 4:
				List<Datas> datas2=new ArrayList<Datas>();
				Datas datas3=new Datas();
				datas3.setImg((String) array.get(0));
				datas2.add(datas3);
				return datas2;
			case 5:
				jsonObject=new JSONObject(array.getString(0));
				List<Datas> datas4=new ArrayList<Datas>();
				Datas datas5=new Datas();
				Calendar ca = Calendar.getInstance();
				Date date = new Date();
				ca.setTime(date);
				int st = ca.get(Calendar.DAY_OF_WEEK);
				st=st-1;
				JSONArray array2=new JSONArray(jsonObject.get("周"+((position)+"")).toString());
				for (int j = 0; j < array2.length(); j++) {
					datas5.setName(array2.getString(j));
					datas4.add(datas5);
					datas5=new Datas();
				}
				datasList=datas4;
				break;
			default:
				break;
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return datasList;
		}
	}

}
