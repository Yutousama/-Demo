package com.example.banner_test;
import java.io.InputStream;




import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.os.AsyncTask;

import android.util.Log;

import android.widget.ImageView;
/**
 * 	在线载入图片，源代码来源于下方
 * @author http://blog.csdn.net/yhm2046/article/details/8152497 
 *
 */

public class DownLoadImage extends AsyncTask<String, Void, Bitmap> {

	ImageView imageView;




	public DownLoadImage(ImageView imageView) {

		// TODO Auto-generated constructor stub

		this.imageView = imageView;

	}




	@Override

	protected Bitmap doInBackground(String... urls) {

		// TODO Auto-generated method stub

		String url = urls[0];

		Bitmap tmpBitmap = null;

		try {

			InputStream is = new java.net.URL(url).openStream();

			tmpBitmap = BitmapFactory.decodeStream(is);

		} catch (Exception e) {

			e.printStackTrace();

			Log.i("test", e.getMessage());

		}

		return tmpBitmap;

	}




	@Override

	protected void onPostExecute(Bitmap result) {

		// TODO Auto-generated method stub

		imageView.setImageBitmap(result);

	}

}