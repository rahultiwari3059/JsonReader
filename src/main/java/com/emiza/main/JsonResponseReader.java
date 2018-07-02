package com.emiza.main;

import java.io.FileReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.emiza.constant.Constant;
import com.emiza.dto.AddressInfo;
import com.emiza.dto.BillingAddress;
import com.emiza.dto.Buyer;
import com.emiza.dto.Financials;
import com.emiza.dto.Offer;
import com.emiza.dto.Order;
import com.emiza.dto.OrderLines;

import com.emiza.dto.Person;
import com.emiza.dto.SalesOrderInfo;
import com.emiza.dto.Seller;
import com.emiza.dto.ShippingAddress;
import com.emiza.util.InsertEasyEcom;

import com.emiza.util.Util;
import com.google.gson.Gson;

public class JsonResponseReader {

	public static void main(String[] args) throws ParseException

	{
		Util utilObject = new Util();
		JSONParser parser = new JSONParser();

		String responseJSON = utilObject.responseFromEasyEcom();
		InsertEasyEcom insertEasyEcomObject = new InsertEasyEcom();
		System.out.println(responseJSON);

		try {
			/*
			 * Object obj = parser.parse(new
			 * FileReader("C:\\rahul_work\\ResponsefromEasyecom.json"));
			 * JSONObject responsejsonobject = (JSONObject) obj;
			 */

			Object obj2 = parser.parse(responseJSON);

			// converting normal object into jsonobject
			JSONObject responsejsonobject = (JSONObject) obj2;

			String msg = (String) responsejsonobject.get(Constant.MESSAGE);

			if (msg.equals(Constant.SUCCESSFUL)) {

				JSONArray datajsonarray = (JSONArray) responsejsonobject.get(Constant.DATA);

				for (int k = 0; k < datajsonarray.size(); k++) {

					// creating object of SaleOrder class
					SalesOrderInfo salesorderinfoObject = new SalesOrderInfo();

					// creating object of order class
					Order orderObject1 = new Order();

					// creating ArrayList of Orderline class

					List<OrderLines> orderlineListObject2 = new ArrayList<>();

					JSONObject suborderjsonobject = (JSONObject) datajsonarray.get(k);

					orderObject1.setId((Long.toString((Long)suborderjsonobject.get("order_id")) ));
					orderObject1.setStatus(Constant.STATUS);
					orderObject1.setWarehouseId(Constant.WAREHOUSEID);
					orderObject1.setStatusdatetime((String) suborderjsonobject.get(Constant.ORDER_DATE));

					JSONArray suborderjsonarrayObject = (JSONArray) (suborderjsonobject.get(Constant.SUB_ORDER));

					Offer offerObject1 = new Offer();

					for (int j = 0; j < suborderjsonarrayObject.size(); j++) {

						// creating object of orderline class
						OrderLines orderinesobjcet1 = new OrderLines();

						JSONObject suborderobject = (JSONObject) (suborderjsonarrayObject.get(j));

						orderinesobjcet1.setSkuid((String) suborderobject.get(Constant.SKU));
						String SKUCODE = (String) suborderobject.get(Constant.SKU);
						Long orderedqnty = (Long) suborderobject.get(Constant.QUANTITY);
						ResultSet rs = insertEasyEcomObject.checkOrderQuantity(Constant.WAREHOUSEID, Constant.OWNERID,SKUCODE);
						
						
						int totalinventory = 0;
						int alreadyorderedqnty = 0;
						while (rs.next()) {
							
							totalinventory = rs.getInt("inventory");
							System.out.println("totalinventory " + totalinventory);
							alreadyorderedqnty = rs.getInt("Ordered");
							System.out.println("alreadyorderedqnty " + alreadyorderedqnty);
						}
						int allorderqnty = (int) (alreadyorderedqnty + orderedqnty);

						int qntyfororder = totalinventory - allorderqnty;

						System.out.println("qntyfororder " + qntyfororder);

						if (qntyfororder > 0) {

							orderinesobjcet1.setQuantity(Long.toString((Long) suborderobject.get(Constant.QUANTITY)));

							orderinesobjcet1.setOffer(offerObject1);
							orderlineListObject2.add(orderinesobjcet1);

							// creating object of Financial class
							Financials financialObject = new Financials();

							financialObject.setGst(Long.toString((Long) suborderjsonobject.get(Constant.TAXRATE)));
							financialObject.setMrp(Long.toString((Long) suborderobject.get(Constant.MRP)));
							financialObject.setPrice((String) suborderobject.get(Constant.SELLING_PRICE));
							financialObject.setVat(Constant.VAT);

							orderObject1.setFinancials(financialObject);
						} else {
							System.out.println("order has not been created due to Inventory unavailability");

							continue;
						}

						salesorderinfoObject.setOrderlines(orderlineListObject2);

						salesorderinfoObject.setOrder(orderObject1);

						// creating object of Buyer class
						Buyer buyerObject1 = new Buyer();

						// creating object of person class
						Person personobject = new Person();

						personobject.setName((String) suborderjsonobject.get(Constant.SUBNAME));
						if (suborderjsonobject.get(Constant.SUBNAME) == null) {
							personobject.setName(Constant.CUSTNAME);
						} else {

							personobject.setName((String) suborderjsonobject.get(Constant.SUBNAME));
						}

						personobject.setEmail(Constant.EMAILID);
						personobject.setPhone((String) suborderjsonobject.get(Constant.PHONE_NUMBER));

						// creating object of AddressInfo class
						AddressInfo addressInfoclassObject1 = new AddressInfo();

						// creating object of BillingAddress class
						BillingAddress billingAddressObject1 = new BillingAddress();
						if (suborderjsonobject.get(Constant.ADDRESS_LINE_1) == null) {

							billingAddressObject1.setAddress(Constant.ADDRESS);
						} else {
							billingAddressObject1.setAddress((String) suborderjsonobject.get(Constant.ADDRESS_LINE_1));
						}

						if (suborderjsonobject.get(Constant.ADDRESS_LINE_2) == null) {
							billingAddressObject1.setAddressStreet(Constant.ADDRESSSTREET);
						} else {

							billingAddressObject1.setAddressStreet((String) suborderjsonobject.get(Constant.ADDRESS_LINE_2));

						}
						billingAddressObject1.setState((String) suborderjsonobject.get(Constant.STATE));
						billingAddressObject1.setCity((String) suborderjsonobject.get(Constant.CITY));

						if (suborderjsonobject.get(Constant.SUBNAME) == null) {
							billingAddressObject1.setName(Constant.CUSTNAME);
						} else {

							billingAddressObject1.setName((String) suborderjsonobject.get(Constant.SUBNAME));
						}

						billingAddressObject1.setZip(Constant.ZIP);

						ShippingAddress shippingAddressClassObject1 = new ShippingAddress();
						if (suborderjsonobject.get(Constant.ADDRESS_LINE_1) == null) {
							shippingAddressClassObject1.setAddress(Constant.ADDRESS);
						} else {
							shippingAddressClassObject1.setAddress((String) suborderjsonobject.get(Constant.ADDRESS_LINE_1));
						}

						if (suborderjsonobject.get(Constant.ADDRESS_LINE_2) == null) {
							shippingAddressClassObject1.setAddressStreet(Constant.ADDRESSSTREET);

						} else {
							shippingAddressClassObject1
									.setAddressStreet((String) suborderjsonobject.get(Constant.ADDRESS_LINE_2));

						}

						shippingAddressClassObject1.setState((String) suborderjsonobject.get(Constant.STATE));
						shippingAddressClassObject1.setCity((String) suborderjsonobject.get(Constant.CITY));

						if (suborderjsonobject.get(Constant.SUBNAME) == null) {
							shippingAddressClassObject1.setName(Constant.NAME);
						} else {
							shippingAddressClassObject1.setName((String) suborderjsonobject.get(Constant.SUBNAME));

						}

						shippingAddressClassObject1.setZip(Constant.ZIP);

						addressInfoclassObject1.setBillingaddress(billingAddressObject1);
						addressInfoclassObject1.setShippingadress(shippingAddressClassObject1);

						buyerObject1.setAddressinfo(addressInfoclassObject1);
						buyerObject1.setPerson(personobject);
						salesorderinfoObject.setBuyer(buyerObject1);

						Seller sellerobject = new Seller();
						sellerobject.setCode(Constant.SELLER);
						salesorderinfoObject.setSeller(sellerobject);

						Gson gson = new Gson();

						String var = gson.toJson(salesorderinfoObject);

						System.out.println(var);

						String responsefromSOAPI = utilObject.insertSO(var);

						System.out.println(responsefromSOAPI);

						Object responseobject = parser.parse(responsefromSOAPI);

						JSONObject responseJSONOBJECTSOAPI = (JSONObject) responseobject;

						String result = (String) responseJSONOBJECTSOAPI.get("Status");

						String orderID = (String) responseJSONOBJECTSOAPI.get("OrderId");

						if (result.equals("Failure")) {

							JSONObject resultJSONObject = (JSONObject) responseJSONOBJECTSOAPI.get("Result");
							// System.out.println(resultJSONObject);
							JSONArray errorJsonARRAY = (JSONArray) resultJSONObject.get("Errors");

							JSONObject errorjsonObject = (JSONObject) errorJsonARRAY.get(0);

							String errormessage = (String) errorjsonObject.get("ErrorMessage");

							System.out.println("orderid " + orderID + " is not created due to " + errormessage);

						} else {

							JSONObject resultJSONObject = (JSONObject) responseJSONOBJECTSOAPI.get("Results");

							System.out.println(" orderid " + orderID + " has been created");

							Long orderid = (Long) suborderjsonobject.get("order_id");

							// for confirming oder from easy ecom

							String confirmationJson = utilObject.responseFromEasyEcomForConfirmation(orderid);
							System.out.println(confirmationJson);

							Object confirmObject = parser.parse(confirmationJson);

							// converting normal object into jsonobject
							JSONObject confirmJsonObject = (JSONObject) confirmObject;
							JSONObject datajsonobject = (JSONObject) confirmJsonObject.get("data");
							JSONArray confirmstring = (JSONArray) datajsonobject.get("Success");

							String successorderid = Long.toString((Long) confirmstring.get(0));

							if (successorderid != null) {
								// inserting orderid in databse for document
								// fetch after 30 min
								insertEasyEcomObject.insertEasyecomOrder(Constant.WAREHOUSEID, Constant.OWNERID,
										successorderid);
								System.out.println(
										"orderid " + successorderid + "  has been successfully inserted into database");

							} 

						}

					}

				}
			} 

		} catch (Exception ex) {
			System.out.println("Unable to open file ");
			ex.printStackTrace();
		}
	}
}
