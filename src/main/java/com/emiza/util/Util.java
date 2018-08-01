package com.emiza.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ProtocolException;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.emiza.Connection.Connection;
import com.emiza.constant.Constant;

public class Util {
	
	public String responseFromEasyEcom()
	{
		
		String resultdata=null;
		
		try {

			Connection conn = new Connection();
			resultdata= conn.getConnectionForEasyEcom();
		//	return results;
		} catch (ProtocolException e) {
			return e.getMessage();

		} catch (IOException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
		//System.out.println("Result::"+resultdata);
		return resultdata;
		
	}
	
	
	//for inserting the salesorder into the system
	public String insertSO(String json) {

		String results = "";
		String resultdata = null;
		
		try {

			Connection conn = new Connection();
			URLConnection connection = conn.getConnection();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(json);
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((results = in.readLine()) != null) {
				resultdata = results;
				
			}
			in.close();
		} catch (ProtocolException e) {
			return e.getMessage();

		} catch (IOException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
		//ssSystem.out.println("Result::"+resultdata);
		return resultdata;
}
	// for confirmation of order
		public String responseFromEasyEcomForConfirmation(Long orderid)
		{
			Long order_id=orderid;
			String resultdata=null;
			try{
				Connection conn = new Connection();
				resultdata = conn.getConnectionForEasyEcomConfirm(order_id);
			}
			catch (ProtocolException e) {
				return e.getMessage();

			} catch (IOException e) {
				return e.getMessage();
			} catch (Exception e) {
				return e.getMessage();
			}		
			return resultdata;
		}
		// for confirmation of order
				public String responseFromEasyEcomforDocuments(Long orderid)
				{
					Long order_id=orderid;
					String resultdata=null;
					try{
						Connection conn = new Connection();
						resultdata = conn.getConnectionForEasyEcomDocument(order_id);
					}
					catch (ProtocolException e) {
						return e.getMessage();

					} catch (IOException e) {
						return e.getMessage();
					} catch (Exception e) {
						return e.getMessage();
					}		
					return resultdata;
				}
	public String dateTimeConversion(String datetime)
	{
		
		// initialize SimpleDateFormat object
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dspatch_date=null;
		try {
			// Convert String to Date in java
			Date today = sdf.parse(datetime);

			// using locale
			DateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");

			
			//today = new Date();
			System.out.println(sdf1.format(today));
			 dspatch_date=sdf1.format(today);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dspatch_date;
	}
	
}