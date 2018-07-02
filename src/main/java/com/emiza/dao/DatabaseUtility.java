package com.emiza.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import com.emiza.constant.Constant;
import com.emiza.util.Utility;



public class DatabaseUtility {
	Connection conn = null;

	Utility util = new Utility();
	Logger log = null;
	Connection conLocal = null;

	/**
	 * This method is used to connect to database. It will take configuration
	 * file from classpath. It will set global connection variable.
	 * 
	 * @throws SQLException
	 *             on SQL query execution.
	 * @throws InvalidFileFormatException
	 *             on reading configuration file
	 * @throws IOException
	 *             on reading configuration file.
	 */
	public Connection getConnection() throws SQLException, IOException, InvalidFileFormatException
	{
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

		/* Getting configuration file instance. */
		Ini ini = util.getIni();
		log = util.getLogger(this.getClass());

		log.debug("Establishing connection.");
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

		/* Creating connection url string. */
		String connection = Constant.JDBCSQL + ini.fetch(Constant.DATABASE, Constant.SERVER)
				+ ini.fetch(Constant.DATABASE, Constant.PORT) + Constant.SDATABASE
				+ ini.fetch(Constant.DATABASE, Constant.DATABASE) + Constant.SUSER
				+ ini.fetch(Constant.USER, Constant.NAME) + Constant.SPASSWORD
				+ ini.fetch(Constant.USER, Constant.PASSWORD);

		/* Connection to database. */
		conn = DriverManager.getConnection(connection);
		log.debug("Connection Established.");
		return conn;
	}
	
	/**
	 * This method is used to fetch records from database.
	 * 
	 * @param query
	 *            String Query which needs to be executed.
	 * @return result ResultSet It will have records fetched from database.
	 * @throws SQLException
	 *             on SQL query execution.
	 * @throws InvalidFileFormatException
	 *             on reading configuration file
	 * @throws IOException
	 *             on reading configuration file.
	 */
	public ResultSet executeSqlString(String query) throws SQLException, InvalidFileFormatException, IOException 
	{
		log = util.getLogger(this.getClass());
		if (conn == null)
		{
			getConnection();
		}

		ResultSet result = null;
		PreparedStatement stmt = null;
		// Statement stmt= null;

		/* To access database. */
		// stmt = conn.createStatement();
		stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		log.info("Getting records.");

		/* Executing query and getting resultset from database. */
		result = stmt.executeQuery();

		log.info("Records fetched.");
		return result;

	}

	/**
	 * Function to open transaction and execute statement
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws InvalidFileFormatException
	 * @throws IOException
	 */
	public ResultSet executeSqlStringTransaction(String query)
			throws SQLException, InvalidFileFormatException, IOException {
		log = util.getLogger(this.getClass());
		if (conn == null) {
			getConnection();
		}
		conn.setAutoCommit(false);
		PreparedStatement stmt = null;
		// Statement stmt= null;
		ResultSet result = null;

		/* To access database. */
		conn.setAutoCommit(true);
		stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		log.debug("Getting records.");

		/* Executing query and getting resultset from database. */
		result = stmt.executeQuery();
		conn.commit();
		log.debug("Records fetched.");
		return result;

	}


}
	

