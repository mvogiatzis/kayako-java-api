package com.kayako.api.test; 

import java.util.ArrayList;
import com.kayako.api.enums.HttpResponseType;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.library.RESTClient;

public class TestAPI extends KEntity{
	/**
	 * The Controller.
	 */
	static protected String controller = "/Core/TestAPI";
	
	/**
	 * To Check the API is working.
	 * 
	 * @throws Exception
	 */
	public static void Check() throws Exception 
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("GetData");
		System.out.println("GET Request Test Results \n");
		System.out.println(((RESTClient) KEntity.getRESTClient()).setResponseType(HttpResponseType.PLAIN).get(controller, list).toString());
		
		list.clear();
		list.add("PostData");
		System.out.println("\n POST Request Test Results \n");
		System.out.println(((RESTClient) KEntity.getRESTClient()).setResponseType(HttpResponseType.PLAIN).post(controller, list).toString());
	}

	@Override
	public KEntity populate(RawArrayElement element) throws KayakoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}