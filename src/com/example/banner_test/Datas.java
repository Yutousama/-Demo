package com.example.banner_test;

import java.io.Serializable;

public class Datas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8337648832920179094L;
	private String name,url,mangent,jmd,time;

	public String getName() {
		return name;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMangent() {
		return mangent;
	}

	public void setMangent(String mangent) {
		this.mangent = mangent;
	}

	public String getJmd() {
		return jmd;
	}

	public void setJmd(String jmd) {
		this.jmd = jmd;
	}
	private String img;
	public void setImg(String object) {
		// TODO Auto-generated method stub
		this.img=object;
	}
	public String getImg(){
		return img;
	}
	
}
