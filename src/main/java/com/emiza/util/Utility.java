package com.emiza.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import com.emiza.constant.Constant;


public class Utility {
	

	
	/**
	 * This method is used to set the Logger for a class.
	 * 
	 * @param clasz
	 *            Class To identify logger with a class.
	 * @return Logger Logger of a particular class.
	 * @throws IOException
	 *             on reading configuration file
	 */
	public Logger getLogger(Class clasz) throws IOException {
		Logger log = Logger.getLogger(clasz);
		Ini ini = getIni();

		/*
		 * Checking Logger level from configuration file and setting up level.
		 */

		if (Integer.parseInt(ini.fetch(Constant.LOGSECTION, Constant.LOGDEBUG)) == 1)
			log.setLevel(Level.DEBUG);
		else
			log.setLevel(Level.INFO);
		return log;
	}

	/**
	 * This method is used to get ini file from classpath.
	 * 
	 * @return Ini Instance of configuration file.
	 * @throws IOException
	 *             on reading configuration file
	 * @throws InvalidFileFormatException
	 *             on reading configuration file.
	 */
	public Ini getIni() throws InvalidFileFormatException, IOException {
		InputStream stream = this.getClass().getResourceAsStream(Constant.CONFIG);
		return new Ini(stream);
	}


	public String nullHander(String inputValue)
	{
		if(inputValue==null)
		{
		 return "";	
		}
		return inputValue;
	}
	
	public String nullReturn(String inputValue)
	{
		if(inputValue.isEmpty())
		{
		 return null;	
		}
		return inputValue;
	}
	
	
	public String readProperties(String fileName, String key) throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		String value = "";

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		input = classloader.getResourceAsStream(fileName);

		// load a properties file
		prop.load(input);

		if (!(key == null)) {
			value = prop.getProperty(key);
		}
		return value;
	}
	
	
	
	
}