package com.emiza.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ini4j.InvalidFileFormatException;

import com.emiza.constant.Constant;
import com.emiza.dao.DatabaseUtility;
import com.emiza.dto.Status;

public class InsertEasyEcom {

	
	DatabaseUtility dbUtility = new DatabaseUtility();
	
public Status insertEasyecomOrder(String Warehouseid,String Ownerid,String orderId) throws InvalidFileFormatException, SQLException, IOException {
		
		Object result = null;
		Status status = new Status(); 
		
		String query = Constant.EMIZA_INSERT_EASYECOM_ORDER.replace(Constant.REPLACE_WAREHOUSE, Warehouseid)
														.replace(Constant.REPLACE_OWNER, Ownerid)
														.replace(Constant.REPLACE_ORDERID, orderId);
		//System.out.println(query);
		
		ResultSet resultSet = dbUtility.executeSqlStringTransaction(query);
		//System.out.println(resultSet);
		
		while (resultSet.next()) {
			status.setStatus(resultSet.getString("STATUS"));
		}
		return status;
	}
public ResultSet checkOrderQuantity(String Warehouseid,String Ownerid,String SKUCODE) throws InvalidFileFormatException, SQLException, IOException {
	
	Object result = null;
	Status status = new Status(); 
	
	String query = Constant.SP_EMIZA_GET_INVENTORY_FOR_SM_ORDER.replace(Constant.REPLACE_WAREHOUSE, Warehouseid)
													.replace(Constant.REPLACE_OWNER, Ownerid)
													.replace(Constant.REPLACE_SKUCODE, SKUCODE);
	//System.out.println(query);
	
	ResultSet resultSet = dbUtility.executeSqlStringTransaction(query);
	//System.out.println(resultSet);
	
	
	return resultSet;
}
}
