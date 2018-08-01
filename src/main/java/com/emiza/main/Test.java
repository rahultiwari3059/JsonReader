package com.emiza.main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.ini4j.InvalidFileFormatException;

import com.emiza.util.GoogleMail;

public class Test {

	public static void main(String[] args) {
		
		// initialize SimpleDateFormat object
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
			// Convert String to Date in java
			Date today = sdf.parse("2018-06-10 12:33:46");

			// using locale
			DateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");

			
			//today = new Date();
			System.out.println(sdf1.format(today));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}

