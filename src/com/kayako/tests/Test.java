package com.kayako.tests;


import com.kayako.api.configuration.Configuration;
import com.kayako.api.test.TestAPI;

public class Test {
	/**
	 * Please change the following Configuration Keys and Details according to your help desk.
	 */
	public static final String API_URL = "Your API URL e.g. http://domain.kayako.com/api/index.php?";
	
	public static final String API_KEY = "Your API Key e.g f10b5798-8f08-12c4-ddb3-3f0c58ead8cc";
	
	public static final String SECRET_KEY = "Your Secret Key e.g. MDRlMDE1OWUtODFkMC1jYjk0LTUxNWYtMTNiZjZlNTM2MDU5MmQ0NDFhZTktMDAxYi03YjQ0LTdkYjAtNzIwYjdmMmZjYjQ1";
	
	
	public static Configuration GetConfigurations() {
		return Configuration.init(API_URL, API_KEY, SECRET_KEY, true);
	}
	
	public static void main(String[] args) {
		
		Configuration.setConfiguration(GetConfigurations());
		
		try {
			TestAPI.Check();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}