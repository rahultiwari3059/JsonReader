package com.emiza.Connection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import com.emiza.constant.Constant;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;





public class Connection {

	private static HttpURLConnection httpConn;   
	public URLConnection getConnection() throws Exception {
		
		URL url = new URL(Constant.PROCESSSOATEMIZA);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setConnectTimeout(50000);
		connection.setReadTimeout(50000);
		connection.setRequestProperty(Constant.METHODSTRING, Constant.METHOD);
		connection.setRequestProperty(Constant.TOKENSM,Constant.EMIZATOKENSM);
		connection.setRequestProperty(Constant.CONTENT_TYPE, Constant.CONTENT_TYPE_VALUE);
		connection.setRequestProperty(Constant.ACCEPT, Constant.CONTENT_TYPE_VALUE);
		return connection;
	}
	
public String getConnectionForEasyEcom() throws Exception {
	
		
		OkHttpClient client = new OkHttpClient();
		HttpUrl.Builder urlBuilder = HttpUrl.parse(Constant.EASYECOMURL).newBuilder();
		urlBuilder.addQueryParameter("api_token", Constant.EASYECOMTOKEN);
		
		String url = urlBuilder.build().toString();

		Request request = new Request.Builder()
		                     .url(url)
		                     .build();


		Response response = client.newCall(request).execute();

		String data = response.body().string();
		return data;
		
		
	}
	//for confirmation of order 
public String getConnectionForEasyEcomConfirm(Long order_id) throws Exception {
	
	String orderid=Long.toString(order_id);
	OkHttpClient client = new OkHttpClient();
	HttpUrl.Builder urlBuilder = HttpUrl.parse(Constant.EASYECOMORDERCONFIRMURL).newBuilder();
	urlBuilder.addQueryParameter("api_token", Constant.EASYECOMTOKEN);
	
	urlBuilder.addQueryParameter("order_id", orderid);
	String url = urlBuilder.build().toString();

	Request request = new Request.Builder()
	                     .url(url)
	                     .build();


	Response response = client.newCall(request).execute();

	String data = response.body().string();
	return data;
	
	
}
public String getConnectionForEasyEcomDocument(Long order_id) throws Exception {
	
	String orderid=Long.toString(order_id);
	OkHttpClient client = new OkHttpClient();
	HttpUrl.Builder urlBuilder = HttpUrl.parse(Constant.EASYECOMDOCUMENTS).newBuilder();
	urlBuilder.addQueryParameter("api_token", Constant.EASYECOMTOKEN);
	
	urlBuilder.addQueryParameter("order_id", orderid);
	String url = urlBuilder.build().toString();

	Request request = new Request.Builder()
	                     .url(url)
	                     .build();


	Response response = client.newCall(request).execute();

	String data = response.body().string();
	return data;
	
	
}
	

}
